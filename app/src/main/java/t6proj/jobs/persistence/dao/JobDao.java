package t6proj.jobs.persistence.dao;

import org.springframework.stereotype.Component;
import t6proj.framework.dto.PaginatedEntityList;
import t6proj.jobs.dto.Job;
import t6proj.jobs.persistence.mapper.JobMapper;
import t6proj.jobs.persistence.repository.JobRepository;

import java.util.ArrayList;

@Component
public class JobDao {
    private final JobRepository repository;
    public JobMapper mapper = JobMapper.INSTANCE;

    public JobDao(
            JobRepository repository
    ) {
        this.repository = repository;
    }

    public Job saveJob(Job dto) {
        var entity = this.mapper.dtoToEntity(dto);
        this.repository.save(entity);

        dto.id = entity.id;
        return dto;
    }

    public PaginatedEntityList<Job> getJobsList(int page, int pageSize) {
        var offset = (page - 1) * pageSize;
        var departmentEntityList = this.repository.getJobsList(pageSize, offset);
        var departmentsCount = this.repository.getJobsCount();

        var dtoList = new ArrayList<Job>(departmentEntityList.size());
        for(var departmentEntity : departmentEntityList) {
            dtoList.add(this.mapper.entityToDto(departmentEntity));
        }

        return new PaginatedEntityList<>(
                dtoList,
                page,
                (int) Math.ceil((double) departmentsCount / pageSize)
        );
    }

    public Job getJobById(Integer id) {
        var entity = this.repository.getJobById(id);
        return this.mapper.entityToDto(entity);
    }
}
