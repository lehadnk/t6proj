package t6proj.jobs.persistence.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t6proj.jobs.persistence.entity.ContractEntity;
import java.util.List;


public interface ContractRepository extends JpaRepository<ContractEntity, Integer> {
    @Query("SELECT c FROM ContractEntity c WHERE c.employeeId = :employeeId")
    List<ContractEntity> getEmployeeContractList(int employeeId, Pageable pageable);

    @Query("SELECT count(*) FROM ContractEntity")
    Integer getEmployeeContractCount();

    @Query("SELECT c FROM ContractEntity c WHERE c.id = :id")
    ContractEntity getContractById(Integer id);
}
