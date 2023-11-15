package test.mongoWorker.group;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface MongoGroupRepository extends ReactiveMongoRepository<MongoGroup, UUID> {
}
