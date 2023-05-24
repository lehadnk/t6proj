package t6proj.employees.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;

public class EditEmployeeTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "employees/communication/http/templates/html/edit-employee.html";

    public EditEmployeeTemplate(AuthorizedAdminLayout layout) {
        super(layout);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}
