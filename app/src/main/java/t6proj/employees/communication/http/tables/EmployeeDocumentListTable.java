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
        this.columns.add(new TextColumn("issuedBy").setTitle("Кем выдан"));
        this.columns.add(new TextColumn("documentNumber").setTitle("Номер документа"));
        this.columns.add(new DateTimeColumn("documentDate", "dd.MM.yyyy").setTitle("Дата выдачи"));

        var actionButtons = new ArrayList<ActionButton>();
        actionButtons.add(new ActionButton("Просмотр", "/employee-documents/<:id>/view", "id"));
        actionButtons.add(new ActionButton("Редактирование", "/employee-documents/<:id>/edit", "id"));
        actionButtons.add(new ActionButton("Удалить", "/employee-documents/<:id>/delete", "id", "btn-danger"));
        this.columns.add(new ActionsColumn(actionButtons));
    }
}
