package test.mongoWorker.service;

import reactor.core.publisher.Mono;

public interface Command {
    Mono<Void> execute();
}
