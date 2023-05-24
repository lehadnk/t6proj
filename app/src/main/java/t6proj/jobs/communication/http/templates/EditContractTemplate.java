package t6proj.jobs.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;

public class EditContractTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "jobs/communication/http/templates/html/edit-contract.html";

    public EditContractTemplate(
            AuthorizedAdminLayout layout,
            String formContents
    ) {
        super(layout);
        this.context.setVariable("formContents", formContents);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}
