package t6proj.jobs.communication.http.forms;

import adminlte.web_form.communication.AbstractWebForm;
import adminlte.web_form.communication.form_elements.*;
import t6proj.jobs.dto.Contract;
import t6proj.jobs.dto.Job;

import java.util.HashMap;
import java.util.List;

public class ContractForm extends AbstractWebForm<Contract> {
    public ContractForm(Integer id, Integer employeeId, List<Job> jobs)
    {
        this.elements.put("id", new Hidden().setValue(id != null ? id.toString() : null));
        this.elements.put("employeeId", new Hidden().setValue(id != null ? id.toString() : null));
        this.elements.put("terms", new Textarea().setRequired().setLabel("Условия договора"));
        this.elements.put("startsAt", new DateTime().setLabel("Дата начала"));
        this.elements.put("endsAt", new DateTime().setLabel("Дата окончания"));
        this.elements.put("salary", new Input().setType("number").setLabel("Зарплата"));

        var jobOptions = new HashMap<String, String>();
        for(var job : jobs) {
            jobOptions.put(job.id.toString(), job.title);
        }
        this.elements.put("jobId", new Select(jobOptions).setLabel("Рабочая должность"));

        this.submitButton = new Submit("Сохранить");
    }
}
