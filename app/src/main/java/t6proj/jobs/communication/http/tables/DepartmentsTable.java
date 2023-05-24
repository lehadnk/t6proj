package t6proj.jobs.communication.http.tables;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.ActionButton;
import adminlte.entity_list_table.communication.http.tables.columns.ActionsColumn;
import adminlte.entity_list_table.communication.http.tables.columns.TextColumn;
import t6proj.jobs.dto.Department;

import java.util.ArrayList;

public class DepartmentsTable extends AbstractTable<Department> {
    public DepartmentsTable(PaginatedEntityListInterface<Department> entityPaginatedList) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "Отделы";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("id").setTitle("ID"));
        this.columns.add(new TextColumn("title").setTitle("Название"));

        var actionButtons = new ArrayList<ActionButton>();
        actionButtons.add(new ActionButton("Редактировать", "/departments/<:id>/edit", "id"));
        this.columns.add(new ActionsColumn(actionButtons));
    }
}
