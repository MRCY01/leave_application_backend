package com.example.leaveApp.controller;

import com.example.leaveApp.reqres.ServiceErrorResponse;
import com.example.leaveApp.reqres.manager.ApproveLeaveRequest;
import com.example.leaveApp.reqres.manager.ApproveLeaveResponse;
import com.example.leaveApp.service.manager.ApproveLeaveService;
import com.example.leaveApp.reqres.manager.showEmployeeApplication.ShowEmployeeApplicationRequest;
import com.example.leaveApp.reqres.manager.showEmployeeApplication.ShowEmployeeApplicationResponse;
import com.example.leaveApp.service.manager.ManagerShowAllService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManagerController {

    @Autowired
    ManagerShowAllService managerShowAllService;
    @Autowired
    ApproveLeaveService approveLeaveService;
    @PostMapping(path = "/manager/showEmployeeLeave")
    @SneakyThrows
    public ShowEmployeeApplicationResponse showEmployeeApplication(@Valid @RequestBody ShowEmployeeApplicationRequest showEmployeeApplicationRequest)  {
        return managerShowAllService.showEmployeeApplication(showEmployeeApplicationRequest);
    }

    @PostMapping(path = "/manager/approveLeave")
    @SneakyThrows
    public ApproveLeaveResponse approveLeave (@Valid@RequestBody ApproveLeaveRequest approveLeaveRequest){
        return approveLeaveService.approveLeave(approveLeaveRequest);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ServiceErrorResponse handleException(Exception ex) {
        ServiceErrorResponse response = new ServiceErrorResponse();
        response.setMessage(ex.getMessage());
        response.setStatusCode("404");
        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ServiceErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
        ServiceErrorResponse response = new ServiceErrorResponse();
        response.setMessage(ex.getMessage());
        response.setStatusCode("404");
        return response;
    }
}
