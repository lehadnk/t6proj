package t6proj.dashboard.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;

public class DashboardTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "dashboard/communication/http/templates/html/dashboard.html";

    public DashboardTemplate(AuthorizedAdminLayout layout, Integer usersCount) {
        super(layout);
        this.context.setVariable("usersCount", usersCount);
    }

    @Override
    public String getTemplatePath()
    {
        return this.templatePath;
    }
}
