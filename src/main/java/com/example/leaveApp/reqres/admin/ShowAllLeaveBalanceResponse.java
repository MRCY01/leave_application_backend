package com.example.leaveApp.reqres.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowAllLeaveBalanceResponse {
    // private Long leaveBalanceId;
    // private String balance;
    // private String dateAssigned;
    // private String description;
    // private String expiredDate;
    // private String leaveType;
    // List<Map<String, String>> leaveBalanceList;
    // List<LeaveBalanceDetails> leaveBalanceList;
    String                         message;
    List<AdminShowLeaveBalanceDTO> adminShowLeaveBalanceDTOList;
}
