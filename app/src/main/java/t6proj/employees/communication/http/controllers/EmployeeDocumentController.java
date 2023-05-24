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
import t6proj.employees.communication.http.templates.EditEmployeeDocumentTemplate;
import t6proj.employees.communication.http.templates.EditEmployeeTemplate;
import t6proj.employees.dto.Employee;
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
    public ResponseEntity<String> saveEmployee(
            @ModelAttribute EmployeeDocument request
    ) {
        var employeeDocumentForm = new EmployeeDocumentForm(request.id, request.employeeId);
        employeeDocumentForm.hydrateFromRequest(request);

        if (this.webFormService.isFormValid(employeeDocumentForm)) {
            this.employeesService.saveEmployeeDocument(request);
            this.addSuccessMessage("Employee document saved");
            return this.redirect("/employees/" + request.employeeId + "/view");
        }

        return ResponseEntity.ok(
                this.renderTemplate(
                        new EditEmployeeDocumentTemplate(
                                this.layoutFactory.createAuthorizedAdminLayout("Edit Document"),
                                this.renderForm(employeeDocumentForm)
                        )
                )
        );
    }
}
