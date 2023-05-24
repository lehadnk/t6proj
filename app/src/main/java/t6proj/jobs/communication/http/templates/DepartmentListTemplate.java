package t6proj.jobs.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;

public class DepartmentListTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "jobs/communication/http/templates/html/department-list.html";

    public DepartmentListTemplate(
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
