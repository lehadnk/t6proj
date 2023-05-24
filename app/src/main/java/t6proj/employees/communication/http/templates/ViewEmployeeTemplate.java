package t6proj.employees.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;

public class ViewEmployeeTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "employees/communication/http/templates/html/view-employee.html";

    public ViewEmployeeTemplate(AuthorizedAdminLayout layout) {
        super(layout);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}
