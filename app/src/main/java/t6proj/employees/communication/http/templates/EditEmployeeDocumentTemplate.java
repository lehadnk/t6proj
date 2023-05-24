package t6proj.employees.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;

public class EditEmployeeDocumentTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "employees/communication/http/templates/html/edit-employee-document.html";

    public EditEmployeeDocumentTemplate(AuthorizedAdminLayout layout) {
        super(layout);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}
