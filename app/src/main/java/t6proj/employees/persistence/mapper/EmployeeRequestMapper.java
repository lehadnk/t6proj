package t6proj.employees.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeRequestMapper {
    EmployeeRequestMapper INSTANCE = Mappers.getMapper(EmployeeRequestMapper.class);
}
