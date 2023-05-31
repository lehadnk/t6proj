package t6proj.jobs.persistence.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import t6proj.jobs.dto.EmployeeContract;

import java.util.List;

@Component
public class ContractJpaRepository {
    private final JdbcTemplate jdbcTemplate;

    public ContractJpaRepository(
       JdbcTemplate jdbcTemplate
    ) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EmployeeContract> getEmployeeContracts(int employeeId, int limit, int offset)
    {
        var query = """
                SELECT 
                    c.id,
                    j.title as jobTitle,
                    d.title as departmentTitle,
                    c.starts_at,
                    c.ends_at
                FROM contracts c
                JOIN jobs j ON c.job_id = j.id
                JOIN departments d ON j.department_id = d.id
                WHERE c.employee_id = ?
                LIMIT ?
                OFFSET ?
        """;

        return this.jdbcTemplate.query(query, (rs, i) -> {
            var result = new EmployeeContract();
            result.id = rs.getInt(1);
            result.jobTitle = rs.getString(2);
            result.departmentTitle = rs.getString(3);
            result.startsAt = rs.getDate(4);
            result.endsAt = rs.getDate(5);

            return result;
        }, employeeId, limit, offset);
    }
}
