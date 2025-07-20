package org.mrpaulwoods.processor;

import org.junit.jupiter.api.Test;
import org.mrpaulwoods.processor.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureWebTestClient
class ProcessorApplicationTests {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProcessorApplicationTests.class);

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

        JobDto dto2 = new JobDto();
        dto2.setStatus(JobStatus.PENDING);
        dto2.setUrl("http://www.example.com/runner/2");

        JobDto update = this.client.put()
                .uri("/job/" + created.getId())
                .bodyValue(dto2)
                .exchange()
                .expectStatus().isOk()
                .expectBody(JobDto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(update);
        assertEquals(update.getId(), created.getId());
        assertEquals(update.getStatus(), dto2.getStatus());
        assertEquals(update.getUrl(), dto2.getUrl());

        this.client.delete()
                .uri("/job/" + created.getId())
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void read_invalid_id_returns_not_found() {

        UUID id = UUID.randomUUID();

        this.client.get()
                .uri("/job/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .consumeWith(r -> log.info("{}", new String(Objects.requireNonNull(r.getResponseBody()))))
                .jsonPath("$.title").isEqualTo("Job - Not Found")
                .jsonPath("$.detail").isEqualTo("Job with id " + id + " not found")
                .jsonPath("$.instance").isEqualTo("/job/" + id);
    }

    @Test
    public void create_invalid_status_returns_bad_request() {

        JobDto dto1 = new JobDto();
        dto1.setStatus(null);
        dto1.setUrl("http://www.example.com/runner/1");

        this.client.post()
                .uri("/job")
                .bodyValue(dto1)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Job - Invalid Input")
                .jsonPath("$.detail").isEqualTo("Status is required")
                .jsonPath("$.instance").isEqualTo("/job");
    }

    @Test
    public void create_invalid_url_returns_bad_request() {

        JobDto dto1 = new JobDto();
        dto1.setStatus(JobStatus.DRAFT);
        dto1.setUrl(null);

        this.client.post()
                .uri("/job")
                .bodyValue(dto1)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Job - Invalid Input")
                .jsonPath("$.detail").isEqualTo("URL is required")
                .jsonPath("$.instance").isEqualTo("/job");
    }

    @Test
    public void delete_invalid_id_returns_not_found() {

        UUID id = UUID.randomUUID();

        this.client.delete()
                .uri("/job/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .consumeWith(r -> log.info("{}", new String(Objects.requireNonNull(r.getResponseBody()))))
                .jsonPath("$.title").isEqualTo("Job - Not Found")
                .jsonPath("$.detail").isEqualTo("Job with id " + id + " not found")
                .jsonPath("$.instance").isEqualTo("/job/" + id);
    }

}
