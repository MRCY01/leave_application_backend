package com.example.leaveApp.reqres.empRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRoleResponse {
    private Long empId;
    private Long roleId;
    private Long id;

    private String empName;
    private String role;
}
