package t6proj.jobs.communication.http.forms.validators;

import adminlte.web_form.communication.validators.AbstractFormValidator;
import adminlte.web_form.dto.ValidationResult;
import t6proj.jobs.JobsService;

public class ParentDepartmentIdValidator extends AbstractFormValidator<String> {
    private final Integer departmentId;
    private final JobsService jobsService;

    public ParentDepartmentIdValidator(Integer departmentId, JobsService jobsService) {
        this.departmentId = departmentId;
        this.jobsService = jobsService;
    }

    public ValidationResult validate(String s) {
        if (this.departmentId == null) {
            return this.successValidationResult();
        }

        if (this.jobsService.isChildToDepartment(this.departmentId, Integer.valueOf(s))) {
            return this.errorValidationResult("Родителем отдела не может быть собственный потомок");
        }

        return this.successValidationResult();
    }
}
