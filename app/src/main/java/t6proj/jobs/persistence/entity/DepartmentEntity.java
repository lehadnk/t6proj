package t6proj.jobs.persistence.entity;

import jakarta.persistence.*;
import t6proj.jobs.dto.Department;

@Entity
@Table(name="departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_seq")
    @SequenceGenerator(name = "department_id_seq", sequenceName = "department_id_seq", allocationSize = 1)
    @Column(name="id")
    public Integer id;

    @Column(name="title")
    public String title;

    @Column(name="parent_department_id")
    public Integer parentDepartmentId;
}
