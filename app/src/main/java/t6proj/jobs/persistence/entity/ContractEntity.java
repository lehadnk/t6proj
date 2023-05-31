package t6proj.jobs.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="contracts")
public class ContractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contracts_id_seq")
    @SequenceGenerator(name = "contracts_id_seq", sequenceName = "contracts_id_seq", allocationSize = 1)
    @Column(name="id")
    public Integer id;

    @Column(name="employee_id")
    public Integer employeeId;

    @Column(name="job_Id")
    public Integer jobId;

    @Column(name="starts_at")
    public Date startsAt;

    @Column(name="ends_at")
    public Date endsAt;

    @Column(name="salary")
    public Double salary;

    @Column(name="terms")
    public String terms;
}
