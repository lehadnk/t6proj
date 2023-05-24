package t6proj.employees.communication.http.tables;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.ActionButton;
import adminlte.entity_list_table.communication.http.tables.columns.ActionsColumn;
import adminlte.entity_list_table.communication.http.tables.columns.TextColumn;
import t6proj.jobs.dto.Contract;

import java.util.ArrayList;

public class EmployeeContractsTable extends AbstractTable<Contract> {
    public EmployeeContractsTable(
            PaginatedEntityListInterface<Contract> entityPaginatedList
    ) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "Контракты сотрудника";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("id").setTitle("ID"));
        this.columns.add(new TextColumn("jobTitle").setTitle("Название рабочей должности"));
        this.columns.add(new TextColumn("startsAt").setTitle("От"));
        this.columns.add(new TextColumn("endsAt").setTitle("До"));

        var actionButtons = new ArrayList<ActionButton>();
        actionButtons.add(new ActionButton("Редактировать", "/contracts/<:id>/edit", "id"));
        this.columns.add(new ActionsColumn(actionButtons));
    }
}
