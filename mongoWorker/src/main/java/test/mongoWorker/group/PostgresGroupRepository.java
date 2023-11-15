package test.mongoWorker.group;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface PostgresGroupRepository extends ReactiveCrudRepository<PostgresGroup, UUID> {
}
