package t6proj.employees.communication.http.tables;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.*;
import t6proj.employees.dto.EmployeeDocument;

import java.util.ArrayList;

public class EmployeeDocumentListTable extends AbstractTable<EmployeeDocument> {
    public EmployeeDocumentListTable(
            PaginatedEntityListInterface<EmployeeDocument> entityPaginatedList
    ) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "Документы сотрудника";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("id").setTitle("ID"));
        this.columns.add(new TextColumn("documentType").setTitle("Тип документа"));
        this.columns.add(new UrlColumn("url").setTitle("Ссылка на документ"));
        this.columns.add(new TextColumn("documentNumber").setTitle("Номер документа"));
        this.columns.add(new DateTimeColumn("issuedAt", "dd.MM.yyyy").setTitle("Дата выдачи"));
        this.columns.add(new DateTimeColumn("validBy", "dd.MM.yyyy").setTitle("Дата действителен до"));

        var actionButtons = new ArrayList<ActionButton>();
        actionButtons.add(new ActionButton("Просмотр", "/employee-documents/<:id>/view", "id"));
        this.columns.add(new ActionsColumn(actionButtons));
    }
}
