package t6proj.authentication.communication.forms;

import adminlte.web_form.communication.AbstractWebForm;
import adminlte.web_form.communication.form_elements.Email;
import adminlte.web_form.communication.form_elements.Password;
import adminlte.web_form.communication.form_elements.Submit;
import t6proj.authentication.dto.AuthenticationRequest;

public class LoginForm extends AbstractWebForm<AuthenticationRequest> {
    public LoginForm() {
        this.setSimpleLayout();

        this.addElement(
            "email",
            new Email().setLabel("Email")
        );
        this.addElement(
            "password",
            new Password().setLabel("Password").setAutocompleteCurrentPassword(true)
        );
        this.addSubmitButton(new Submit("Войти").setLength(12));
    }

    @Override
    public String getActionUrl() {
        return "/login";
    }
}
