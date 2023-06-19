package t6proj.jobs.communication.http.controllers;

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
import t6proj.jobs.JobsService;
import t6proj.jobs.communication.http.forms.JobForm;
import t6proj.jobs.communication.http.tables.JobsTable;
import t6proj.jobs.dto.Job;

import java.util.ArrayList;

@Controller
@RequestMapping("/jobs")
public class JobsController extends AbstractHtmlController {
    private final JobsService jobsService;

    public JobsController(
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
        var jobsTable = new JobsTable(
                this.jobsService.getJobList(page, 10)
        );

        var actionButtonList = new ArrayList<HrefButton>();
        actionButtonList.add(new HrefButton("Добавить рабочее место", "/jobs/create"));

        return this.renderTemplate(
                new AuthorizedAdminTableTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Список рабочих мест"),
                        this.renderTable(jobsTable),
                        actionButtonList
                )
        );
    }

    @GetMapping("/create")
    @ResponseBody
    @RequiresAuthorizedUser
    public String createJob()
    {
        var departmentList = this.jobsService.getDepartmentList(1, 1000);

        var jobsForm = new JobForm(null, departmentList.getEntities());
        jobsForm.setActionUrl("/jobs/save");

        return this.renderTemplate(
                new AuthorizedAdminFormTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Добавить рабочее место"),
                        this.renderForm(jobsForm)
                )
        );
    }

    @GetMapping("/{id}/edit")
    @ResponseBody
    @RequiresAuthorizedUser
    public String editJob(
            @PathVariable("id") Integer id
    ) {
        var job = this.jobsService.getJobById(id);
        if (job == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var departmentList = this.jobsService.getDepartmentList(1, 1000);

        var jobForm = new JobForm(id, departmentList.getEntities());
        jobForm.hydrateFromRequest(job);
        jobForm.setActionUrl("/jobs/save");

        return this.renderTemplate(
                new AuthorizedAdminFormTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Редактировать рабочее место"),
                        this.renderForm(jobForm)
                )
        );
    }

    @PostMapping("/save")
    @ResponseBody
    @RequiresAuthorizedUser
    public ResponseEntity<String> saveJob(
            @ModelAttribute Job request
    ) {
        var departmentList = this.jobsService.getDepartmentList(1, 1000);

        var jobForm = new JobForm(null, departmentList.getEntities());
        jobForm.hydrateFromRequest(request);

        if (this.webFormService.isFormValid(jobForm)) {
            this.jobsService.saveJob(request);
            this.addSuccessMessage("Рабочее место сохранено");

            return this.redirect("/jobs/");
        }

        return ResponseEntity.ok(
                this.renderTemplate(
                        new AuthorizedAdminFormTemplate(
                                this.layoutFactory.createAuthorizedAdminLayout("Редактировать рабочее место"),
                                this.renderForm(jobForm)
                        )
                )
        );
    }
}
