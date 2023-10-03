package t6proj.jobs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t6proj.jobs.persistence.entity.ContractEntity;


public interface ContractHibernateRepository extends JpaRepository<ContractEntity, Integer> {
    @Query("SELECT count(*) FROM ContractEntity c WHERE c.employeeId = :employeeId")
    Integer getEmployeeContractCount(int employeeId);

    @Query("SELECT c FROM ContractEntity c WHERE c.id = :id")
    ContractEntity getContractById(Integer id);
}
