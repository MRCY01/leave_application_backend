package com.example.leaveApp.reqres.manager.showEmployeeApplication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDetails {
    private String reason;
    private String leaveId;
    private String applyDate;
    // private boolean isFullDay;
    private String leaveBalanceId;

    private String leaveTypeName;


}
