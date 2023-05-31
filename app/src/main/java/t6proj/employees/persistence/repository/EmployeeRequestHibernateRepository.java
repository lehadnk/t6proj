package t6proj.employees.persistence.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t6proj.employees.persistence.entity.EmployeeRequestEntity;

import java.util.List;

public interface EmployeeRequestHibernateRepository extends JpaRepository<EmployeeRequestEntity, Integer> {
    @Query(value = "SELECT e FROM EmployeeRequestEntity e WHERE e.employeeId = :employeeId")
    List<EmployeeRequestEntity> getEmployeeRequestListByEmployee(Integer employeeId, Pageable pageable);

    @Query("SELECT count(*) FROM EmployeeRequestEntity r WHERE r.employeeId = :employeeId")
    Integer getEmployeeRequestsCountByEmployee(Integer employeeId);

    @Query("SELECT count(*) FROM EmployeeRequestEntity r")
    Integer getEmployeeRequestsCount();

    @Query("SELECT e FROM EmployeeRequestEntity e WHERE e.id = :id")
    EmployeeRequestEntity getEmployeeRequestsById(Integer id);
}
