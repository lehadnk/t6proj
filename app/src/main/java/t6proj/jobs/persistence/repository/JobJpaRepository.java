package t6proj.jobs.persistence.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import t6proj.jobs.dto.Job;

import java.util.List;

@Component
public class JobJpaRepository {
    private final JdbcTemplate jdbcTemplate;

    public JobJpaRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Job> getJobList(int limit, int offset)
    {
        var query = """
                SELECT 
                    j.id,
                    j.department_id,
                    d.title as departmentTitle,
                    j.title as jobTitle
                FROM jobs j
                JOIN departments d ON j.department_id = d.id
                LIMIT ?
                OFFSET ?
        """;

        return this.jdbcTemplate.query(query, (rs, i) -> {
            var result = new Job();
            result.id = rs.getInt(1);
            result.departmentId = rs.getInt(2);
            result.departmentTitle = rs.getString(3);
            result.title = rs.getString(4);

            return result;
        }, limit, offset);
    }
}
