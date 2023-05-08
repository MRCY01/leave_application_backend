package com.example.leaveApp.controller;

import com.example.leaveApp.reqres.createEmp.CreateEmployeeRequest;
import com.example.leaveApp.reqres.employee.EmployeeRequest;
import com.example.leaveApp.reqres.employee.EmployeeResponse;
import com.example.leaveApp.service.CreateEmployeeService;
import com.example.leaveApp.service.EmployeeService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CreateEmployeeService createEmployeeService;
    @PostMapping(path = "/test")
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {

         return employeeService.addEmployee(employeeRequest);
    }

    @PostMapping(path = "/employee/create")
    public void createNewEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {

         createEmployeeService.createNewEmployee(createEmployeeRequest);
    }
}
