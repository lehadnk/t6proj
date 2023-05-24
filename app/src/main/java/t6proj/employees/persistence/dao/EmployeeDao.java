package t6proj.employees.persistence.dao;

import org.springframework.stereotype.Component;
import t6proj.employees.dto.Employee;
import t6proj.employees.persistence.mapper.EmployeeMapper;
import t6proj.employees.persistence.repository.EmployeeRepository;

@Component
public class EmployeeDao {
    private final EmployeeRepository repository;
    public EmployeeMapper mapper = EmployeeMapper.INSTANCE;

    public EmployeeDao(
            EmployeeRepository repository
    ) {
        this.repository = repository;
    }

    public Employee saveEmployee(Employee dto) {
        var entity = this.mapper.mapDtoToEntity(dto);
        this.repository.save(entity);
        dto.id = entity.id;
        return dto;
    }
}
