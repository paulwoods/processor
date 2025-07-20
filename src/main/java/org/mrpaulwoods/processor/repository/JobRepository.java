package org.mrpaulwoods.processor.repository;

import org.mrpaulwoods.processor.entity.Job;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface JobRepository extends ReactiveCrudRepository<Job, UUID> {
    Flux<Job> findBy(Pageable pageable);

    Mono<Boolean> deleteJobById(UUID id);
}

