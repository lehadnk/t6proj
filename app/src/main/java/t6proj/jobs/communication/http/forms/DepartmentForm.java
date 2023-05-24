package t6proj.jobs.communication.http.forms;

import adminlte.web_form.communication.AbstractWebForm;
import adminlte.web_form.communication.form_elements.Hidden;
import adminlte.web_form.communication.form_elements.Input;
import adminlte.web_form.communication.form_elements.Submit;
import t6proj.jobs.dto.Department;

public class DepartmentForm extends AbstractWebForm<Department> {
    public DepartmentForm(Integer id)
    {
        this.elements.put("id", new Hidden().setValue(id != null ? id.toString() : null));
        this.elements.put("title", new Input().setRequired().setLabel("Title"));

        this.submitButton = new Submit("Save");
    }
}
