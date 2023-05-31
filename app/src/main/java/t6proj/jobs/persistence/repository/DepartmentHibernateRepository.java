package t6proj.jobs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t6proj.jobs.persistence.entity.DepartmentEntity;


public interface DepartmentHibernateRepository extends JpaRepository<DepartmentEntity, Integer> {
    @Query("SELECT count(*) FROM DepartmentEntity")
    Integer getDepartmentsCount();

    @Query("SELECT d FROM DepartmentEntity d WHERE d.id = :id")
    DepartmentEntity getDepartmentById(Integer id);
}
