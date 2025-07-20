package org.mrpaulwoods.processor.service;

import org.mrpaulwoods.processor.dto.JobDto;
import org.mrpaulwoods.processor.mapper.EntityDtoMapper;
import org.mrpaulwoods.processor.repository.JobRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Mono<List<JobDto>> list(Pageable pageable) {
        return jobRepository.findBy(pageable)
                .map(EntityDtoMapper::toDto)
                .collectList();
    }

    public Mono<JobDto> create(Mono<JobDto> mono) {
        return mono
                .map(EntityDtoMapper::toEntity)
                .flatMap(jobRepository::save)
                .map(EntityDtoMapper::toDto);
    }

    public Mono<JobDto> read(Long id) {
        return jobRepository.findById(id)
                .map(EntityDtoMapper::toDto);
    }

    public Mono<Boolean> delete(Long id) {
        return jobRepository.deleteJobById(id);
    }

}
