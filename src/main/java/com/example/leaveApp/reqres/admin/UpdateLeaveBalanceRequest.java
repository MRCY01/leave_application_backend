package com.example.leaveApp.reqres.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLeaveBalanceRequest {
    private Long leaveBalanceId ;
    private String balance;
    private String dateExpired;
}
