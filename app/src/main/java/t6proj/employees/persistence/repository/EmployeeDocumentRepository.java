package t6proj.employees.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t6proj.employees.persistence.entity.EmployeeDocumentEntity;

public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocumentEntity, Integer> {
}
