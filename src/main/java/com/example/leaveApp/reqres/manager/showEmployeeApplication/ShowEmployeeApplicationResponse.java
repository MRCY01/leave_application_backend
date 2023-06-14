package com.example.leaveApp.reqres.manager.showEmployeeApplication;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.Leave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowEmployeeApplicationResponse {
    // private String appId;
    // private String status;
    // private String submitDate;
    // private boolean isManagerApprove;
    // private Employee employee;
    private List<ApplicationDetails> applicationDetailsList;
}
