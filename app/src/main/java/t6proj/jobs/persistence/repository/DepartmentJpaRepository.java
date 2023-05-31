package t6proj.jobs.persistence.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import t6proj.jobs.dto.Department;

import java.util.List;

@Component
public class DepartmentJpaRepository {
    private final JdbcTemplate jdbcTemplate;

    public DepartmentJpaRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Department> getDepartmentList(int limit, int offset)
    {
        var query = """
            SELECT 
                d.id,
                d.title,
                d.parent_department_id,
                dp.title
            FROM departments d
            LEFT JOIN departments dp ON d.parent_department_id = dp.id
            ORDER BY d.id DESC
            LIMIT ?
            OFFSET ?
        """;

        return this.jdbcTemplate.query(query, (rs, i) -> {
            var department = new Department();
            department.id = rs.getInt(1);
            department.title = rs.getString(2);
            department.parentDepartmentId = rs.getInt(3);
            department.parentDepartmentTitle = rs.getString(4);

            return department;
        }, limit, offset);
    }

    public Boolean isChildToDepartment(int departmentId, int possibleChildId)
    {
        var query = """
            WITH RECURSIVE tree AS
                (
                    SELECT id
                    FROM departments d
                    WHERE d.id IN (?)
            
                    UNION ALL
            
                    SELECT departments.id
                    FROM departments, tree
                    WHERE tree.id = departments.parent_department_id
                )
            
             SELECT count(*)
             FROM tree
             WHERE id = ?;
        """;

        var resultSet = this.jdbcTemplate.query(query, (rs, i) -> {
            var matches = rs.getInt(1);
            if (matches > 0) {
                return true;
            }

            return false;
        }, departmentId, possibleChildId);

        return resultSet.get(0);
    }
}
