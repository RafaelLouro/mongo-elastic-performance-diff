package test.mongoWorker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.mongoWorker.group.PostgresGroup;
import test.mongoWorker.group.PostgresGroupRepository;
import test.mongoWorker.people.PostgresPeople;
import test.mongoWorker.people.PostgresPeopleRepository;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PopulatePostgresCommand implements Command {

    private final PostgresGroupRepository postgresGroupRepository;
    private final PostgresPeopleRepository postgresPeopleRepository;
    private final Logger logger = LoggerFactory.getLogger(PopulatePostgresCommand.class);

    public PopulatePostgresCommand(PostgresGroupRepository postgresGroupRepository, PostgresPeopleRepository postgresPeopleRepository) {
        this.postgresGroupRepository = postgresGroupRepository;
        this.postgresPeopleRepository = postgresPeopleRepository;
    }

    @Override
    public Mono<Void> execute() {
        Instant start = Instant.now();
        final UUID[] groupId = new UUID[1];

        postgresGroupRepository.save(new PostgresGroup("Group 1", 1, LocalDateTime.now(), LocalDateTime.now()))
                .doOnNext(group -> {
                    groupId[0] = group.getId();
                    logger.info("Postgres Group saved - ID: {}", groupId[0]);
                })
                .thenMany(

                        Flux.range(0, 1000000)
                                .doOnNext(count -> logger.info("Count processing: {}", count))
                                .map(i -> new PostgresPeople("XXXX" + i, groupId[0], 1, true, LocalDateTime.now(), LocalDateTime.now()))
                                .flatMap(postgresPeopleRepository::save)
                                .timed()
                                .doOnNext(groupTimed -> {
                                    logger.info("<<<<<<<<<<<<<<<<< {} nanosecs >>>>>>>>>>>>>>>>>>", groupTimed.elapsed().toNanos());
                                })
                                .doOnComplete(() -> logger.info("<<<<<<<<<<<<<<<<< Termninado em {} minutes >>>>>>>>>>>>>>>>>>", Duration.between(start, Instant.now()).toMinutes()))

                )
                //.log()
                .subscribe();

        return Mono.empty();
    }
}
