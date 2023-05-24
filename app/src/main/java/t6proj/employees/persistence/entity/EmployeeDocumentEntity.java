package t6proj.employees.persistence.entity;

import jakarta.persistence.*;
import t6proj.employees.dto.EmployeeDocumentEnum;

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
}
