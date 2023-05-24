package t6proj.employees.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import t6proj.employees.dto.EmployeeDocument;
import t6proj.employees.persistence.entity.EmployeeDocumentEntity;

@Mapper
public interface EmployeeDocumentMapper {
    EmployeeDocumentMapper INSTANCE = Mappers.getMapper(EmployeeDocumentMapper.class);

    EmployeeDocumentEntity mapDtoToEntity(EmployeeDocument dto);
}
