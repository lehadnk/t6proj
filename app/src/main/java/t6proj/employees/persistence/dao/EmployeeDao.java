package t6proj.employees.persistence.dao;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import org.springframework.stereotype.Component;
import t6proj.employees.dto.Employee;
import t6proj.employees.persistence.mapper.EmployeeMapper;
import t6proj.employees.persistence.repository.EmployeeRepository;
import t6proj.framework.dto.PaginatedEntityList;

import java.util.ArrayList;

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

    public PaginatedEntityListInterface<Employee> getEmployeesList(Integer page, int pageSize) {
        var offset = (page - 1) * pageSize;
        var employeeEntityList = this.repository.getEmployeeList(pageSize, offset);
        var departmentsCount = this.repository.getEmployeesCount();

        var dtoList = new ArrayList<Employee>(employeeEntityList.size());
        for(var employeeEntity : employeeEntityList) {
            dtoList.add(this.mapper.entityToDto(employeeEntity));
        }

        return new PaginatedEntityList<>(
                dtoList,
                page,
                (int) Math.ceil(departmentsCount / pageSize)
        );
    }

    public Employee getEmployee(Integer id)
    {
        var employeeEntity = this.repository.getEmployeeById(id);
        return this.mapper.entityToDto(employeeEntity);
    }
}
