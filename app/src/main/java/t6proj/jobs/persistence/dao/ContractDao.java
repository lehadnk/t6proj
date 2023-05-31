package t6proj.jobs.persistence.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import t6proj.framework.dto.PaginatedEntityList;
import t6proj.jobs.dto.Contract;
import t6proj.jobs.dto.EmployeeContract;
import t6proj.jobs.persistence.mapper.ContractMapper;
import t6proj.jobs.persistence.repository.ContractHibernateRepository;
import t6proj.jobs.persistence.repository.ContractJpaRepository;

import java.util.ArrayList;

@Component
public class ContractDao {
    private final ContractHibernateRepository hibernateRepository;
    private final ContractJpaRepository jpaRepository;
    private final ContractMapper mapper = ContractMapper.INSTANCE;

    public ContractDao(
            ContractHibernateRepository contractHibernateRepository,
            ContractJpaRepository jpaRepository
    ) {
        this.hibernateRepository = contractHibernateRepository;
        this.jpaRepository = jpaRepository;
    }

    public Contract saveContract(Contract contract)
    {
        var entity = this.mapper.dtoToEntity(contract);
        this.hibernateRepository.save(entity);

        contract.id = entity.id;
        return contract;
    }

    public PaginatedEntityList<EmployeeContract> getEmployeeContractList(int employeeId, int page, int pageSize) {
        var employeeContractList = this.jpaRepository.getEmployeeContracts(employeeId, pageSize, (page - 1) * pageSize);
        var employeeContractsCount = this.hibernateRepository.getEmployeeContractCount(employeeId);

        return new PaginatedEntityList<>(
                employeeContractList,
                page,
                (int) Math.ceil((double) employeeContractsCount / pageSize)
        );
    }

    public Contract getContractById(Integer id)
    {
        var entity = this.hibernateRepository.getContractById(id);
        return this.mapper.entityToDto(entity);
    }
}
