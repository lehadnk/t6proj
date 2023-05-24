package t6proj.jobs.communication.http.tables;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.TextColumn;
import t6proj.jobs.dto.Job;

public class JobsTable extends AbstractTable<Job> {
    public JobsTable(PaginatedEntityListInterface<Job> entityPaginatedList) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "Jobs";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("id").setTitle("ID"));
        this.columns.add(new TextColumn("title").setTitle("Title"));
    }
}
