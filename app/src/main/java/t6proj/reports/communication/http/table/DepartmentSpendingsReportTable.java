package t6proj.reports.communication.http.table;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.TextColumn;
import t6proj.reports.dto.DepartmentSpendingsReportRow;
import t6proj.reports.dto.EndOfContractReportRow;

public class DepartmentSpendingsReportTable extends AbstractTable<DepartmentSpendingsReportRow> {

    public DepartmentSpendingsReportTable(PaginatedEntityListInterface<DepartmentSpendingsReportRow> entityPaginatedList) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "Траты по отделам";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("departmentName").setTitle("Название отдела"));
        this.columns.add(new TextColumn("monthlySpendings").setTitle("Месячные траты"));
    }
}
