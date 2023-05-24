package t6proj.jobs.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import t6proj.jobs.dto.Job;
import t6proj.jobs.persistence.entity.JobEntity;

@Mapper
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    JobEntity dtoToEntity(Job dto);

    Job entityToDto(JobEntity departmentEntity);
}
