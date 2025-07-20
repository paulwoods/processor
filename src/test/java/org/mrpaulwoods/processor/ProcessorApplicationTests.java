package org.mrpaulwoods.processor;

import org.junit.jupiter.api.Test;
import org.mrpaulwoods.processor.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureWebTestClient
class ProcessorApplicationTests {

    @Autowired
    private WebTestClient client;

    @Test
    public void crud() {

        JobDto dto = new JobDto();
        dto.setStatus(JobStatus.DRAFT);
        dto.setUrl("http://www.example.com/runner/1");

        JobDto created = this.client.post()
                .uri("/job")
                .bodyValue(dto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JobDto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals(dto.getStatus(), created.getStatus());

        List<JobDto> jobs = this.client.get()
                .uri("/job", Map.of("page", 0, "size", 10))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(JobDto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(jobs);
        assertEquals(1, jobs.size());
        assertEquals(created.getId(), jobs.getFirst().getId());
        assertEquals(created.getStatus(), jobs.getFirst().getStatus());
        assertEquals(created.getUrl(), jobs.getFirst().getUrl());

        JobDto read = this.client.get()
                .uri("/job/" + created.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JobDto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(read);
        assertEquals(read.getId(), created.getId());
        assertEquals(read.getStatus(), created.getStatus());
        assertEquals(read.getUrl(), created.getUrl());

        this.client.delete()
                .uri("/job/" + created.getId())
                .exchange()
                .expectStatus().isNoContent();
    }

}
