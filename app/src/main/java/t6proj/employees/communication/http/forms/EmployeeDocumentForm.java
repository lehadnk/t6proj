package t6proj.employees.communication.http.forms;

import adminlte.web_form.communication.AbstractWebForm;
import adminlte.web_form.communication.form_elements.*;
import t6proj.employees.dto.EmployeeDocument;
import t6proj.employees.dto.EmployeeDocumentEnum;

import java.util.HashMap;

public class EmployeeDocumentForm extends AbstractWebForm<EmployeeDocument> {
    public EmployeeDocumentForm(Integer id, Integer employeeId)
    {
        this.elements.put("id", new Hidden().setValue(id != null ? id.toString() : null));
        this.elements.put("employeeId", new Hidden().setValue(employeeId != null ? employeeId.toString() : null));

        var documentTypeOptions = new HashMap<String, String>();
        documentTypeOptions.put(EmployeeDocumentEnum.PASSPORT.name(), "Паспорт");
        documentTypeOptions.put(EmployeeDocumentEnum.TIN.name(), "ИНН");
        documentTypeOptions.put(EmployeeDocumentEnum.SNILS.name(), "СНИЛС");
        documentTypeOptions.put(EmployeeDocumentEnum.EMPLOYMENT_HISTORY.name(), "Трудовая книга");
        this.elements.put("documentType", new Select(documentTypeOptions).setLabel("Тип документа"));

        this.elements.put("url", new Input().setLabel("Ссылка на файл"));
        this.elements.put("issuedBy", new Input().setLabel("Кем выдан"));
        this.elements.put("documentDate", new DateTime().setLabel("Когда выдан"));
        this.elements.put("documentNumber", new Input().setLabel("Номер документа"));

        this.addSubmitButton(new Submit("Сохранить"));
    }
}
