package org.mrpaulwoods.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mrpaulwoods.processor.dto.JobDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;

@SpringBootTest
@AutoConfigureWebTestClient
class ProcessorApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(ProcessorApplicationTests.class);

    @Autowired
    private WebTestClient client;

    @Test
    public void job_create_get_delete() {

        var dto = new JobDto();
        dto.setUrl("http://www.example.com/runner/1");

        JobDto created = this.client.post()
                .uri("/job")
                .bodyValue(dto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JobDto.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(created.getId());

        this.client.get()
                .uri("/job", Map.of("page", 0, "size", 10))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(JobDto.class)
                .hasSize(1);
    }

}
