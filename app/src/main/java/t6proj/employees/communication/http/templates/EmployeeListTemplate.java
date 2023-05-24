package t6proj.employees.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;

public class EmployeeListTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "employees/communication/http/templates/html/employee-list.html";

    public EmployeeListTemplate(
            AuthorizedAdminLayout layout,
            String tableContents
    ) {
        super(layout);
        this.context.setVariable("tableContents", tableContents);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}
