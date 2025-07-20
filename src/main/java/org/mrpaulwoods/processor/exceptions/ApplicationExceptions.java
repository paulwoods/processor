package org.mrpaulwoods.processor.exceptions;

import reactor.core.publisher.Mono;

import java.util.UUID;

public class ApplicationExceptions {

    public static <T> Mono<T> jobNotFound(UUID id) {
        return Mono.error(new JobNotFoundException(id));
    }

    public static <T> Mono<T> urlMissing() {
        return Mono.error(new InvalidInputException("URL is required"));
    }

    public static <T> Mono<T> statusMissing() {
        return Mono.error(new InvalidInputException("Status is required"));
    }

}
