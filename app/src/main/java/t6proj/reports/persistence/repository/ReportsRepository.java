package t6proj.reports.persistence.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import t6proj.reports.dto.DepartmentSpendingsReportRow;
import t6proj.reports.dto.EndOfContractReportRow;

import java.util.List;

@Component
public class ReportsRepository {
    private final JdbcTemplate jdbcTemplate;

    public ReportsRepository(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EndOfContractReportRow> getExpiringContracts(int limit, int offset)
    {
        var query = """
                SELECT
                    e.id as employee_id,
                    CONCAT(e.first_name, ' ', e.last_name),
                    c.ends_at, j.title as job_title,
                    d.title as department_title
                FROM contracts c
                JOIN employees e ON c.employee_id = e.id
                JOIN jobs j on c.job_id = j.id
                JOIN departments d on j.department_id = d.id
                WHERE c.ends_at < now() - interval '3 months' AND c.ends_at > now()
                LIMIT ?
                OFFSET ?
        """;

        return this.jdbcTemplate.query(query, (rs, i) -> {
            var result = new EndOfContractReportRow();
            result.employeeId = rs.getInt(1);
            result.employeeName = rs.getString(2);
            result.contractEndsAt = rs.getDate(3);
            result.jobTitle = rs.getString(4);
            result.departmentName = rs.getString(5);
            return result;
        }, limit, offset);
    }

    public Integer countExpiringContracts()
    {
        var query = """
                SELECT
                    count(*)
                FROM contracts c
                JOIN employees e ON c.employee_id = e.id
                JOIN jobs j on c.job_id = j.id
                JOIN departments d on j.department_id::int = d.id
                WHERE c.ends_at < now() - interval '3 months' AND c.ends_at > now()
        """;

        return this.jdbcTemplate.queryForObject(query, Integer.class);
    }

    public List<DepartmentSpendingsReportRow> getDepartmentSpendingsReport(int offset, int limit) {
        var query = """
                WITH RECURSIVE
                    tree AS (
                        SELECT
                            departments.id,
                            concat(departments.title) as title
                        FROM departments
                        WHERE parent_department_id is null
                
                        UNION ALL
                
                        SELECT
                            d.id,
                            concat(tree.title, ' > ', d.title)
                        FROM tree, departments d
                        WHERE d.parent_department_id = tree.id
                    )
                
                SELECT t.title, get_department_budget(t.id) as budget
                FROM tree t
                ORDER BY t.title
                LIMIT ?
                OFFSET ?
        """;

        return this.jdbcTemplate.query(query, (rs, i) -> {
            var result = new DepartmentSpendingsReportRow();
            result.departmentName = rs.getString(1);
            result.monthlySpendings = rs.getDouble(2);
            return result;
        }, limit, offset);
    }
}
