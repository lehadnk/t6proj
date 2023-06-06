package t6proj.employees;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import org.springframework.stereotype.Service;
import t6proj.employees.dto.Employee;
import t6proj.employees.dto.EmployeeDocument;
import t6proj.employees.dto.EmployeeRequest;
import t6proj.employees.persistence.dao.EmployeeDao;
import t6proj.employees.persistence.dao.EmployeeDocumentDao;
import t6proj.employees.persistence.dao.EmployeeRequestDao;

@Service
public class EmployeesService {
    private final EmployeeDao employeeDao;
    private final EmployeeDocumentDao employeeDocumentDao;
    private final EmployeeRequestDao employeeRequestDao;

    public EmployeesService(
            EmployeeDao employeeDao,
            EmployeeDocumentDao employeeDocumentDao,
            EmployeeRequestDao employeeRequestDao
    ) {
        this.employeeDao = employeeDao;
        this.employeeDocumentDao = employeeDocumentDao;
        this.employeeRequestDao = employeeRequestDao;
    }

    public Employee saveEmployee(Employee employee)
    {
        return this.employeeDao.saveEmployee(employee);
    }

    public EmployeeDocument saveEmployeeDocument(EmployeeDocument employeeDocument)
    {
        return this.employeeDocumentDao.saveEmployeeDocument(employeeDocument);
    }

    public PaginatedEntityListInterface<Employee> getEmployeesList(Integer page, int pageSize) {
        return this.employeeDao.getEmployeesList(page, pageSize);
    }

    public PaginatedEntityListInterface<EmployeeDocument> getEmployeeDocumentList(Integer employeeId, Integer page, int pageSize) {
        return this.employeeDocumentDao.getEmployeeDocumentsList(employeeId, page, pageSize);
    }

    public PaginatedEntityListInterface<EmployeeRequest> getEmployeeRequests(Integer page, int pageSize) {
        return this.employeeRequestDao.getEmployeeRequestList(page, pageSize);
    }

    public PaginatedEntityListInterface<EmployeeRequest> getEmployeeRequestsByEmployee(Integer employeeId, Integer page, int pageSize) {
        return this.employeeRequestDao.getEmployeeRequestListByEmployee(employeeId, page, pageSize);
    }

    public Employee getEmployeeById(Integer id) {
        return this.employeeDao.getEmployee(id);
    }

    public EmployeeRequest getEmployeeRequestById(Integer id) {
        return this.employeeRequestDao.getEmployeeRequestById(id);
    }

    public Integer getEmployeeRequestsCount() {
        return this.employeeRequestDao.getEmployeeRequestCount();
    }

    public EmployeeDocument getEmployeeDocumentById(Integer id) {
        return this.employeeDocumentDao.getEmployeeDocumentById(id);
    }
}
