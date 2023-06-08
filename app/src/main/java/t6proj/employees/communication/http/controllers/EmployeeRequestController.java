package t6proj.employees.communication.http.controllers;

import adminlte.authentication.AuthenticationServiceInterface;
import adminlte.common_templates.communication.templates.AuthorizedAdminTableTemplate;
import adminlte.entity_list_table.EntityListTableService;
import adminlte.flash_message.FlashMessageService;
import adminlte.html_controller.business.AbstractHtmlController;
import adminlte.html_controller.communication.http.layout.LayoutFactory;
import adminlte.html_template_renderer.HtmlTemplateRendererService;
import adminlte.session.SessionServiceInterface;
import adminlte.web_form.WebFormService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import t6proj.authorization.communication.http.RequiresAuthorizedUser;
import t6proj.employees.EmployeesService;
import t6proj.employees.communication.http.tables.EmployeeRequestListTable;
import t6proj.employees.communication.http.templates.ViewEmployeeRequestTemplate;

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
                new AuthorizedAdminTableTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Запросы от сотрудников"),
                        this.renderTable(employeeRequestsTable)
                )
        );
    }

    @GetMapping("/{id}")
    @RequiresAuthorizedUser
    @ResponseBody
    public String view(
            @PathVariable("id") Integer id
    ) {
        var employeeRequest = this.employeesService.getEmployeeRequestById(id);
        if (employeeRequest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return this.renderTemplate(
                new ViewEmployeeRequestTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Просмотр запроса от сотрудника"),
                        employeeRequest
                )
        );
    }
}
