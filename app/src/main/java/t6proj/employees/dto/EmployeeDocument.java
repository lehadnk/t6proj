package t6proj.employees.dto;

import lombok.Setter;

@Setter
public class EmployeeDocument {
    public Integer id;
    public EmployeeDocumentEnum documentType;
    public String url;
    public Integer employeeId;
}
