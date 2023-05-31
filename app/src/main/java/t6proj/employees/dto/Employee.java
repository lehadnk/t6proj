package t6proj.employees.dto;

import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
public class Employee {
    public Integer id;
    public String firstName;
    public String middleName;
    public String lastName;
    public LocalDateTime birthdate;
    public LocalDateTime employedAt;
    public Integer departmentId;
    public Integer departmentTitle;
    public Boolean isDeleted;
}
