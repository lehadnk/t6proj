package t6proj.jobs.persistence.dao;

import org.springframework.stereotype.Component;
import t6proj.framework.dto.PaginatedEntityList;
import t6proj.jobs.dto.Job;
import t6proj.jobs.persistence.mapper.JobMapper;
import t6proj.jobs.persistence.repository.JobHibernateRepository;
import t6proj.jobs.persistence.repository.JobJpaRepository;

import java.util.ArrayList;

@Component
public class JobDao {
    private final JobHibernateRepository hibernateRepository;
    private final JobJpaRepository jpaRepository;
    public JobMapper mapper = JobMapper.INSTANCE;

    public JobDao(
            JobHibernateRepository hibernateRepository,
            JobJpaRepository jpaRepository
    ) {
        this.hibernateRepository = hibernateRepository;
        this.jpaRepository = jpaRepository;
    }

    public Job saveJob(Job dto) {
        var entity = this.mapper.dtoToEntity(dto);
        this.hibernateRepository.save(entity);

        dto.id = entity.id;
        return dto;
    }

    public PaginatedEntityList<Job> getJobsList(int page, int pageSize) {
        var jobList = this.jpaRepository.getJobList(pageSize, (page - 1) * pageSize);
        var departmentsCount = this.hibernateRepository.getJobsCount();

        return new PaginatedEntityList<>(
                jobList,
                page,
                (int) Math.ceil((double) departmentsCount / pageSize)
        );
    }

    public Job getJobById(Integer id) {
        var entity = this.hibernateRepository.getJobById(id);
        return this.mapper.entityToDto(entity);
    }
}
