package t6proj.jobs.communication.http.tables;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.ActionButton;
import adminlte.entity_list_table.communication.http.tables.columns.ActionsColumn;
import adminlte.entity_list_table.communication.http.tables.columns.TextColumn;
import t6proj.jobs.dto.Job;

import java.util.ArrayList;

public class JobsTable extends AbstractTable<Job> {
    public JobsTable(PaginatedEntityListInterface<Job> entityPaginatedList) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "Рабочие места";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("id").setTitle("ID"));
        this.columns.add(new TextColumn("title").setTitle("Название"));
        this.columns.add(new TextColumn("departmentTitle").setTitle("Отдел"));

        var actionButtons = new ArrayList<ActionButton>();
        actionButtons.add(new ActionButton("Редактировать", "/jobs/<:id>/edit", "id"));
        this.columns.add(new ActionsColumn(actionButtons));
    }
}
