package t6proj.dashboard.communication.http;

import adminlte.authentication.AuthenticationServiceInterface;
import adminlte.entity_list_table.EntityListTableService;
import adminlte.flash_message.FlashMessageService;
import adminlte.html_controller.business.AbstractHtmlController;
import adminlte.html_controller.communication.http.layout.LayoutFactory;
import adminlte.html_template_renderer.HtmlTemplateRendererService;
import adminlte.session.SessionServiceInterface;
import adminlte.web_form.WebFormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import t6proj.authorization.communication.http.RequiresAuthorizedUser;
import t6proj.dashboard.communication.http.templates.DashboardTemplate;

@Controller
public class DashboardController extends AbstractHtmlController {
    public DashboardController(LayoutFactory layoutFactory, HtmlTemplateRendererService htmlTemplateRendererService, WebFormService webFormService, EntityListTableService entityListTableService, SessionServiceInterface sessionService, AuthenticationServiceInterface authenticationService, FlashMessageService flashMessageService) {
        super(layoutFactory, htmlTemplateRendererService, webFormService, entityListTableService, sessionService, authenticationService, flashMessageService);
    }

    @GetMapping("/test-auth")
    @RequiresAuthorizedUser
    public void testAuth()
    {
    }

    @GetMapping("/dashboard")
    @ResponseBody
    @RequiresAuthorizedUser
    public String dashboard()
    {
        var template = new DashboardTemplate(
                this.layoutFactory.createAuthorizedAdminLayout("Dashboard"),
                7
        );

        return this.renderTemplate(template);
    }
}
