package test.mongoWorker.people;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface PostgresPeopleRepository extends ReactiveCrudRepository<PostgresPeople, UUID> {
}
