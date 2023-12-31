package t6proj.employees.communication.http.tables;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.ActionButton;
import adminlte.entity_list_table.communication.http.tables.columns.ActionsColumn;
import adminlte.entity_list_table.communication.http.tables.columns.DateTimeColumn;
import adminlte.entity_list_table.communication.http.tables.columns.TextColumn;
import t6proj.jobs.dto.EmployeeContract;

import java.util.ArrayList;

public class EmployeeContractsTable extends AbstractTable<EmployeeContract> {
    public EmployeeContractsTable(
            PaginatedEntityListInterface<EmployeeContract> entityPaginatedList
    ) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "Договоры сотрудника";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("id").setTitle("ID"));
        this.columns.add(new TextColumn("jobTitle").setTitle("Название рабочей должности"));
        this.columns.add(new TextColumn("departmentTitle").setTitle("Название отдела"));
        this.columns.add(new DateTimeColumn("startsAt", "dd.MM.yyyy").setTitle("От"));
        this.columns.add(new DateTimeColumn("endsAt", "dd.MM.yyyy").setTitle("До"));

        var actionButtons = new ArrayList<ActionButton>();
        actionButtons.add(new ActionButton("Редактировать", "/contracts/<:id>/edit", "id"));
        this.columns.add(new ActionsColumn(actionButtons));
    }
}
