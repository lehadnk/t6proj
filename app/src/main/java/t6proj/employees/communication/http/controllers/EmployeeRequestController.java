package t6proj.employees.communication.http.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import t6proj.authorization.communication.http.RequiresAuthorizedUser;
import t6proj.employees.EmployeesService;
import t6proj.employees.communication.http.tables.EmployeeRequestListTable;
import t6proj.employees.communication.http.templates.EmployeeRequestListTemplate;

@Controller
@RequestMapping("/employee-requests")
public class EmployeeRequestController extends AbstractHtmlController {
    private final EmployeesService employeesService;

    public EmployeeRequestController(
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

    @GetMapping("/")
    @ResponseBody
    @RequiresAuthorizedUser
    public String list(
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        var employeeRequests = this.employeesService.getEmployeeRequests(page, 25);
        var employeeRequestsTable = new EmployeeRequestListTable(employeeRequests);

        return this.renderTemplate(
                new EmployeeRequestListTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Employee Requests"),
                        this.renderTable(employeeRequestsTable)
                )
        );
    }
}
