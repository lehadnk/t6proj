package t6proj.jobs.communication.http.forms;

import adminlte.web_form.communication.AbstractWebForm;
import adminlte.web_form.communication.form_elements.Hidden;
import adminlte.web_form.communication.form_elements.Input;
import adminlte.web_form.communication.form_elements.Select;
import adminlte.web_form.communication.form_elements.Submit;
import t6proj.jobs.communication.http.forms.validators.ParentDepartmentIdValidator;
import t6proj.jobs.dto.Department;

import java.util.HashMap;
import java.util.List;

public class DepartmentForm extends AbstractWebForm<Department> {
    public DepartmentForm(Integer id, List<Department> departments, ParentDepartmentIdValidator parentDepartmentIdValidator)
    {
        this.elements.put("id", new Hidden().setValue(id != null ? id.toString() : null));
        this.elements.put("title", new Input().setRequired().setLabel("Заголовок"));

        var departmentOptions = new HashMap<String, String>();
        for(var department : departments) {
            departmentOptions.put(department.id.toString(), department.title);
        }
        this.elements.put("parentDepartmentId", new Select(departmentOptions).setNullable(true).setLabel("Родительское подразделение").addValidator(parentDepartmentIdValidator));

        this.submitButton = new Submit("Сохранить");
    }
}
