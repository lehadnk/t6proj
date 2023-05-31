package t6proj.jobs.persistence.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import t6proj.framework.dto.PaginatedEntityList;
import t6proj.jobs.dto.Department;
import t6proj.jobs.persistence.mapper.DepartmentMapper;
import t6proj.jobs.persistence.repository.DepartmentHibernateRepository;
import t6proj.jobs.persistence.repository.DepartmentJpaRepository;

import java.util.ArrayList;

@Component
public class DepartmentDao {
    private final DepartmentHibernateRepository hibernateRepository;
    private final DepartmentJpaRepository jpaRepository;
    public DepartmentMapper mapper = DepartmentMapper.INSTANCE;

    public DepartmentDao(
            DepartmentHibernateRepository hibernateRepository,
            DepartmentJpaRepository jpaRepository
    ) {
        this.hibernateRepository = hibernateRepository;
        this.jpaRepository = jpaRepository;
    }

    public Department saveDepartment(Department dto) {
        var entity = this.mapper.dtoToEntity(dto);
        if (dto.parentDepartmentId != null) {
            entity.parentDepartment = this.hibernateRepository.getReferenceById(dto.parentDepartmentId);
        }

        this.hibernateRepository.save(entity);

        dto.id = entity.id;
        return dto;
    }

    public PaginatedEntityList<Department> getDepartmentList(int page, int pageSize) {
        var pageable = PageRequest.of(page - 1, pageSize);
        var departmentEntityList = this.hibernateRepository.getDepartmentList(pageable);
        var departmentsCount = this.hibernateRepository.getDepartmentsCount();

        var dtoList = new ArrayList<Department>(departmentEntityList.size());
        for(var departmentEntity : departmentEntityList) {
            var dto = this.mapper.entityToDto(departmentEntity);
            dto.parentDepartmentTitle = departmentEntity.parentDepartment != null ? departmentEntity.parentDepartment.title : null;
            dtoList.add(dto);
        }

        return new PaginatedEntityList<>(
                dtoList,
                page,
                (int) Math.ceil((double) departmentsCount / pageSize)
        );
    }

    public Department getDepartmentById(Integer id) {
        var entity = this.hibernateRepository.getDepartmentById(id);
        return this.mapper.entityToDto(entity);
    }

    public boolean isChildToDepartment(Integer departmentId, Integer possibleChildId)
    {
        return this.jpaRepository.isChildToDepartment(departmentId, possibleChildId);
    }
}
