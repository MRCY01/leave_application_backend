package com.example.leaveApp.reqres.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminShowLeaveBalanceDTO {
    private String employeeId;
    private String employeeName;
    private LeaveBalanceDetails leaveBalanceDetails;
}
