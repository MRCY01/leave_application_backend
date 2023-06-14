package com.example.leaveApp.reqres.manager.showEmployeeApplication;

import com.example.leaveApp.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDetails {
    private String appId;
    private boolean managerApprove;
    private String status;
    private String submitDate;
    // private Employee employee;
    // private String reason;
    private List<LeaveDetails> leaveDetailsList;
}
