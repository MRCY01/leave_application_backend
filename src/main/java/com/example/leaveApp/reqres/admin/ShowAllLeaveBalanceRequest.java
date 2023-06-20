package com.example.leaveApp.reqres.admin;

import com.example.leaveApp.entity.Employee;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllLeaveBalanceRequest {
    private String token;
    private Employee user;
}
