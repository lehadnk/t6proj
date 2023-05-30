package t6proj.reports;

import org.springframework.stereotype.Service;
import t6proj.reports.dto.DepartmentSpendingsReportRow;
import t6proj.reports.dto.EndOfContractReportRow;
import t6proj.reports.persistence.dao.ReportsDao;
import t6proj.framework.dto.PaginatedEntityList;

@Service
public class ReportsService {

    private final ReportsDao reportsDao;

    public ReportsService(ReportsDao reportsDao)
    {
        this.reportsDao = reportsDao;
    }

    public PaginatedEntityList<EndOfContractReportRow> getEndOfContractReport(int page, int pageSize)
    {
        return this.reportsDao.getExpiringContractsReport(page, pageSize);
    }

    public PaginatedEntityList<DepartmentSpendingsReportRow> getDepartmentSpendingsReport(int page, int pageSize)
    {
        return this.reportsDao.getDepartmentSpendingsReport(page, pageSize);
    }
}
