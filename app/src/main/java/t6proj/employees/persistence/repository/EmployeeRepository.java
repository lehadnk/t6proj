package t6proj.employees.persistence.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t6proj.employees.persistence.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    @Query(value = "SELECT e FROM EmployeeEntity e WHERE e.isDeleted = false")
    List<EmployeeEntity> getEmployeeList(Pageable pageable);

    @Query("SELECT count(*) FROM EmployeeEntity e WHERE e.isDeleted = false")
    Integer getEmployeesCount();

    @Query("SELECT e FROM EmployeeEntity e WHERE e.id = :id AND e.isDeleted = false")
    EmployeeEntity getEmployeeById(Integer id);
}
