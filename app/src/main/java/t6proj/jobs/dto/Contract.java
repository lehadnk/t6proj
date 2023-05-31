package t6proj.jobs.dto;

import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class Contract {
    public Integer id;
    public Integer employeeId;
    public Integer jobId;
    public Double salary;
    public LocalDateTime startsAt;
    public LocalDateTime endsAt;
    public String terms;
}
