package t6proj.jobs;

import org.springframework.stereotype.Service;
import t6proj.framework.dto.PaginatedEntityList;
import t6proj.jobs.business.DepartmentTreeFactory;
import t6proj.jobs.dto.*;
import t6proj.jobs.persistence.dao.ContractDao;
import t6proj.jobs.persistence.dao.DepartmentDao;
import t6proj.jobs.persistence.dao.JobDao;

import java.util.List;

@Service
public class JobsService {
    private final DepartmentDao departmentDao;
    private final JobDao jobDao;
    private final ContractDao contractDao;
    private final DepartmentTreeFactory departmentTreeFactory;

    public JobsService(
            DepartmentDao departmentDao,
            JobDao jobDao,
            ContractDao contractDao,
            DepartmentTreeFactory departmentTreeFactory
    ) {
        this.departmentDao = departmentDao;
        this.jobDao = jobDao;
        this.contractDao = contractDao;
        this.departmentTreeFactory = departmentTreeFactory;
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

    public PaginatedEntityList<EmployeeContract> getEmployeeContracts(Integer employeeId, Integer page, Integer pageSize)
    {
        return this.contractDao.getEmployeeContractList(employeeId, page, pageSize);
    }

    public Contract saveContract(Contract contract)
    {
        return this.contractDao.saveContract(contract);
    }

    public Boolean isChildToDepartment(Integer departmentId, Integer possibleChildId)
    {
        return this.departmentDao.isChildToDepartment(departmentId, possibleChildId);
    }

    public List<DepartmentTreeNode> getDepartmentTree()
    {
        return this.departmentTreeFactory.build();
    }
}
