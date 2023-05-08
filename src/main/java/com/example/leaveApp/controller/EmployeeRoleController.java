package com.example.leaveApp.controller;

import com.example.leaveApp.reqres.empRole.EmployeeRoleRequest;
import com.example.leaveApp.reqres.empRole.EmployeeRoleResponse;
import com.example.leaveApp.service.EmployeeRoleService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class EmployeeRoleController {
    @Autowired
    EmployeeRoleService employeeRoleService;


    @RequestMapping(path="/empRole/insert", method = RequestMethod.POST)
    public EmployeeRoleResponse insertData(@RequestBody EmployeeRoleRequest employeeRoleRequest){
        return employeeRoleService.insertData(employeeRoleRequest);
    }
}
