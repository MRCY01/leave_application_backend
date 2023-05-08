package com.example.leaveApp.reqres.empRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRoleRequest {
    private Long empId;
    private Long roleId;
}
