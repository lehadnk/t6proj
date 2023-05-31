package t6proj.jobs.persistence.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DepartmentJpaRepository {
    private final JdbcTemplate jdbcTemplate;

    public DepartmentJpaRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
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
