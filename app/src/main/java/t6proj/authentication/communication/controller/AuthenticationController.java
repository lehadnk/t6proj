package t6proj.authentication.communication.controller;

import adminlte.entity_list_table.EntityListTableService;
import adminlte.flash_message.FlashMessageService;
import adminlte.html_controller.business.AbstractHtmlController;
import adminlte.html_controller.communication.http.layout.LayoutFactory;
import adminlte.html_template_renderer.HtmlTemplateRendererService;
import adminlte.session.SessionServiceInterface;
import adminlte.web_form.WebFormService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import t6proj.authentication.AuthenticationService;
import t6proj.authentication.communication.forms.LoginForm;
import t6proj.authentication.communication.templates.AdminLoginPageTemplate;
import t6proj.authentication.dto.AuthenticationRequest;

@Controller
public class AuthenticationController extends AbstractHtmlController {
    private AuthenticationService service;

    public AuthenticationController(
            LayoutFactory layoutFactory,
            HtmlTemplateRendererService htmlTemplateRendererService,
            WebFormService webFormService,
            EntityListTableService entityListTableService,
            SessionServiceInterface sessionService,
            AuthenticationService authenticationService,
            FlashMessageService flashMessageService
    ) {
        super(layoutFactory, htmlTemplateRendererService, webFormService, entityListTableService, sessionService, authenticationService, flashMessageService);
        this.service = authenticationService;
    }

    @GetMapping("/login")
    @ResponseBody
    public String getLoginPage()
    {
        var loginForm = new LoginForm();

        return this.renderTemplate(
                new AdminLoginPageTemplate(
                        this.layoutFactory.createGeneralLayout(),
                        this.webFormService.renderForm(loginForm)
                )
        );
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> postLoginPage(
            @ModelAttribute AuthenticationRequest request,
            HttpServletResponse response
    ) {
        var authenticationResult = this.service.authenticate(request);
        if (authenticationResult.isSuccess) {
            var authCookie = new Cookie("authToken", authenticationResult.authenticationToken);
            response.addCookie(authCookie);

            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, "/dashboard")
                    .body(null);
        } else {
            var loginForm = new LoginForm();
            loginForm.addValidationErrorMessage("Incorrect login and password pair");

            return ResponseEntity.ok().body(
                        this.renderTemplate(
                        new AdminLoginPageTemplate(
                                this.layoutFactory.createGeneralLayout(),
                                this.webFormService.renderForm(loginForm)
                        )
                )
            );
        }
    }

    public String redirect()
    {
        return "forward:/dashboard";
    }
}
