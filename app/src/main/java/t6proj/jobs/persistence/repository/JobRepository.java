package t6proj.jobs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t6proj.jobs.persistence.entity.JobEntity;

import java.util.List;

public interface JobRepository extends JpaRepository<JobEntity, Integer> {
    @Query(value = "SELECT * FROM jobs LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<JobEntity> getJobsList(int limit, int offset);

    @Query("SELECT count(*) FROM JobEntity")
    Integer getJobsCount();

    @Query("SELECT j FROM JobEntity j WHERE j.id = :id")
    JobEntity getJobById(Integer id);
}
