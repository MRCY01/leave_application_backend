package com.example.leaveApp.controller;

import com.example.leaveApp.exception.ServiceException;
import com.example.leaveApp.reqres.ServiceErrorResponse;
import com.example.leaveApp.reqres.admin.*;
import com.example.leaveApp.service.admin.ResetPasswordService;
import com.example.leaveApp.service.admin.AdminShowAllService;
import com.example.leaveApp.service.admin.UpdateStatusService;
import com.example.leaveApp.service.admin.CreateEmployeeService;
import com.example.leaveApp.service.admin.UpdateLeaveBalanceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    CreateEmployeeService   createEmployeeService;
    @Autowired
    UpdateStatusService       updateStatusService;
    @Autowired
    UpdateLeaveBalanceService updateLeaveBalanceService;
    @Autowired
    AdminShowAllService       adminShowAllService;
    @Autowired
    ResetPasswordService    resetPasswordService;

    @PostMapping(path = "/admin/createNewUser")
    @SneakyThrows
    public CreateEmployeeResponse newUser(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest, HttpServletRequest httpServletRequest)  {

        return createEmployeeService.addEmployee(createEmployeeRequest);
    }

    @PostMapping(path = "/admin/showAllUser")
    public ShowAllEmployeeResponse showAllEmp(@Valid@RequestBody ShowAllEmployeeRequest showAllEmployeeRequest) {
        return adminShowAllService.showAllEmployee(showAllEmployeeRequest);
    }

    @PostMapping(path = "/admin/assignRole")
    public AssignRoleResponse assignRole(@Valid @RequestBody AssignRoleRequest assignRoleRequest) {
        return updateStatusService.assignRole(assignRoleRequest);
    }

    @PostMapping(path = "/admin/updateHiredStatus")
    public ApproveEmployeeResponse updateStatus(@Valid @RequestBody ApproveEmployeeRequest approveEmployeeRequest) {
        return updateStatusService.updateHiredStatus(approveEmployeeRequest);
    }

    @PostMapping(path = "/admin/assignLeaveBalance")
    public AssignTotalLeaveResponse updateLeave(@Valid @RequestBody AssignTotalLeaveRequest assignTotalLeaveRequest) {
        return updateLeaveBalanceService.createLeave(assignTotalLeaveRequest);
    }

    @PostMapping(path = "/admin/updateLeaveBalance")
    public UpdateLeaveBalanceResponse updateLeaveBalance(@Valid @RequestBody UpdateLeaveBalanceRequest updateLeaveBalanceRequest) {
        return updateLeaveBalanceService.updateLeaveBalance(updateLeaveBalanceRequest);
    }

    @PostMapping(path="/admin/showAllLeaveBalance")
    public ShowAllLeaveBalanceResponse showAllLeaveBalance(@Valid @RequestBody ShowAllLeaveBalanceRequest showAllLeaveBalanceRequest){
        return adminShowAllService.showAllLeaveBalance(showAllLeaveBalanceRequest);
    }
    @PostMapping(path = "/admin/resetPassword")
    public ResetPasswordResponse resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        return resetPasswordService.resetPassword(resetPasswordRequest);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
    public ServiceErrorResponse handleServiceException(ServiceException e) {
        e.printStackTrace();
        ServiceErrorResponse response = new ServiceErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatusCode("400");
        response.setInfo("From Admin API");
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServiceErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ServiceErrorResponse response = new ServiceErrorResponse();
        response.setMessage("Error had been occur");
        response.setStatusCode("400");
        response.setInfo(errors);
        return response;
    }
}
