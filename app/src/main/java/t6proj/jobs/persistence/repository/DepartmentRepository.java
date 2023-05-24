package t6proj.jobs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t6proj.jobs.dto.Department;
import t6proj.jobs.persistence.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
    @Query(value = "SELECT * FROM departments d LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<DepartmentEntity> getDepartmentList(int limit, int offset);

    @Query("SELECT count(*) FROM DepartmentEntity")
    Integer getDepartmentsCount();

    @Query("SELECT d FROM DepartmentEntity d WHERE d.id = :id")
    DepartmentEntity getDepartmentById(Integer id);
}
