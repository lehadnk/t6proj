package t6proj.jobs.communication.http.controllers;

import adminlte.authentication.AuthenticationServiceInterface;
import adminlte.common_templates.communication.templates.AuthorizedAdminFormTemplate;
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
import t6proj.jobs.communication.http.forms.ContractForm;
import t6proj.jobs.dto.Contract;

@Controller
@RequestMapping("/contracts")
public class ContractsController extends AbstractHtmlController {
    private final JobsService jobsService;

    public ContractsController(
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

    @PostMapping("/save")
    @ResponseBody
    @RequiresAuthorizedUser
    public ResponseEntity<String> saveContract(
            @ModelAttribute Contract request
    ) {
        var jobs = this.jobsService.getJobList(1, 10000);

        var contractForm = new ContractForm(request.id, request.employeeId, jobs.getEntities());
        contractForm.hydrateFromRequest(request);

        if (this.webFormService.isFormValid(contractForm)) {
            this.jobsService.saveContract(request);
            this.addSuccessMessage("Договор сохранен");

            return this.redirect("/employees/" + request.employeeId + "/view");
        }

        return ResponseEntity.ok(
                this.renderTemplate(
                        new AuthorizedAdminFormTemplate(
                                this.layoutFactory.createAuthorizedAdminLayout("Сохранить договор"),
                                this.renderForm(contractForm)
                        )
                )
        );
    }

    @GetMapping("/{id}/edit")
    @ResponseBody
    @RequiresAuthorizedUser
    public String editContract(
            @PathVariable("id") Integer id
    )
    {
        var jobs = this.jobsService.getJobList(1, 10000);

        var contract = this.jobsService.getContractById(id);
        if (contract == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var contractForm = new ContractForm(id, contract.employeeId, jobs.getEntities());
        contractForm.hydrateFromRequest(contract);

        return this.renderTemplate(
                new AuthorizedAdminFormTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Редактировать контракт"),
                        this.renderForm(contractForm)
                )
        );
    }
}
