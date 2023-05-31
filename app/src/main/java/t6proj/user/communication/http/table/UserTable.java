package t6proj.user.communication.http.table;

import adminlte.entity_list_table.business.PaginatedEntityListInterface;
import adminlte.entity_list_table.communication.http.tables.AbstractTable;
import adminlte.entity_list_table.communication.http.tables.columns.ActionButton;
import adminlte.entity_list_table.communication.http.tables.columns.ActionsColumn;
import adminlte.entity_list_table.communication.http.tables.columns.TextColumn;
import t6proj.user.dto.User;

import java.util.ArrayList;

public class UserTable extends AbstractTable<User> {
    public UserTable(PaginatedEntityListInterface<User> entityPaginatedList) {
        super(entityPaginatedList);
    }

    @Override
    public String getTitle() {
        return "User List";
    }

    @Override
    public void defineColumns() {
        this.columns.add(new TextColumn("id").setTitle("ID"));
        this.columns.add(new TextColumn("email").setTitle("Email"));

        var actionButtons = new ArrayList<ActionButton>();
        actionButtons.add(new ActionButton("Редактировать", "/users/<:id>/edit", "id"));
        this.columns.add(new ActionsColumn(actionButtons));
    }
}
