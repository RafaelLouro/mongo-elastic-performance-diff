package test.mongoWorker.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.mongoWorker.group.PostgresGroup;
import test.mongoWorker.group.PostgresGroupRepository;

import java.time.LocalDateTime;

@Configuration
public class DataSourceConfig {

    /*@Bean
    public CommandLineRunner initMongo(ReactiveMongoOperations operations, MongoGroupRepository mongoGroupRepository, MongoPeopleRepository mongoPeopleRepository) {
        return args -> {
            Flux<Group> groupFlux = Flux.just(
                    new Group(UUID.randomUUID(), "rafael", 2, LocalDateTime.now(), LocalDateTime.now()),
                    new Group(UUID.randomUUID(), "jao", 1, LocalDateTime.now(), LocalDateTime.now()),
                    new Group(UUID.randomUUID(), "rodolfo", 1, LocalDateTime.now(), LocalDateTime.now())
            ).flatMap(mongoGroupRepository::save);

            groupFlux.thenMany(mongoGroupRepository.findAll()).subscribe(System.out::println);

            //Aqui seria para, toda vez que a aplicação ligar, ele criar uma nova collection de documentos no mongo.
            operations.collectionExists(Product.class)
                    .flatMap(exists -> exists ? operations.dropCollection(Product.class) : Mono.just(exists))
                    .thenMany(v -> operations.createCollection(Product.class))
                    .thenMany(productFlux)
                    .thenMany(repository.findAll())
                    .subscribe(System.out::println);
        };
    }*/

    @Bean
    public CommandLineRunner initPostgres(PostgresGroupRepository postgresGroupRepository) {
        return args -> {
            Flux<PostgresGroup> groupFluxOnPostgres = postgresGroupRepository.findAll();

            Mono<Boolean> checkIfDbIsInitialized = groupFluxOnPostgres.count().map(count -> count < 3);

            checkIfDbIsInitialized.filter(i -> i).doOnEach(i -> {
                        Flux<PostgresGroup> groupFlux = Flux.just(
                                new PostgresGroup("rafael", 2, LocalDateTime.now(), LocalDateTime.now()),
                                new PostgresGroup("jao", 1, LocalDateTime.now(), LocalDateTime.now()),
                                new PostgresGroup("rodolfo", 1, LocalDateTime.now(), LocalDateTime.now())
                        ).flatMap(postgresGroupRepository::save);

                        groupFlux.thenMany(postgresGroupRepository.findAll()).subscribe(System.out::println);
                    }
            );
        };
    }

}
