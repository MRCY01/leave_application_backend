package com.example.leaveApp.reqres.employee.employeeShowAll;

import com.example.leaveApp.entity.Application;
import com.example.leaveApp.reqres.manager.showEmployeeApplication.ApplicationDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowLeaveHistoryResponse {
    private List<ApplicationDetails> applicationList;
}
