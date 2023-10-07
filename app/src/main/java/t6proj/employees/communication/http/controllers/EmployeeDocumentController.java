package t6proj.employees.communication.http.controllers;

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
import t6proj.employees.EmployeesService;
import t6proj.employees.communication.http.forms.EmployeeDocumentForm;
import t6proj.employees.communication.http.templates.ViewEmployeeDocumentTemplate;
import t6proj.employees.dto.EmployeeDocument;

@Controller
@RequestMapping("/employee-documents")
public class EmployeeDocumentController extends AbstractHtmlController {
    private final EmployeesService employeesService;

    public EmployeeDocumentController(
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

    @PostMapping("/save")
    @ResponseBody
    @RequiresAuthorizedUser
    public ResponseEntity<String> saveEmployeeDocument(
            @ModelAttribute EmployeeDocument request
    ) {
        var employeeDocumentForm = new EmployeeDocumentForm(request.id, request.employeeId);
        employeeDocumentForm.hydrateFromRequest(request);

        if (this.webFormService.isFormValid(employeeDocumentForm)) {
            this.employeesService.saveEmployeeDocument(request);
            this.addSuccessMessage("Документ сотрудника сохранен");
            return this.redirect("/employees/" + request.employeeId + "/view");
        }

        return ResponseEntity.ok(
                this.renderTemplate(
                        new AuthorizedAdminFormTemplate(
                                this.layoutFactory.createAuthorizedAdminLayout("Редактировать документ"),
                                this.renderForm(employeeDocumentForm)
                        )
                )
        );
    }

    @RequiresAuthorizedUser
    @GetMapping("/{id}/view")
    @ResponseBody
    public String viewEmployeeDocument(
            @PathVariable("id") Integer id
    ) {
        var document = this.employeesService.getEmployeeDocumentById(id);
        if (document == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return this.renderTemplate(
                new ViewEmployeeDocumentTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Просмотр документа"),
                        document
                )
        );
    }

    @RequiresAuthorizedUser
    @GetMapping("/{id}/edit")
    @ResponseBody
    public String editEmployeeDocument(
            @PathVariable("id") Integer id
    ) {
        var document = this.employeesService.getEmployeeDocumentById(id);
        if (document == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var employeeDocumentForm = new EmployeeDocumentForm(id, document.employeeId);
        employeeDocumentForm.hydrateFromRequest(document);
        employeeDocumentForm.actionUrl = "/employee-documents/save";

        return this.renderTemplate(
                new AuthorizedAdminFormTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Редактирование документа"),
                        this.renderForm(employeeDocumentForm)
                )
        );
    }

    @RequiresAuthorizedUser
    @GetMapping("/{id}/delete")
    @ResponseBody
    public ResponseEntity<String> deleteEmployeeDocument(
            @PathVariable("id") Integer id
    ) {
        var document = this.employeesService.getEmployeeDocumentById(id);
        if (document == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        this.employeesService.deleteEmployeeDocumentById(id);
        this.addSuccessMessage("Документ сотрудника удален");

        return this.redirect("/employees/" + document.employeeId + "/view");
    }
}
