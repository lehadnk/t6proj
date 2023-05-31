package t6proj.user.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;

public class EditUserTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "user/communication/http/templates/html/edit-user.html";

    public EditUserTemplate(
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
