package t6proj.employees.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t6proj.employees.persistence.entity.EmployeeDocumentEntity;

import java.util.List;

public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocumentEntity, Integer> {
    @Query(value = "SELECT * FROM employee_documents WHERE employee_id = :employeeId LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<EmployeeDocumentEntity> getEmployeeDocumentsList(Integer employeeId, int limit, int offset);

    @Query(value = "SELECT count(*) FROM employee_documents WHERE employee_id = :employeeId", nativeQuery = true)
    Integer getEmployeeDocumentsCount(Integer employeeId);

    @Query("SELECT e FROM EmployeeEntity e WHERE e.id = :id")
    EmployeeDocumentEntity getEmployeeDocumentById(Integer id);
}
