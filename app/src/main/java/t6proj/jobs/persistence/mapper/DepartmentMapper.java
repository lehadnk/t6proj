package t6proj.jobs.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import t6proj.jobs.dto.Department;
import t6proj.jobs.persistence.entity.DepartmentEntity;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentEntity dtoToEntity(Department dto);

    Department entityToDto(DepartmentEntity departmentEntity);
}
