package org.mrpaulwoods.processor.validator;

import io.micrometer.common.util.StringUtils;
import org.mrpaulwoods.processor.dto.JobDto;
import org.mrpaulwoods.processor.exceptions.ApplicationExceptions;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class RequestValidator {

    public static UnaryOperator<Mono<JobDto>> validate() {
        return mono -> mono
                .filter(hasUrl())
                .switchIfEmpty(ApplicationExceptions.urlMissing())
                .filter(hasStatus())
                .switchIfEmpty(ApplicationExceptions.statusMissing())
                ;
    }

    private static Predicate<JobDto> hasUrl() {
        return dto -> !StringUtils.isBlank(dto.getUrl());
    }

    private static Predicate<JobDto> hasStatus() {
        return dto -> dto.getStatus() != null;
    }

}
