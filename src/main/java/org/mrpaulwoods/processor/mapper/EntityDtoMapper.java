package org.mrpaulwoods.processor.mapper;

import org.mrpaulwoods.processor.dto.JobDto;
import org.mrpaulwoods.processor.entity.Job;

public class EntityDtoMapper {

    public static JobDto toDto(Job entity) {
        JobDto dto = new JobDto();
        dto.setId(entity.getId());
        dto.setUrl(entity.getUrl());
        return dto;
    }

    public static Job toEntity(JobDto dto) {
        Job job = new Job();
        job.setId(dto.getId());
        job.setUrl(dto.getUrl());
        return job;
    }

}
