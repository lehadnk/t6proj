package t6proj.reports.communication.http.table;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.TextColumn;
import t6proj.reports.dto.EndOfContractReportRow;

public class EndOfContractReportTable extends AbstractTable<EndOfContractReportRow> {

    public EndOfContractReportTable(PaginatedEntityListInterface<EndOfContractReportRow> entityPaginatedList) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "Сотрудники с истекающими контрактами";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("employeeName").setTitle("Имя сотрудника"));
        this.columns.add(new TextColumn("departmentName").setTitle("Отдел"));
        this.columns.add(new TextColumn("jobTitle").setTitle("Должность"));
        this.columns.add(new TextColumn("contractEndsAt").setTitle("Дата окончания договора"));
    }
}
