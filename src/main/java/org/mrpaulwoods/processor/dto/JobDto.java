package org.mrpaulwoods.processor.dto;

import org.mrpaulwoods.processor.JobStatus;

import java.util.UUID;

public class JobDto {

    private UUID id;
    private String url;
    private JobStatus status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "JobDto{" +
               "id=" + id +
               ", status=" + status +
               ", url='" + url + '\'' +
               '}';
    }
}
