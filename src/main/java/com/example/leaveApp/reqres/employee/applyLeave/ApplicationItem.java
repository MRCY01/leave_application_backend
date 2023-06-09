package com.example.leaveApp.reqres.employee.applyLeave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationItem {
    private Date leaveApply;
    private Long leaveTypeId;
    private String reason;
    private boolean checkFullDay;
}
