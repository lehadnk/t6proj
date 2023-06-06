package t6proj.employees.persistence.entity;

import jakarta.persistence.*;
import t6proj.employees.dto.EmployeeDocumentEnum;

import java.util.Date;

@Entity
@Table(name="employee_documents")
public class EmployeeDocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_document_id_seq")
    @SequenceGenerator(name = "employee_document_id_seq", sequenceName = "employee_document_id_seq", allocationSize = 1)
    @Column(name="id")
    public Integer id;

    @Column(name="employee_id")
    public Integer employeeId;

    @Column(name="document_type")
    public EmployeeDocumentEnum documentType;

    @Column(name="url")
    public String url;

    @Column(name="document_number")
    public String documentNumber;

    @Column(name="issued_at")
    public Date issuedAt;

    @Column(name="valid_by")
    public Date validBy;

    @Column(name="issued_by")
    public String issuedBy;
}
