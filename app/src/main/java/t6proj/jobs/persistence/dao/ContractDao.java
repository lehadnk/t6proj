package t6proj.jobs.persistence.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import t6proj.framework.dto.PaginatedEntityList;
import t6proj.jobs.dto.Contract;
import t6proj.jobs.persistence.mapper.ContractMapper;
import t6proj.jobs.persistence.repository.ContractRepository;

import java.util.ArrayList;

@Component
public class ContractDao {
    private final ContractRepository repository;
    private final ContractMapper mapper = ContractMapper.INSTANCE;

    public ContractDao(
            ContractRepository contractRepository
    ) {
        this.repository = contractRepository;
    }

    public Contract saveContract(Contract contract)
    {
        var entity = this.mapper.dtoToEntity(contract);
        entity.job.id = contract.jobId;
        this.repository.save(entity);

        contract.id = entity.id;
        return contract;
    }

    public PaginatedEntityList<Contract> getEmployeeContractList(int employeeId, int page, int pageSize) {
        var pageable = PageRequest.of(page - 1, pageSize);
        var contractEntityList = this.repository.getEmployeeContractList(employeeId, pageable);
        var contractsCount = this.repository.getEmployeeContractCount();

        var dtoList = new ArrayList<Contract>(contractEntityList.size());
        for(var contractEntity : contractEntityList) {
            var dto = this.mapper.entityToDto(contractEntity);
            dto.jobTitle = contractEntity.job.title;
            dtoList.add(dto);
        }

        return new PaginatedEntityList<>(
                dtoList,
                page,
                (int) Math.ceil((double) contractsCount / pageSize)
        );
    }

    public Contract getContractById(Integer id)
    {
        var entity = this.repository.getContractById(id);
        return this.mapper.entityToDto(entity);
    }
}
