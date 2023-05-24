package t6proj.employees.communication.http.forms;

import adminlte.web_form.communication.AbstractWebForm;
import adminlte.web_form.communication.form_elements.*;
import t6proj.employees.dto.Employee;
import t6proj.jobs.dto.Job;

import java.util.HashMap;
import java.util.List;

public class EmployeeForm extends AbstractWebForm<Employee> {
    public EmployeeForm(Integer id, List<Job> jobs)
    {
        this.elements.put("id", new Hidden().setValue(id != null ? id.toString() : null));
        this.elements.put("firstName", new Input().setRequired().setLabel("Имя"));
        this.elements.put("middleName", new Input().setRequired().setLabel("Отчество"));
        this.elements.put("lastName", new Input().setRequired().setLabel("Фамилия"));
        this.elements.put("birthdate", new DateTime().setRequired().setLabel("Дата рождения"));
        this.elements.put("employedAt", new DateTime().setRequired().setLabel("Дата трудоустройства"));

        var jobOptions = new HashMap<String, String>();
        for (var job : jobs) {
            jobOptions.put(job.id.toString(), job.title);
        }
        this.elements.put("jobId", new Select(jobOptions).setLabel("Работа"));

        this.submitButton = new Submit("Сохранить");
    }
}
