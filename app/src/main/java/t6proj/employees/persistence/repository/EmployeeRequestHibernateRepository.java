package t6proj.employees.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t6proj.employees.persistence.entity.EmployeeRequestEntity;

import java.util.List;

public interface EmployeeRequestHibernateRepository extends JpaRepository<EmployeeRequestEntity, Integer> {
    @Query(value = "SELECT * FROM employee_requests WHERE employee_id = :employeeId LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<EmployeeRequestEntity> getEmployeeRequestListByEmployee(Integer employeeId, int limit, int offset);

    @Query("SELECT count(*) FROM EmployeeRequestEntity r WHERE r.employeeId = :employeeId")
    Integer getEmployeeRequestsCountByEmployee(Integer employeeId);

    @Query("SELECT count(*) FROM EmployeeRequestEntity r")
    Integer getEmployeeRequestsCount();

    @Query("SELECT r FROM EmployeeRequestEntity r WHERE r.id = :id")
    EmployeeRequestEntity getEmployeeRequestsById(Integer id);
}
