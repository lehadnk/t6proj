package t6proj.employees.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t6proj.employees.persistence.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    @Query(value = "SELECT * FROM employees LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<EmployeeEntity> getEmployeeList(int limit, int offset);

    @Query("SELECT count(*) FROM EmployeeEntity")
    Integer getEmployeesCount();

    @Query("SELECT e FROM EmployeeEntity e WHERE id = :id")
    EmployeeEntity getEmployeeById(Integer id);
}
