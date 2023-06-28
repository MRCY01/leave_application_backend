package com.example.leaveApp.controller;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.exception.ServiceException;
import com.example.leaveApp.jwt.JWTUtil;
import com.example.leaveApp.reqres.ServiceErrorResponse;
import com.example.leaveApp.reqres.admin.*;
import com.example.leaveApp.service.AuthService;
import com.example.leaveApp.service.admin.create.CreateLeaveService;
import com.example.leaveApp.service.admin.update.ResetPasswordService;
import com.example.leaveApp.service.admin.view.AdminViewService;
import com.example.leaveApp.service.admin.update.UpdateStatusService;
import com.example.leaveApp.service.admin.create.CreateEmployeeService;
import com.example.leaveApp.service.admin.update.UpdateLeaveBalanceService;
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
    AdminViewService          adminViewService;
    @Autowired
    ResetPasswordService      resetPasswordService;
    @Autowired
    CreateLeaveService createLeaveService;
    @Autowired
    AuthService authService;
    @Autowired
    JWTUtil jwtUtil;

    @PostMapping(path = "/admin/user/create")
    @SneakyThrows
    public CreateEmployeeResponse newUser(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest,
                                          @RequestHeader("Authorization") String header)  {

        // HttpHeaders headers = new HttpHeaders();
        // headers.add("Custom-Header", "Header Value");
        String token = createEmployeeRequest.getToken();
        Employee user = authService.getUser(token);
        createEmployeeRequest.setUser(user);
        return createEmployeeService.createEmployee(createEmployeeRequest);
    }

    @PostMapping(path = "/admin/user/showAll")
    public ShowAllEmployeeResponse showAllEmp(@Valid@RequestBody ShowAllEmployeeRequest showAllEmployeeRequest,
                                              @RequestHeader("Authorization") String authorizationHeader) {
        // HttpHeaders headers = new HttpHeaders();
        // headers.add("Custom-Header", "Header Value");
        String token = showAllEmployeeRequest.getToken();
        Employee user = authService.getUser(token);
        showAllEmployeeRequest.setUser(user);
        return adminViewService.showAllEmployee(showAllEmployeeRequest);
    }

    @PostMapping(path = "/admin/role/assign")
    public AssignRoleResponse assignRole(@Valid @RequestBody AssignRoleRequest assignRoleRequest) {
        String token = assignRoleRequest.getToken();
        Employee user = authService.getUser(token);
        assignRoleRequest.setUser(user);
        return updateStatusService.assignRole(assignRoleRequest);
    }

    @PostMapping(path = "/admin/hiredStatus/update")
    public ApproveEmployeeResponse updateStatus(@Valid @RequestBody ApproveEmployeeRequest approveEmployeeRequest) {
        String token = approveEmployeeRequest.getToken();
        Employee user = authService.getUser(token);
        approveEmployeeRequest.setUser(user);
        return updateStatusService.updateHiredStatus(approveEmployeeRequest);
    }

    @PostMapping(path = "/admin/leave/create")
    public AssignTotalLeaveResponse updateLeave(@Valid @RequestBody AssignTotalLeaveRequest assignTotalLeaveRequest) {
        String token = assignTotalLeaveRequest.getToken();
        Employee user = authService.getUser(token);
        assignTotalLeaveRequest.setUser(user);
        return createLeaveService.createLeave(assignTotalLeaveRequest);
    }

    @PostMapping(path = "/admin/leaveBalance/update")
    public UpdateLeaveBalanceResponse updateLeaveBalance(@Valid @RequestBody UpdateLeaveBalanceRequest updateLeaveBalanceRequest) {
        String token = updateLeaveBalanceRequest.getToken();
        Employee user = authService.getUser(token);
        updateLeaveBalanceRequest.setUser(user);
        return updateLeaveBalanceService.updateLeaveBalance(updateLeaveBalanceRequest);
    }

    @PostMapping(path="/admin/leaveBalance/all/show")
    public ShowAllLeaveBalanceResponse showAllLeaveBalance(@Valid @RequestBody ShowAllLeaveBalanceRequest showAllLeaveBalanceRequest){
        String token = showAllLeaveBalanceRequest.getToken();
        Employee user = authService.getUser(token);
        showAllLeaveBalanceRequest.setUser(user);
        return adminViewService.showAllLeaveBalance(showAllLeaveBalanceRequest);
    }
    @PostMapping(path = "/admin/Password/reset")
    public ResetPasswordResponse resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        String token = resetPasswordRequest.getToken();
        Employee user = authService.getUser(token);
        resetPasswordRequest.setUser(user);
        return resetPasswordService.resetPassword(resetPasswordRequest);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
    public ServiceErrorResponse handleServiceException(ServiceException e) {
        e.printStackTrace();
        ServiceErrorResponse response = new ServiceErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatusCode("400");
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
        return response;
    }
}
