package com.example.leaveApp.reqres.createEmp;

import com.example.leaveApp.entity.LeaveBalance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {
    private String empName;
    private String role;
    private Long roleId;
    private String status;
    private Date dateCreated;
    private ArrayList<LeaveBalance> leaveBalanceList;

}
