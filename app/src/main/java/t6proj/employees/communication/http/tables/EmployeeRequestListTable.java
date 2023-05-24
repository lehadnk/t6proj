package t6proj.employees.communication.http.tables;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.ActionButton;
import adminlte.entity_list_table.communication.http.tables.columns.ActionsColumn;
import adminlte.entity_list_table.communication.http.tables.columns.TextColumn;
import t6proj.employees.dto.EmployeeRequest;

import java.util.ArrayList;

public class EmployeeRequestListTable extends AbstractTable<EmployeeRequest> {
    public EmployeeRequestListTable(PaginatedEntityListInterface<EmployeeRequest> entityPaginatedList) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "Запросы от сотрудников";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("id").setTitle("ID"));
        this.columns.add(new TextColumn("authorName").setTitle("Автор"));
        this.columns.add(new TextColumn("title").setTitle("Заголовок"));

        var actionButtons = new ArrayList<ActionButton>();
        actionButtons.add(new ActionButton("Документы", "/employee-requests/<:id>", "id"));
        this.columns.add(new ActionsColumn(actionButtons));
    }
}
