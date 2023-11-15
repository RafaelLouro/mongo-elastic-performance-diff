package test.mongoWorker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import test.mongoWorker.group.MongoGroup;
import test.mongoWorker.group.MongoGroupRepository;
import test.mongoWorker.group.PostgresGroupRepository;
import test.mongoWorker.people.MongoPeople;
import test.mongoWorker.people.MongoPeopleRepository;
import test.mongoWorker.people.PostgresPeopleRepository;

import java.time.Duration;
import java.time.Instant;

@Service
public class ImportToMongoCommand implements Command {

    private final MongoGroupRepository mongoGroupRepository;
    private final MongoPeopleRepository mongoPeopleRepository;
    private final PostgresGroupRepository postgresGroupRepository;
    private final PostgresPeopleRepository postgresPeopleRepository;
    private final Logger logger = LoggerFactory.getLogger(ImportToMongoCommand.class);

    public ImportToMongoCommand(MongoGroupRepository mongoGroupRepository, MongoPeopleRepository mongoPeopleRepository, PostgresGroupRepository postgresGroupRepository, PostgresPeopleRepository postgresPeopleRepository) {
        this.mongoGroupRepository = mongoGroupRepository;
        this.mongoPeopleRepository = mongoPeopleRepository;
        this.postgresGroupRepository = postgresGroupRepository;
        this.postgresPeopleRepository = postgresPeopleRepository;
    }

    @Override
    public Mono<Void> execute() {
        Instant start = Instant.now();
        final String[] groupId = new String[1];

        postgresGroupRepository.findAll()
                .take(1)
                .map(MongoGroup::new)
                .flatMap(mongoGroupRepository::save)
                .doOnNext(mongoGroup -> {
                    groupId[0] = mongoGroup.getId();
                    logger.info("Mongo Group saved - ID: {}", groupId[0]);
                })
                .thenMany(postgresPeopleRepository.findAll())
                .take(1000000)
                .flatMap(postgresPeople -> mongoPeopleRepository.save(new MongoPeople(postgresPeople)))
                .timed()
                .doOnNext(groupTimed -> {
                    logger.info("<<<<<<<<<<<<<<<<< {} nanosecs >>>>>>>>>>>>>>>>>>", groupTimed.elapsed().toNanos());
                })
                .doOnComplete(() ->  logger.info("<<<<<<<<<<<<<<<<< Termninado em {} minutes >>>>>>>>>>>>>>>>>>", Duration.between(start, Instant.now()).toMinutes()))
                .thenMany(mongoGroupRepository.findAll())
                .count()
                .doOnNext(item -> System.out.println("<<<<<<<<<<<<<<<<< Total de itens: " + item + " >>>>>>>>>>>>>>>>>>"))
                .subscribe();

        mongoGroupRepository.findAll().log().subscribe();

        return Mono.empty();
    }
}
