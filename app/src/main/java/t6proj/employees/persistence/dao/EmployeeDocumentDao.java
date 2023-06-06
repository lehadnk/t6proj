package t6proj.employees.persistence.dao;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import t6proj.employees.dto.Employee;
import t6proj.employees.dto.EmployeeDocument;
import t6proj.employees.persistence.mapper.EmployeeDocumentMapper;
import t6proj.employees.persistence.repository.EmployeeDocumentRepository;
import t6proj.framework.dto.PaginatedEntityList;

import java.util.ArrayList;

@Component
public class EmployeeDocumentDao {
    private final EmployeeDocumentRepository repository;
    public EmployeeDocumentMapper mapper = EmployeeDocumentMapper.INSTANCE;

    public EmployeeDocumentDao(
            EmployeeDocumentRepository repository
    ) {
        this.repository = repository;
    }

    public EmployeeDocument saveEmployeeDocument(EmployeeDocument dto) {
        var entity = this.mapper.mapDtoToEntity(dto);
        this.repository.save(entity);
        dto.id = entity.id;
        return dto;
    }

    public PaginatedEntityListInterface<EmployeeDocument> getEmployeeDocumentsList(Integer employeeId, Integer page, int pageSize) {
        var pageable = PageRequest.of(page - 1, pageSize);
        var employeeDocumentEntityList = this.repository.getEmployeeDocumentsList(employeeId, pageable);
        var employeeDocumentsCount = this.repository.getEmployeeDocumentsCount(employeeId);

        var dtoList = new ArrayList<EmployeeDocument>(employeeDocumentEntityList.size());
        for(var employeeDocumentEntity : employeeDocumentEntityList) {
            dtoList.add(this.mapper.entityToDto(employeeDocumentEntity));
        }

        return new PaginatedEntityList<>(
                dtoList,
                page,
                (int) Math.ceil((double) employeeDocumentsCount / pageSize)
        );
    }

    public EmployeeDocument getEmployeeDocumentById(Integer id)
    {
        var entity = this.repository.getEmployeeDocumentById(id);
        return this.mapper.entityToDto(entity);
    }
}
