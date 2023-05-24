package t6proj.employees.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t6proj.employees.persistence.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
}
