package t6proj.jobs.persistence.dao;

import org.springframework.stereotype.Component;
import t6proj.framework.dto.PaginatedEntityList;
import t6proj.jobs.dto.Department;
import t6proj.jobs.persistence.mapper.DepartmentMapper;
import t6proj.jobs.persistence.repository.DepartmentHibernateRepository;
import t6proj.jobs.persistence.repository.DepartmentJpaRepository;

import java.util.ArrayList;
import java.util.List;

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
        this.hibernateRepository.save(entity);

        dto.id = entity.id;
        return dto;
    }

    public PaginatedEntityList<Department> getDepartmentList(int page, int pageSize) {
        var departmentList = this.jpaRepository.getDepartmentList(pageSize, (page - 1) * pageSize);
        var departmentsCount = this.hibernateRepository.getDepartmentsCount();

        return new PaginatedEntityList<>(
                departmentList,
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

    public List<Department> getAllDepartments() {
        var departmentEntities = this.hibernateRepository.getAllDepartments();

        var departmentList = new ArrayList<Department>(departmentEntities.size());
        for(var departmentEntity : departmentEntities) {
            departmentList.add(this.mapper.entityToDto(departmentEntity));
        }

        return departmentList;
    }
}
