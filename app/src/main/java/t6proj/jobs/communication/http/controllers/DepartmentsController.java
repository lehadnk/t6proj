package t6proj.jobs.communication.http.controllers;

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
import t6proj.jobs.JobsService;
import t6proj.jobs.communication.http.forms.DepartmentForm;
import t6proj.jobs.communication.http.forms.validators.ParentDepartmentIdValidator;
import t6proj.jobs.communication.http.tables.DepartmentsTable;
import t6proj.jobs.communication.http.templates.DepartmentListTemplate;
import t6proj.jobs.communication.http.templates.EditDepartmentTemplate;
import t6proj.jobs.dto.Department;

@Controller
@RequestMapping("/departments")
public class DepartmentsController extends AbstractHtmlController {
    private final JobsService jobsService;

    public DepartmentsController(
            LayoutFactory layoutFactory,
            HtmlTemplateRendererService htmlTemplateRendererService,
            WebFormService webFormService,
            EntityListTableService entityListTableService,
            SessionServiceInterface sessionService,
            AuthenticationServiceInterface authenticationService,
            FlashMessageService flashMessageService,
            JobsService jobsService
    ) {
        super(layoutFactory, htmlTemplateRendererService, webFormService, entityListTableService, sessionService, authenticationService, flashMessageService);
        this.jobsService = jobsService;
    }

    @GetMapping("/")
    @ResponseBody
    @RequiresAuthorizedUser
    public String list(
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        var departmentsTable = new DepartmentsTable(
                this.jobsService.getDepartmentList(page, 10)
        );

        return this.renderTemplate(
                new DepartmentListTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Department List"),
                        this.renderTable(departmentsTable)
                )
        );
    }

    @GetMapping("/create")
    @ResponseBody
    @RequiresAuthorizedUser
    public String createDepartment()
    {
        var departments = this.jobsService.getDepartmentList(1, 10000);

        var departmentForm = new DepartmentForm(
                null,
                departments.getEntities(),
                this.createParentDepartmentIdValidator(null)
        );
        departmentForm.setActionUrl("/departments/save");

        return this.renderTemplate(
                new EditDepartmentTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Create Department"),
                        this.renderForm(departmentForm)
                )
        );
    }

    @GetMapping("/{id}/edit")
    @ResponseBody
    @RequiresAuthorizedUser
    public String editDepartment(
            @PathVariable("id") Integer id
    )
    {
        var department = this.jobsService.getDepartmentById(id);
        if (department == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var departmentList = this.jobsService.getDepartmentList(1, 10000);

        var departmentForm = new DepartmentForm(
                id,
                departmentList.getEntities(),
                this.createParentDepartmentIdValidator(id)
        );
        departmentForm.hydrateFromRequest(department);
        departmentForm.setActionUrl("/departments/save");

        return this.renderTemplate(
                new EditDepartmentTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Edit Department"),
                        this.renderForm(departmentForm)
                )
        );
    }

    @PostMapping("/save")
    @ResponseBody
    @RequiresAuthorizedUser
    public ResponseEntity<String> saveDepartment(
            @ModelAttribute Department request
    ) {
        var departmentList = this.jobsService.getDepartmentList(1, 10000);

        var departmentForm = new DepartmentForm(
                request.id,
                departmentList.getEntities(),
                this.createParentDepartmentIdValidator(request.id)
        );
        departmentForm.hydrateFromRequest(request);

        if (this.webFormService.isFormValid(departmentForm)) {
            this.jobsService.saveDepartment(request);
            this.addSuccessMessage("Отдел сохранен");

            return this.redirect("/departments/");
        }

        return ResponseEntity.ok(
                this.renderTemplate(
                        new EditDepartmentTemplate(
                                this.layoutFactory.createAuthorizedAdminLayout("Edit Department"),
                                this.renderForm(departmentForm)
                        )
                )
        );
    }


    private ParentDepartmentIdValidator createParentDepartmentIdValidator(Integer departmentId)
    {
        return new ParentDepartmentIdValidator(departmentId, this.jobsService);
    }
}
