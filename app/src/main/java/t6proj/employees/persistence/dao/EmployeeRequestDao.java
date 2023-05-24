package t6proj.employees.persistence.dao;

import org.springframework.stereotype.Component;
import t6proj.employees.persistence.mapper.EmployeeRequestMapper;
import t6proj.employees.persistence.repository.EmployeeRequestRepository;

@Component
public class EmployeeRequestDao {
    private final EmployeeRequestRepository repository;
    public EmployeeRequestMapper mapper = EmployeeRequestMapper.INSTANCE;

    public EmployeeRequestDao(
            EmployeeRequestRepository repository
    ) {
        this.repository = repository;
    }
}
