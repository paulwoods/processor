package org.mrpaulwoods.processor.dto;

import java.util.UUID;

public class JobDto {

    private Long id;
    private String url;

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

    @Override
    public String toString() {
        return "JobDto{" +
               "id=" + id +
               ", url='" + url + '\'' +
               '}';
    }
}
