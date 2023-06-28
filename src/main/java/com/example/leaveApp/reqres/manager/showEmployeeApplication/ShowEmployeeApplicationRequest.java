package com.example.leaveApp.reqres.manager.showEmployeeApplication;

import com.example.leaveApp.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowEmployeeApplicationRequest {
    private String appId;
    private String managerId;

    private String token;
    private Employee user;
}
