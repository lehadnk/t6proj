package t6proj.employees.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;
import t6proj.employees.dto.EmployeeRequest;

public class ViewEmployeeRequestTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "employees/communication/http/templates/html/view-employee-request.html";

    public ViewEmployeeRequestTemplate(
            AuthorizedAdminLayout layout,
            EmployeeRequest employeeRequest
    ) {
        super(layout);
        this.context.setVariable("employeeRequest", employeeRequest);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}
