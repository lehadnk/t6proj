package t6proj.jobs.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import t6proj.jobs.dto.Contract;
import t6proj.jobs.dto.Department;
import t6proj.jobs.persistence.entity.ContractEntity;
import t6proj.jobs.persistence.entity.DepartmentEntity;

@Mapper
public interface ContractMapper {
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    ContractEntity dtoToEntity(Contract dto);

    Contract entityToDto(ContractEntity departmentEntity);
}
