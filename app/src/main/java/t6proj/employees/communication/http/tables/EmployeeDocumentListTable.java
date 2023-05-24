package t6proj.employees.communication.http.tables;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.TextColumn;
import t6proj.employees.dto.EmployeeDocument;

public class EmployeeDocumentListTable extends AbstractTable<EmployeeDocument> {
    public EmployeeDocumentListTable(PaginatedEntityListInterface<EmployeeDocument> entityPaginatedList) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "Employee Documents";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("id").setTitle("ID"));
        this.columns.add(new TextColumn("documentType").setTitle("Document Type"));
    }
}
