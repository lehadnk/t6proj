package t6proj.jobs;

import org.springframework.stereotype.Service;
import t6proj.framework.dto.PaginatedEntityList;
import t6proj.jobs.dto.Department;
import t6proj.jobs.dto.Job;
import t6proj.jobs.persistence.dao.DepartmentDao;
import t6proj.jobs.persistence.dao.JobDao;

@Service
public class JobsService {
    private final DepartmentDao departmentDao;
    private final JobDao jobDao;

    public JobsService(
            DepartmentDao departmentDao,
            JobDao jobDao
    ) {
        this.departmentDao = departmentDao;
        this.jobDao = jobDao;
    }

    public Department saveDepartment(Department department)
    {
        return this.departmentDao.saveDepartment(department);
    }

    public Job saveJob(Job job)
    {
        return this.jobDao.saveJob(job);
    }

    public PaginatedEntityList<Department> getDepartmentList(int page, int pageSize)
    {
        return this.departmentDao.getDepartmentList(page, pageSize);
    }

    public PaginatedEntityList<Job> getJobList(int page, int pageSize)
    {
        return this.jobDao.getJobsList(page, pageSize);
    }

    public Department getDepartmentById(Integer id)
    {
        return this.departmentDao.getDepartmentById(id);
    }
}
