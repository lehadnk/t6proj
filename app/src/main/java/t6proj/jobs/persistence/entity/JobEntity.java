package t6proj.jobs.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name="jobs")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_id_seq")
    @SequenceGenerator(name = "job_id_seq", sequenceName = "job_id_seq", allocationSize = 1)
    @Column(name="id")
    public Integer id;

    @Column(name="title")
    public String title;

    @Column(name="department_id")
    public String departmentId;
}
