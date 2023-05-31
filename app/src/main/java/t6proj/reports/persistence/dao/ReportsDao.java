package t6proj.reports.persistence.dao;

import org.springframework.stereotype.Component;
import t6proj.jobs.persistence.repository.DepartmentHibernateRepository;
import t6proj.reports.dto.DepartmentSpendingsReportRow;
import t6proj.reports.dto.EndOfContractReportRow;
import t6proj.reports.persistence.repository.ReportsRepository;
import t6proj.framework.dto.PaginatedEntityList;

@Component
public class ReportsDao {

    private final ReportsRepository reportsRepository;
    private final DepartmentHibernateRepository departmentHibernateRepository;

    public ReportsDao(
            ReportsRepository reportsRepository,
            DepartmentHibernateRepository departmentHibernateRepository
    ) {
        this.reportsRepository = reportsRepository;
        this.departmentHibernateRepository = departmentHibernateRepository;
    }

    public PaginatedEntityList<EndOfContractReportRow> getExpiringContractsReport(int page, int pageSize) {
        var offset = (page - 1) * pageSize;
        var reportRows = this.reportsRepository.getExpiringContracts(pageSize, offset);
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
        var reportRowsCount = this.departmentHibernateRepository.getDepartmentsCount();

        return new PaginatedEntityList<>(
                reportRows,
                page,
                (int) Math.ceil((double) reportRowsCount / pageSize)
        );
    }
}
