package com.example.leaveApp.reqres.admin;

import com.example.leaveApp.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignTotalLeaveRequest {
    private Long empId;
    private List<LeaveBalanceItem> LeaveBalanceList;
    private String token;
    private Employee user;
}
