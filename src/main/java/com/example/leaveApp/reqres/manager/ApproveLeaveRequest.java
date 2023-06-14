package com.example.leaveApp.reqres.manager;

import com.example.leaveApp.entity.Leave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApproveLeaveRequest {
    private String appId;
    private boolean approve;
    // private List<Leave> leaveList;

}
