package t6proj.employees.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;
import t6proj.employees.dto.Employee;

public class ViewEmployeeTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "employees/communication/http/templates/html/view-employee.html";

    public ViewEmployeeTemplate(
            AuthorizedAdminLayout layout,
            Employee employee,
            String documentListTable,
            String contractListTable
    ) {
        super(layout);
        this.context.setVariable("employee", employee);
        this.context.setVariable("documentListTable", documentListTable);
        this.context.setVariable("contractListTable", contractListTable);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}
