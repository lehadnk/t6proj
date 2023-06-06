package t6proj.employees.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;
import t6proj.employees.dto.EmployeeDocument;

public class ViewEmployeeDocumentTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "employees/communication/http/templates/html/view-employee-document.html";

    public ViewEmployeeDocumentTemplate(
            AuthorizedAdminLayout layout,
            EmployeeDocument document
    ) {
        super(layout);
        this.context.setVariable("document", document);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}
