package t6proj.employees.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t6proj.employees.persistence.entity.EmployeeRequestEntity;

public interface EmployeeRequestRepository extends JpaRepository<EmployeeRequestEntity, Integer> {
}
