package t6proj.user.communication.http.form;

import adminlte.web_form.communication.AbstractWebForm;
import adminlte.web_form.communication.form_elements.Hidden;
import adminlte.web_form.communication.form_elements.Input;
import adminlte.web_form.communication.form_elements.Submit;
import t6proj.user.dto.User;

public class UserForm extends AbstractWebForm<User> {
    public UserForm()
    {
        this.elements.put("id", new Hidden());
        this.elements.put("email", new Input().setNullable(false).setRequired().setLabel("Email"));
        this.elements.put("password", new Hidden());
        this.elements.put("changePassword", new Input().setLabel("Пароль"));

        this.submitButton = new Submit("Сохранить");
        this.actionUrl = "/users/save";
    }
}
