package org.mrpaulwoods.processor.entity;

import org.mrpaulwoods.processor.JobStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class Job {

    @Id
    private Long id;

    @Column
    private String url;

    @Column
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
        return "Job{" +
               "id=" + id +
               ", status=" + status +
               ", url='" + url + '\'' +
               '}';
    }

}
