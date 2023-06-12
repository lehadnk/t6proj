package t6proj.employees.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import t6proj.employees.dto.Employee;
import t6proj.employees.persistence.entity.EmployeeEntity;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeEntity mapDtoToEntity(Employee dto);

    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "departmentTitle", ignore = true)
    Employee entityToDto(EmployeeEntity employeeEntity);
}
