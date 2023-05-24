package t6proj.employees.persistence.entity;

import jakarta.persistence.*;
import t6proj.employees.dto.EmployeeRequestStatus;

@Entity
@Table(name="employee_requests")
public class EmployeeRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_request_id_seq")
    @SequenceGenerator(name = "employee_request_id_seq", sequenceName = "employee_request_id_seq", allocationSize = 1)
    @Column(name="id")
    public Integer id;

    @Column(name="employee_id")
    public Integer employeeId;

    @Column(name="opened_at")
    public Integer opened_at;

    @Column(name="status")
    public EmployeeRequestStatus status;

    @Column(name="title")
    public String title;

    @Column(name="text")
    public String text;
}
