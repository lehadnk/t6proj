package t6proj.employees.persistence.dao;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import org.springframework.stereotype.Component;
import t6proj.employees.dto.EmployeeDocument;
import t6proj.employees.dto.EmployeeRequest;
import t6proj.employees.persistence.mapper.EmployeeRequestMapper;
import t6proj.employees.persistence.repository.EmployeeRequestRepository;
import t6proj.framework.dto.PaginatedEntityList;

import java.util.ArrayList;

@Component
public class EmployeeRequestDao {
    private final EmployeeRequestRepository repository;
    public EmployeeRequestMapper mapper = EmployeeRequestMapper.INSTANCE;

    public EmployeeRequestDao(
            EmployeeRequestRepository repository
    ) {
        this.repository = repository;
    }

    public PaginatedEntityListInterface<EmployeeRequest> getEmployeeRequestListByEmployee(Integer employeeId, Integer page, int pageSize) {
        var offset = (page - 1) * pageSize;
        var employeeDocumentEntityList = this.repository.getEmployeeRequestListByEmployee(employeeId, pageSize, offset);
        var employeeDocumentsCount = this.repository.getEmployeeRequestsCountByEmployee(employeeId);

        var dtoList = new ArrayList<EmployeeRequest>(employeeDocumentEntityList.size());
        for(var employeeDocumentEntity : employeeDocumentEntityList) {
            dtoList.add(this.mapper.entityToDto(employeeDocumentEntity));
        }

        return new PaginatedEntityList<>(
                dtoList,
                page,
                (int) Math.ceil((double) employeeDocumentsCount / pageSize)
        );
    }

    public PaginatedEntityListInterface<EmployeeRequest> getEmployeeRequestList(Integer page, int pageSize) {
        var offset = (page - 1) * pageSize;
        var employeeDocumentEntityList = this.repository.getEmployeeRequestList(pageSize, offset);
        var employeeDocumentsCount = this.repository.getEmployeeRequestsCount();

        var dtoList = new ArrayList<EmployeeRequest>(employeeDocumentEntityList.size());
        for(var employeeDocumentEntity : employeeDocumentEntityList) {
            dtoList.add(this.mapper.entityToDto(employeeDocumentEntity));
        }

        return new PaginatedEntityList<>(
                dtoList,
                page,
                (int) Math.ceil((double) employeeDocumentsCount / pageSize)
        );
    }

    public EmployeeRequest getEmployeeRequestById(Integer id)
    {
        var employeeRequestEntity = this.repository.getEmployeeRequestsById(id);
        return this.mapper.entityToDto(employeeRequestEntity);
    }

    public Integer getEmployeeRequestCount() {
        return this.repository.getEmployeeRequestsCount();
    }
}
