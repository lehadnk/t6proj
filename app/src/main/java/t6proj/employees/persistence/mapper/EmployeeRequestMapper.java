package t6proj.employees.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import t6proj.employees.dto.EmployeeRequest;
import t6proj.employees.persistence.entity.EmployeeRequestEntity;

@Mapper
public interface EmployeeRequestMapper {
    EmployeeRequestMapper INSTANCE = Mappers.getMapper(EmployeeRequestMapper.class);

    EmployeeRequest entityToDto(EmployeeRequestEntity employeeDocumentEntity);
}
