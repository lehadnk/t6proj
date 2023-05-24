package t6proj.employees.communication.http.tables;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.ActionButton;
import adminlte.entity_list_table.communication.http.tables.columns.ActionsColumn;
import adminlte.entity_list_table.communication.http.tables.columns.TextColumn;
import t6proj.employees.dto.Employee;

import java.util.ArrayList;

public class EmployeeListTable extends AbstractTable<Employee> {
    public EmployeeListTable(PaginatedEntityListInterface<Employee> entityPaginatedList) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "Сотрудники";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("id").setTitle("ID"));
        this.columns.add(new TextColumn("firstName").setTitle("Имя"));
        this.columns.add(new TextColumn("lastName").setTitle("Фамилия"));
        this.columns.add(new TextColumn("birthdate").setTitle("Дата рождения"));

        var actionButtons = new ArrayList<ActionButton>();
        actionButtons.add(new ActionButton("Документы", "/employees/<:id>/view", "id"));
        actionButtons.add(new ActionButton("Редактировать", "/employees/<:id>/edit", "id"));
        this.columns.add(new ActionsColumn(actionButtons));
    }
}
