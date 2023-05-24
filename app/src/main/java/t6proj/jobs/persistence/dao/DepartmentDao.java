package t6proj.jobs.persistence.dao;

import org.springframework.stereotype.Component;
import t6proj.framework.dto.PaginatedEntityList;
import t6proj.jobs.dto.Department;
import t6proj.jobs.persistence.entity.DepartmentEntity;
import t6proj.jobs.persistence.mapper.DepartmentMapper;
import t6proj.jobs.persistence.repository.DepartmentRepository;

import java.util.ArrayList;

@Component
public class DepartmentDao {
    private final DepartmentRepository repository;
    public DepartmentMapper mapper = DepartmentMapper.INSTANCE;

    public DepartmentDao(
            DepartmentRepository repository
    ) {
        this.repository = repository;
    }

    public Department saveDepartment(Department dto) {
        var entity = this.mapper.dtoToEntity(dto);
        this.repository.save(entity);

        dto.id = entity.id;
        return dto;
    }

    public PaginatedEntityList<Department> getDepartmentList(int page, int pageSize) {
        var offset = (page - 1) * pageSize;
        var departmentEntityList = this.repository.getDepartmentList(pageSize, offset);
        var departmentsCount = this.repository.getDepartmentsCount();

        var dtoList = new ArrayList<Department>(departmentEntityList.size());
        for(var departmentEntity : departmentEntityList) {
            dtoList.add(this.mapper.entityToDto(departmentEntity));
        }

        return new PaginatedEntityList<>(
                dtoList,
                page,
                (int) Math.ceil((double) departmentsCount / pageSize)
        );
    }

    public Department getDepartmentById(Integer id) {
        var entity = this.repository.getDepartmentById(id);
        return this.mapper.entityToDto(entity);
    }
}
