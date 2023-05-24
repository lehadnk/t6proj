package t6proj.employees.communication.http.tables;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.TextColumn;
import t6proj.employees.dto.Employee;

public class EmployeeListTable extends AbstractTable<Employee> {
    public EmployeeListTable(PaginatedEntityListInterface<Employee> entityPaginatedList) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "Employees";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("id").setTitle("ID"));
        this.columns.add(new TextColumn("firstName").setTitle("First Name"));
        this.columns.add(new TextColumn("lastName").setTitle("Last Name"));
        this.columns.add(new TextColumn("status").setTitle("Status"));
    }
}
