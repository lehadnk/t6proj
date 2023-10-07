package t6proj.jobs.communication.http.forms;

import adminlte.web_form.communication.AbstractWebForm;
import adminlte.web_form.communication.form_elements.Hidden;
import adminlte.web_form.communication.form_elements.Input;
import adminlte.web_form.communication.form_elements.Select;
import adminlte.web_form.communication.form_elements.Submit;
import t6proj.jobs.dto.Department;
import t6proj.jobs.dto.Job;

import java.util.HashMap;
import java.util.List;

public class JobForm extends AbstractWebForm<Job> {
    public JobForm(Integer id, List<Department> departments)
    {
        this.elements.put("id", new Hidden().setValue(id != null ? id.toString() : null));
        this.elements.put("title", new Input().setRequired().setLabel("Заголовок"));

        var departmentOptions = new HashMap<String, String>();
        for (var department : departments) {
            departmentOptions.put(department.id.toString(), department.title);
        }
        this.elements.put("departmentId", new Select(departmentOptions).setLabel("Отдел"));

        this.addSubmitButton(new Submit("Сохранить"));
    }
}
