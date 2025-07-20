package org.mrpaulwoods.processor.controller;

import org.mrpaulwoods.processor.dto.JobDto;
import org.mrpaulwoods.processor.service.JobService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("job")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public Mono<List<JobDto>> list(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return this.jobService.list(PageRequest.of(page, size));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<JobDto> create(@RequestBody Mono<JobDto> mono) {
        return this.jobService.create(mono);
    }

    @GetMapping("{id}")
    public Mono<JobDto> read(@PathVariable UUID id) {
        return this.jobService.read(id);
    }

    @PutMapping("{id}")
    public Mono<JobDto> update(@PathVariable UUID id, @RequestBody Mono<JobDto> mono) {
        return this.jobService.update(id, mono);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Boolean> delete(@PathVariable UUID id) {
        return this.jobService.delete(id);
    }

}
