package t6proj.jobs;

import org.springframework.stereotype.Service;
import t6proj.framework.dto.PaginatedEntityList;
import t6proj.jobs.dto.Contract;
import t6proj.jobs.dto.Department;
import t6proj.jobs.dto.Job;
import t6proj.jobs.persistence.dao.ContractDao;
import t6proj.jobs.persistence.dao.DepartmentDao;
import t6proj.jobs.persistence.dao.JobDao;

@Service
public class JobsService {
    private final DepartmentDao departmentDao;
    private final JobDao jobDao;
    private final ContractDao contractDao;

    public JobsService(
            DepartmentDao departmentDao,
            JobDao jobDao,
            ContractDao contractDao
    ) {
        this.departmentDao = departmentDao;
        this.jobDao = jobDao;
        this.contractDao = contractDao;
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

    public Job getJobById(Integer id) {
        return this.jobDao.getJobById(id);
    }

    public Contract getContractById(Integer id)
    {
        return this.contractDao.getContractById(id);
    }

    public PaginatedEntityList<Contract> getEmployeeContracts(Integer employeeId, Integer page, Integer pageSize)
    {
        return this.contractDao.getEmployeeContractList(employeeId, page, pageSize);
    }

    public Contract saveContract(Contract contract)
    {
        return this.contractDao.saveContract(contract);
    }
}
