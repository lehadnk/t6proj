package t6proj.employees.persistence.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t6proj.employees.persistence.entity.EmployeeDocumentEntity;

import java.util.List;

public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocumentEntity, Integer> {
    @Query(value = "SELECT e FROM EmployeeDocumentEntity e WHERE e.employeeId = :employeeId")
    List<EmployeeDocumentEntity> getEmployeeDocumentsList(Integer employeeId, Pageable pageable);

    @Query(value = "SELECT count(*) FROM EmployeeDocumentEntity e WHERE e.employeeId = :employeeId")
    Integer getEmployeeDocumentsCount(Integer employeeId);

    @Query("SELECT e FROM EmployeeDocumentEntity e WHERE e.id = :id")
    EmployeeDocumentEntity getEmployeeDocumentById(Integer id);
}
