package t6proj.authentication.communication.templates;

import adminlte.html_controller.communication.http.layout.general.AbstractGeneralLayoutTemplate;
import adminlte.html_controller.communication.http.layout.general.GeneralLayout;

public class AdminLoginPageTemplate extends AbstractGeneralLayoutTemplate {
    public String templatePath = "authentication/communication/templates/html/login.html";

    public AdminLoginPageTemplate(GeneralLayout layout, String loginFormContents) {
        super(layout);
        this.context.setVariable("loginForm", loginFormContents);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}
