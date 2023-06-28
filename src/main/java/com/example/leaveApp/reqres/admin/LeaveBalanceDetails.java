package com.example.leaveApp.reqres.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveBalanceDetails {

    private String balance;
    private String leaveType;
    private String description;
    private String dateAssigned;
    private String expiryDate;

}
