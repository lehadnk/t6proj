package t6proj.employees.persistence.dao;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import t6proj.employees.dto.EmployeeRequest;
import t6proj.employees.persistence.mapper.EmployeeRequestMapper;
import t6proj.employees.persistence.repository.EmployeeRequestHibernateRepository;
import t6proj.employees.persistence.repository.EmployeeRequestJpaRepository;
import t6proj.framework.dto.PaginatedEntityList;

import java.util.ArrayList;

@Component
public class EmployeeRequestDao {
    private final EmployeeRequestHibernateRepository hibernateRepository;
    private final EmployeeRequestJpaRepository jpaRepository;
    public EmployeeRequestMapper mapper = EmployeeRequestMapper.INSTANCE;

    public EmployeeRequestDao(
            EmployeeRequestHibernateRepository hibernateRepository,
            EmployeeRequestJpaRepository jpaRepository
    ) {
        this.hibernateRepository = hibernateRepository;
        this.jpaRepository = jpaRepository;
    }

    public PaginatedEntityListInterface<EmployeeRequest> getEmployeeRequestList(Integer page, int pageSize) {
        var offset = (page - 1) * pageSize;
        var employeeRequestsList = this.jpaRepository.getEmployeeRequestList(pageSize, offset);
        var employeeRequestsCount = this.hibernateRepository.getEmployeeRequestsCount();

        return new PaginatedEntityList<>(
                employeeRequestsList,
                page,
                (int) Math.ceil((double) employeeRequestsCount / pageSize)
        );
    }

    public EmployeeRequest getEmployeeRequestById(Integer id)
    {
        var employeeRequestEntity = this.hibernateRepository.getEmployeeRequestsById(id);
        return this.mapper.entityToDto(employeeRequestEntity);
    }

    public Integer getEmployeeRequestCount() {
        return this.hibernateRepository.getEmployeeRequestsCount();
    }
}
