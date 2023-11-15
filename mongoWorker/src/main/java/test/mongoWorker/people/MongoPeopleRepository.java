package test.mongoWorker.people;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface MongoPeopleRepository extends ReactiveMongoRepository<MongoPeople, UUID> {
    Flux<MongoPeople> findByGroupId(UUID id);
}
