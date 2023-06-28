package com.example.leaveApp.reqres.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminShowAllDTO {
    private String employeeId;
    private String employeeName;
    private String email;
    private String password;
    private String createdDate;
    private String approvedDate;
    private boolean active;
    private String address;
    private String bod;
    private String maritalStatus;
    private String phone;
    private String role;

}
