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
import t6proj.employees.EmployeesService;

@Controller
public class DashboardController extends AbstractHtmlController {
    private final EmployeesService employeesService;

    public DashboardController(
            LayoutFactory layoutFactory,
            HtmlTemplateRendererService htmlTemplateRendererService,
            WebFormService webFormService,
            EntityListTableService entityListTableService,
            SessionServiceInterface sessionService,
            AuthenticationServiceInterface authenticationService,
            FlashMessageService flashMessageService,
            EmployeesService employeesService
    ) {
        super(layoutFactory, htmlTemplateRendererService, webFormService, entityListTableService, sessionService, authenticationService, flashMessageService);
        this.employeesService = employeesService;
    }

    @GetMapping("/dashboard")
    @ResponseBody
    @RequiresAuthorizedUser
    public String dashboard()
    {
        var template = new DashboardTemplate(
                this.layoutFactory.createAuthorizedAdminLayout("Статистика"),
                this.employeesService.getEmployeeRequestsCount()
        );

        return this.renderTemplate(template);
    }
}
