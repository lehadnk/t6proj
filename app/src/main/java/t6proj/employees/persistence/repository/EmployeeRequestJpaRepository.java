package t6proj.employees.persistence.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import t6proj.employees.dto.EmployeeRequest;
import t6proj.employees.dto.EmployeeRequestStatusEnum;

import java.util.List;

@Component
public class EmployeeRequestJpaRepository {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeRequestJpaRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EmployeeRequest> getEmployeeRequestList(int limit, int offset) {
        var query = """
                SELECT
                    er.id,
                    er.status,
                    CONCAT(e.first_name, ' ', e.last_name),
                    er.title,
                    er.text
                FROM employee_requests er
                JOIN employees e ON er.employee_id = e.id
                LIMIT ?
                OFFSET ?
        """;

        return this.jdbcTemplate.query(query, (rs, i) -> {
            var result = new EmployeeRequest();
            result.id = rs.getInt(1);
//            result.status = rs.getObject(2, EmployeeRequestStatusEnum.class);
            result.authorName = rs.getString(3);
            result.title = rs.getString(4);
            result.text = rs.getString(5);

            return result;
        }, limit, offset);
    }
}
