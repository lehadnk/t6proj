package t6proj.employees;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import org.springframework.stereotype.Service;
import t6proj.employees.dto.Employee;
import t6proj.employees.dto.EmployeeDocument;
import t6proj.employees.persistence.dao.EmployeeDao;
import t6proj.employees.persistence.dao.EmployeeDocumentDao;

@Service
public class EmployeesService {
    private final EmployeeDao employeeDao;
    private final EmployeeDocumentDao employeeDocumentDao;

    public EmployeesService(
            EmployeeDao employeeDao,
            EmployeeDocumentDao employeeDocumentDao
    ) {
        this.employeeDao = employeeDao;
        this.employeeDocumentDao = employeeDocumentDao;
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

    public PaginatedEntityListInterface<EmployeeDocument> getEmployeeDocumentList(Integer page, int pageSize) {
        return null;
    }

    public PaginatedEntityListInterface<Employee> getEmployeeRequests(Integer page, int pageSize) {
        return null;
    }

    public PaginatedEntityListInterface<Employee> getEmployeeRequestsByEmployee(Integer employeeId, Integer page, int pageSize) {
        return null;
    }
}
