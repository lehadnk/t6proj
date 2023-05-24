package t6proj.employees.communication.http.controllers;

import adminlte.authentication.AuthenticationServiceInterface;
import adminlte.entity_list_table.EntityListTableService;
import adminlte.flash_message.FlashMessageService;
import adminlte.html_controller.business.AbstractHtmlController;
import adminlte.html_controller.communication.http.layout.LayoutFactory;
import adminlte.html_template_renderer.HtmlTemplateRendererService;
import adminlte.session.SessionServiceInterface;
import adminlte.web_form.WebFormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import t6proj.authorization.communication.http.RequiresAuthorizedUser;
import t6proj.employees.EmployeesService;
import t6proj.employees.communication.http.forms.EmployeeDocumentForm;
import t6proj.employees.communication.http.forms.EmployeeForm;
import t6proj.employees.communication.http.tables.EmployeeDocumentListTable;
import t6proj.employees.communication.http.tables.EmployeeListTable;
import t6proj.employees.communication.http.templates.EditEmployeeDocumentTemplate;
import t6proj.employees.communication.http.templates.EditEmployeeTemplate;
import t6proj.employees.communication.http.templates.EmployeeListTemplate;
import t6proj.employees.communication.http.templates.ViewEmployeeTemplate;
import t6proj.employees.dto.Employee;
import t6proj.employees.dto.EmployeeDocument;
import t6proj.jobs.JobsService;
import t6proj.jobs.communication.http.templates.DepartmentListTemplate;
import t6proj.jobs.communication.http.templates.EditDepartmentTemplate;

@Controller
@RequestMapping("/employees")
public class EmployeeController extends AbstractHtmlController {
    private final EmployeesService employeesService;
    private final JobsService jobsService;

    public EmployeeController(
            LayoutFactory layoutFactory,
            HtmlTemplateRendererService htmlTemplateRendererService,
            WebFormService webFormService,
            EntityListTableService entityListTableService,
            SessionServiceInterface sessionService,
            AuthenticationServiceInterface authenticationService,
            FlashMessageService flashMessageService,
            EmployeesService employeesService,
            JobsService jobsService
    ) {
        super(layoutFactory, htmlTemplateRendererService, webFormService, entityListTableService, sessionService, authenticationService, flashMessageService);
        this.employeesService = employeesService;
        this.jobsService = jobsService;
    }

    @GetMapping("/")
    @ResponseBody
    @RequiresAuthorizedUser
    public String list(
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        var departmentsTable = new EmployeeListTable(
                this.employeesService.getEmployeesList(page, 10)
        );

        return this.renderTemplate(
                new EmployeeListTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Employee List"),
                        this.renderTable(departmentsTable)
                )
        );
    }

    @GetMapping("/create")
    @ResponseBody
    @RequiresAuthorizedUser
    public String createEmployee()
    {
        var jobs = this.jobsService.getJobList(1, 1000);
        var employeeForm = new EmployeeForm(null, jobs.getEntities());

        employeeForm.setActionUrl("/employees/save");

        return this.renderTemplate(
                new EditEmployeeTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Create Employee"),
                        this.renderForm(employeeForm)
                )
        );
    }

    @GetMapping("/{id}/edit")
    @ResponseBody
    @RequiresAuthorizedUser
    public String editEmployee(
            @PathVariable("id") Integer id
    )
    {
        var employee = this.employeesService.getEmployeeById(id);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var jobs = this.jobsService.getJobList(1, 1000);
        var employeeForm = new EmployeeForm(null, jobs.getEntities());

        employeeForm.hydrateFromRequest(employee);
        employeeForm.setActionUrl("/employees/save");

        return this.renderTemplate(
                new EditEmployeeTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Edit Employees"),
                        this.renderForm(employeeForm)
                )
        );
    }

    @PostMapping("/save")
    @ResponseBody
    @RequiresAuthorizedUser
    public ResponseEntity<String> saveEmployee(
            @ModelAttribute Employee request
    ) {
        var jobs = this.jobsService.getJobList(1, 1000);
        var employeeForm = new EmployeeForm(null, jobs.getEntities());
        employeeForm.hydrateFromRequest(request);

        if (this.webFormService.isFormValid(employeeForm)) {
            this.employeesService.saveEmployee(request);
            this.addSuccessMessage("Employee saved");

            return this.redirect("/employees/");
        }

        return ResponseEntity.ok(
                this.renderTemplate(
                        new EditEmployeeTemplate(
                                this.layoutFactory.createAuthorizedAdminLayout("Edit Employees"),
                                this.renderForm(employeeForm)
                        )
                )
        );
    }

    @GetMapping("/{id}/view")
    @ResponseBody
    @RequiresAuthorizedUser
    public String viewEmployee(
            @PathVariable("id") Integer id,
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        var employee = this.employeesService.getEmployeeById(id);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var employeeDocuments = this.employeesService.getEmployeeDocumentList(employee.id, page, 10);
        var documentListTable = new EmployeeDocumentListTable(employeeDocuments);

        return this.renderTemplate(
                new ViewEmployeeTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("View Employee"),
                        employee,
                        this.renderTable(documentListTable)
                )
        );
    }

    @GetMapping("/{id}/add-document")
    @ResponseBody
    @RequiresAuthorizedUser
    public String createEmployeeDocument(
            @PathVariable("id") Integer employeeId
    )
    {
        var employee = this.employeesService.getEmployeeById(employeeId);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var employeeDocument = new EmployeeDocument();
        employeeDocument.employeeId = employeeId;

        var employeeDocumentForm = new EmployeeDocumentForm(null, employeeId);
        employeeDocumentForm.hydrateFromRequest(employeeDocument);
        employeeDocumentForm.setActionUrl("/employee-documents/save");

        return this.renderTemplate(
                new EditEmployeeDocumentTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Create Employee"),
                        this.renderForm(employeeDocumentForm)
                )
        );
    }
}
