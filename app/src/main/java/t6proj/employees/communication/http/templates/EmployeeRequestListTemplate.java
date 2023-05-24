package t6proj.employees.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;

public class EmployeeRequestListTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "employees/communication/http/templates/html/employee-request-list.html";

    public EmployeeRequestListTemplate(AuthorizedAdminLayout layout) {
        super(layout);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}
