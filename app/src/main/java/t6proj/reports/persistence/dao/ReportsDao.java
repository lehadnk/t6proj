package t6proj.reports.persistence.dao;

import org.springframework.stereotype.Component;
import t6proj.jobs.persistence.repository.DepartmentRepository;
import t6proj.reports.dto.DepartmentSpendingsReportRow;
import t6proj.reports.dto.EndOfContractReportRow;
import t6proj.reports.persistence.repository.ReportsRepository;
import t6proj.framework.dto.PaginatedEntityList;

@Component
public class ReportsDao {

    private final ReportsRepository reportsRepository;
    private final DepartmentRepository departmentRepository;

    public ReportsDao(
            ReportsRepository reportsRepository,
            DepartmentRepository departmentRepository
    ) {
        this.reportsRepository = reportsRepository;
        this.departmentRepository = departmentRepository;
    }

    public PaginatedEntityList<EndOfContractReportRow> getExpiringContractsReport(int page, int pageSize) {
        var offset = (page - 1) * pageSize;
        var reportRows = this.reportsRepository.getExpiringContracts(offset, pageSize);
        var reportRowsCount = this.reportsRepository.countExpiringContracts();

        return new PaginatedEntityList<>(
                reportRows,
                page,
                (int) Math.ceil((double) reportRowsCount / pageSize)
        );
    }

    public PaginatedEntityList<DepartmentSpendingsReportRow> getDepartmentSpendingsReport(int page, int pageSize) {
        var offset = (page - 1) * pageSize;
        var reportRows = this.reportsRepository.getDepartmentSpendingsReport(offset, pageSize);
        var reportRowsCount = this.departmentRepository.getDepartmentsCount();

        return new PaginatedEntityList<>(
                reportRows,
                page,
                (int) Math.ceil((double) reportRowsCount / pageSize)
        );
    }
}
