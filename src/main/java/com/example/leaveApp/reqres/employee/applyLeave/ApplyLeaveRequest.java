package com.example.leaveApp.reqres.employee.applyLeave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyLeaveRequest {
    private Long empId;
    private Long managerId;
    private Long applicationId;
    private boolean managerApproved;
    private Date dateSubmit;
    private List<ApplicationItem> applicationDetails;

}
