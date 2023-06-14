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
public class ApplyLeaveResponse {
    private String status;
    private String dateSubmit;
}
