package org.mrpaulwoods.processor.controller;

import org.mrpaulwoods.processor.dto.JobDto;
import org.mrpaulwoods.processor.service.JobService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

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
    public Mono<JobDto> read(@PathVariable Long id) {
        return this.jobService.read(id);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Boolean> delete(@PathVariable Long id) {
        return this.jobService.delete(id);
    }

}
