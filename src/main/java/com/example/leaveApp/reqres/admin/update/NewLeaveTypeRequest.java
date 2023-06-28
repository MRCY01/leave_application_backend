package com.example.leaveApp.reqres.admin.update;

import com.example.leaveApp.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewLeaveTypeRequest {
    private Employee user;
    private String newLeaveType;
    private boolean active;
    private String token;
}
