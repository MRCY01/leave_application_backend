package com.example.leaveApp.reqres.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeResponse {
    private String message;
    private Long empId;
    private String status;
    // 200/400
//    private Long managerId;
//    private String managerName;
}
