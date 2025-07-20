package org.mrpaulwoods.processor.dto;

import org.mrpaulwoods.processor.JobStatus;

public class JobDto {

    private Long id;
    private String url;
    private JobStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
