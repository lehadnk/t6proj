package t6proj.employees.dto;

import lombok.Setter;

import java.util.Date;

@Setter
public class Employee {
    public Integer id;
    public String firstName;
    public String middleName;
    public String lastName;
    public Date birthdate;
    public Date employedAt;
    public Integer jobId;
    public String jobTitle;
    public Integer departmentId;
    public Integer departmentTitle;
}
