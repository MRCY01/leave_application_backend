package com.example.leaveApp.controller;

import com.example.leaveApp.reqres.employee.employeeShowAll.ShowLeaveHistoryRequest;
import com.example.leaveApp.reqres.employee.employeeShowAll.ShowLeaveHistoryResponse;
import com.example.leaveApp.reqres.employee.login.LoginResponse;
import com.example.leaveApp.reqres.ServiceErrorResponse;
import com.example.leaveApp.reqres.employee.applyLeave.ApplyLeaveRequest;
import com.example.leaveApp.reqres.employee.applyLeave.ApplyLeaveResponse;
import com.example.leaveApp.reqres.employee.login.LoginRequest;
import com.example.leaveApp.service.employee.ApplyLeaveService;
import com.example.leaveApp.service.employee.EmployeeShowAllService;
import com.example.leaveApp.service.employee.LoginService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    LoginService loginService;
    @Autowired
    ApplyLeaveService applyLeaveService;
    @Autowired
    EmployeeShowAllService employeeShowAllService;

    @PostMapping(path = "/employee/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }

    @PostMapping(path="/employee/applyLeave")
    @SneakyThrows
    public ApplyLeaveResponse newLeaveApplication (@Valid @RequestBody ApplyLeaveRequest applyLeaveRequest){
        return applyLeaveService.createLeaveApplication(applyLeaveRequest);
    }

    @PostMapping(path="/employee/showLeaveHistory")
    @SneakyThrows
    public ShowLeaveHistoryResponse showLeaveHistory (@Valid @RequestBody ShowLeaveHistoryRequest showLeaveHistoryRequest){
        return employeeShowAllService.showLeaveHistory(showLeaveHistoryRequest);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ServiceErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
        ServiceErrorResponse response = new ServiceErrorResponse();
        response.setMessage(ex.getMessage());
        response.setStatusCode("404");
        response.setInfo("error");
        return response;
    }
}
