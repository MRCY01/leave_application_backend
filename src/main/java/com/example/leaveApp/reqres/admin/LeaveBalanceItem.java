package com.example.leaveApp.reqres.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveBalanceItem {
//    private String leaveType;
    private String balance;
    private Long leaveTypeId;
    private String description;
    private String dateExpired;
}
