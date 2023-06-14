package com.example.leaveApp.reqres.manager.showEmployeeApplication;

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
}
