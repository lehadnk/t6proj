package t6proj.employees.dto;

import lombok.Setter;

import java.time.LocalDateTime;


@Setter
public class EmployeeDocument {
    public Integer id;
    public EmployeeDocumentEnum documentType;
    public String url;
    public Integer employeeId;
    public String documentNumber;
    public LocalDateTime issuedAt;
    public LocalDateTime validBy;
    public String issuedBy;
}
