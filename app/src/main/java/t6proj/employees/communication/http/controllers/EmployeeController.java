package t6proj.employees.communication.http.controllers;

import adminlte.authentication.AuthenticationServiceInterface;
import adminlte.common_templates.communication.templates.AuthorizedAdminFormTemplate;
import adminlte.common_templates.communication.templates.AuthorizedAdminTableTemplate;
import adminlte.entity_list_table.EntityListTableService;
import adminlte.flash_message.FlashMessageService;
import adminlte.html_controller.business.AbstractHtmlController;
import adminlte.html_controller.communication.http.layout.LayoutFactory;
import adminlte.html_template_renderer.HtmlTemplateRendererService;
import adminlte.session.SessionServiceInterface;
import adminlte.ui.business.HrefButton;
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
import t6proj.employees.communication.http.tables.EmployeeContractsTable;
import t6proj.employees.communication.http.tables.EmployeeDocumentListTable;
import t6proj.employees.communication.http.tables.EmployeeListTable;
import t6proj.employees.communication.http.templates.ViewEmployeeTemplate;
import t6proj.employees.dto.Employee;
import t6proj.employees.dto.EmployeeDocument;
import t6proj.jobs.JobsService;
import t6proj.jobs.communication.http.forms.ContractForm;
import t6proj.jobs.dto.Contract;

import java.util.ArrayList;

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

        var actionButtonList = new ArrayList<HrefButton>();
        actionButtonList.add(new HrefButton("Добавить сотрудника", "/employees/create"));

        return this.renderTemplate(
                new AuthorizedAdminTableTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Список сотрудников"),
                        this.renderTable(departmentsTable),
                        actionButtonList
                )
        );
    }

    @GetMapping("/create")
    @ResponseBody
    @RequiresAuthorizedUser
    public String createEmployee()
    {
        var employeeForm = new EmployeeForm(null);

        employeeForm.setActionUrl("/employees/save");

        return this.renderTemplate(
                new AuthorizedAdminFormTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Добавить сотрудника"),
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

        var employeeForm = new EmployeeForm(null);

        employeeForm.hydrateFromRequest(employee);
        employeeForm.setActionUrl("/employees/save");

        return this.renderTemplate(
                new AuthorizedAdminFormTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Редактировать сотрудника"),
                        this.renderForm(employeeForm)
                )
        );
    }

    @GetMapping("/{id}/delete")
    @ResponseBody
    @RequiresAuthorizedUser
    public ResponseEntity<String> deleteEmployee(
            @PathVariable("id") Integer id
    )
    {
        var employee = this.employeesService.getEmployeeById(id);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        employee.isDeleted = true;
        this.employeesService.saveEmployee(employee);
        this.addSuccessMessage("Сотрудник был удален.");
        return this.redirect("/employees/");
    }

    @PostMapping("/save")
    @ResponseBody
    @RequiresAuthorizedUser
    public ResponseEntity<String> saveEmployee(
            @ModelAttribute Employee request
    ) {
        var employeeForm = new EmployeeForm(null);
        employeeForm.hydrateFromRequest(request);

        if (this.webFormService.isFormValid(employeeForm)) {
            this.employeesService.saveEmployee(request);
            this.addSuccessMessage("Сотрудник сохранен");

            return this.redirect("/employees/");
        }

        return ResponseEntity.ok(
                this.renderTemplate(
                        new AuthorizedAdminFormTemplate(
                                this.layoutFactory.createAuthorizedAdminLayout("Редактировать сотрудника"),
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

        var employeeContracts = this.jobsService.getEmployeeContracts(employee.id, page, 10);
        var employeeContractsTable = new EmployeeContractsTable(employeeContracts);

        return this.renderTemplate(
                new ViewEmployeeTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Документы сотрудника"),
                        employee,
                        this.renderTable(documentListTable),
                        this.renderTable(employeeContractsTable)
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
                new AuthorizedAdminFormTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Добавить документ сотрудника"),
                        this.renderForm(employeeDocumentForm)
                )
        );
    }

    @GetMapping("/{id}/add-contract")
    @ResponseBody
    @RequiresAuthorizedUser
    public String createEmployeeContract(
            @PathVariable("id") Integer employeeId
    )
    {
        var employee = this.employeesService.getEmployeeById(employeeId);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var contract = new Contract();
        contract.employeeId = employeeId;

        var jobs = this.jobsService.getJobList(1, 10000);

        var employeeDocumentForm = new ContractForm(null, employeeId, jobs.getEntities());
        employeeDocumentForm.hydrateFromRequest(contract);

        return this.renderTemplate(
                new AuthorizedAdminFormTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Добавить договор"),
                        this.renderForm(employeeDocumentForm)
                )
        );
    }
}
