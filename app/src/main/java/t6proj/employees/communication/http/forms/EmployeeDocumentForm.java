package t6proj.employees.communication.http.forms;

import adminlte.web_form.communication.AbstractWebForm;
import adminlte.web_form.communication.form_elements.Hidden;
import adminlte.web_form.communication.form_elements.Input;
import adminlte.web_form.communication.form_elements.Select;
import adminlte.web_form.communication.form_elements.Submit;
import t6proj.employees.dto.EmployeeDocument;
import t6proj.employees.dto.EmployeeDocumentEnum;

import java.util.HashMap;

public class EmployeeDocumentForm extends AbstractWebForm<EmployeeDocument> {
    public EmployeeDocumentForm(Integer id, Integer employeeId)
    {
        this.elements.put("id", new Hidden().setValue(id != null ? id.toString() : null));
        this.elements.put("employeeId", new Hidden().setValue(employeeId != null ? employeeId.toString() : null));

        var documentTypeOptions = new HashMap<String, String>();
        documentTypeOptions.put(EmployeeDocumentEnum.PASSPORT.name(), "Passport");
        documentTypeOptions.put(EmployeeDocumentEnum.TIN.name(), "TIN");
        documentTypeOptions.put(EmployeeDocumentEnum.SNILS.name(), "SNILS");
        documentTypeOptions.put(EmployeeDocumentEnum.EMPLOYMENT_HISTORY.name(), "Employment History");
        this.elements.put("documentType", new Select(documentTypeOptions).setLabel("Document Type"));

        this.elements.put("url", new Input().setRequired().setLabel("URL"));

        this.submitButton = new Submit("Save");
    }
}
