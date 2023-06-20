package com.example.leaveApp.reqres.admin;

import com.example.leaveApp.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignRoleRequest {
    private Long empId;
    private Long roleId;
    private Employee user;
    private String token;
}
