package t6proj.employees.persistence.dao;

import org.springframework.stereotype.Component;
import t6proj.employees.dto.EmployeeDocument;
import t6proj.employees.persistence.mapper.EmployeeDocumentMapper;
import t6proj.employees.persistence.repository.EmployeeDocumentRepository;

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
}
