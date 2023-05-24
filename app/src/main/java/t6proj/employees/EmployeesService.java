package t6proj.employees;

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
}
