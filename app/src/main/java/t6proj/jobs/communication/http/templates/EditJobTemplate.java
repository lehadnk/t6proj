package t6proj.jobs.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;

public class EditJobTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "jobs/communication/http/templates/html/edit-job.html";

    public EditJobTemplate(AuthorizedAdminLayout layout) {
        super(layout);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}
