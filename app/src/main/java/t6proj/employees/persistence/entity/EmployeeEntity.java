package t6proj.employees.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize = 1)
    @Column(name="id")
    public Integer id;

    @Column(name="job_id")
    public Integer jobId;

    @Column(name="employed_at")
    public Date employedAt;

    @Column(name="birthdate")
    public Date birthdate;

    @Column(name="first_name")
    public Date firstName;

    @Column(name="middle_name")
    public Date middleName;

    @Column(name="last_name")
    public Date lastName;
}
