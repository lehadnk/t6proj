package t6proj.user.communication.http.controller;

import adminlte.authentication.AuthenticationServiceInterface;
import adminlte.entity_list_table.EntityListTableService;
import adminlte.flash_message.FlashMessageService;
import adminlte.html_controller.business.AbstractHtmlController;
import adminlte.html_controller.communication.http.layout.LayoutFactory;
import adminlte.html_template_renderer.HtmlTemplateRendererService;
import adminlte.session.SessionServiceInterface;
import adminlte.web_form.WebFormService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import t6proj.authorization.communication.http.RequiresAuthorizedUser;
import t6proj.user.UserService;
import t6proj.user.communication.http.form.UserForm;
import t6proj.user.communication.http.table.UserTable;
import t6proj.user.communication.http.templates.EditUserTemplate;
import t6proj.user.communication.http.templates.UserListTemplate;
import t6proj.user.dto.User;

@RequestMapping("/users/")
@Controller
public class UserController extends AbstractHtmlController {
    private final UserService userService;

    public UserController(
            LayoutFactory layoutFactory,
            HtmlTemplateRendererService htmlTemplateRendererService,
            WebFormService webFormService,
            EntityListTableService entityListTableService,
            SessionServiceInterface sessionService,
            AuthenticationServiceInterface authenticationService,
            FlashMessageService flashMessageService,
            UserService userService
    ) {
        super(layoutFactory, htmlTemplateRendererService, webFormService, entityListTableService, sessionService, authenticationService, flashMessageService);
        this.userService = userService;
    }

    @GetMapping("/")
    @ResponseBody
    @RequiresAuthorizedUser
    public String getUserList(
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        var userList = this.userService.getUserList(page, 20);

        return this.renderTemplate(
                new UserListTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Список пользователей"),
                        this.renderTable(
                                new UserTable(userList)
                        )
                )
        );
    }

    @GetMapping("/create")
    @ResponseBody
    @RequiresAuthorizedUser
    public String createUser()
    {
        var userForm = new UserForm();

        return this.renderTemplate(
                new EditUserTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Создание пользователя"),
                        this.renderForm(userForm)
                )
        );
    }

    @GetMapping("/{id}/edit")
    @ResponseBody
    @RequiresAuthorizedUser
    public String editUser(
            @PathVariable("id") Integer id
    ) {
        var user = this.userService.getUserById(id);
        var userForm = new UserForm();
        userForm.hydrateFromRequest(user);

        return this.renderTemplate(
                new EditUserTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Редактирование пользователя"),
                        this.renderForm(userForm)
                )
        );
    }

    @PostMapping("/save")
    @ResponseBody
    @RequiresAuthorizedUser
    public ResponseEntity<String> saveUser(
            @ModelAttribute User user
    ) {
        var userForm = new UserForm();
        userForm.hydrateFromRequest(user);

        if (this.isFormValid(userForm)) {
            this.userService.saveUser(user);
            this.addSuccessMessage("Пользователь сохранен");
            return this.redirect("/users/");
        } else {
            this.addErrorMessage("Ошибка при сохранении пользователя");
        }

        return ResponseEntity.ok(
                    this.renderTemplate(
                    new EditUserTemplate(
                            this.layoutFactory.createAuthorizedAdminLayout("Редактирование пользователя"),
                            this.renderForm(userForm)
                    )
            )
        );
    }
}
