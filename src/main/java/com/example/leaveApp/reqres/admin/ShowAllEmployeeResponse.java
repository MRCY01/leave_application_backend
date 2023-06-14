package com.example.leaveApp.reqres.admin;

import com.example.leaveApp.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowAllEmployeeResponse {
    public List<Map<String, Object>> employeeList;
    private String message;
}
