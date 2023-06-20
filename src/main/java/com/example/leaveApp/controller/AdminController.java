package com.example.leaveApp.controller;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.exception.ServiceException;
import com.example.leaveApp.jwt.JWTUtil;
import com.example.leaveApp.reqres.ServiceErrorResponse;
import com.example.leaveApp.reqres.admin.*;
import com.example.leaveApp.service.AuthService;
import com.example.leaveApp.service.admin.ResetPasswordService;
import com.example.leaveApp.service.admin.AdminShowAllService;
import com.example.leaveApp.service.admin.UpdateStatusService;
import com.example.leaveApp.service.admin.CreateEmployeeService;
import com.example.leaveApp.service.admin.UpdateLeaveBalanceService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    @Autowired
    AuthService authService;
    @Autowired
    JWTUtil jwtUtil;

    @PostMapping(path = "/admin/createNewUser")
    @SneakyThrows
    public CreateEmployeeResponse newUser(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest,
                                          @RequestHeader("Authorization") String header)  {

        // HttpHeaders headers = new HttpHeaders();
        // headers.add("Custom-Header", "Header Value");
        String token = createEmployeeRequest.getToken();
        Employee user = authService.getUser(token);
        createEmployeeRequest.setUser(user);
        return createEmployeeService.addEmployee(createEmployeeRequest);
    }

    @PostMapping(path = "/admin/showAllUser")
    public ShowAllEmployeeResponse showAllEmp(@Valid@RequestBody ShowAllEmployeeRequest showAllEmployeeRequest,
                                              @RequestHeader("Authorization") String authorizationHeader) {
        // HttpHeaders headers = new HttpHeaders();
        // headers.add("Custom-Header", "Header Value");
        String token = showAllEmployeeRequest.getToken();
        Employee user = authService.getUser(token);
        showAllEmployeeRequest.setUser(user);
        return adminShowAllService.showAllEmployee(showAllEmployeeRequest);
    }

    @PostMapping(path = "/admin/assignRole")
    public AssignRoleResponse assignRole(@Valid @RequestBody AssignRoleRequest assignRoleRequest) {
        String token = assignRoleRequest.getToken();
        Employee user = authService.getUser(token);
        assignRoleRequest.setUser(user);
        return updateStatusService.assignRole(assignRoleRequest);
    }

    @PostMapping(path = "/admin/updateHiredStatus")
    public ApproveEmployeeResponse updateStatus(@Valid @RequestBody ApproveEmployeeRequest approveEmployeeRequest) {
        String token = approveEmployeeRequest.getToken();
        Employee user = authService.getUser(token);
        approveEmployeeRequest.setUser(user);
        return updateStatusService.updateHiredStatus(approveEmployeeRequest);
    }

    @PostMapping(path = "/admin/assignLeaveBalance")
    public AssignTotalLeaveResponse updateLeave(@Valid @RequestBody AssignTotalLeaveRequest assignTotalLeaveRequest) {
        String token = assignTotalLeaveRequest.getToken();
        Employee user = authService.getUser(token);
        assignTotalLeaveRequest.setUser(user);
        return updateLeaveBalanceService.createLeave(assignTotalLeaveRequest);
    }

    @PostMapping(path = "/admin/updateLeaveBalance")
    public UpdateLeaveBalanceResponse updateLeaveBalance(@Valid @RequestBody UpdateLeaveBalanceRequest updateLeaveBalanceRequest) {
        String token = updateLeaveBalanceRequest.getToken();
        Employee user = authService.getUser(token);
        updateLeaveBalanceRequest.setUser(user);
        return updateLeaveBalanceService.updateLeaveBalance(updateLeaveBalanceRequest);
    }

    @PostMapping(path="/admin/showAllLeaveBalance")
    public ShowAllLeaveBalanceResponse showAllLeaveBalance(@Valid @RequestBody ShowAllLeaveBalanceRequest showAllLeaveBalanceRequest){
        String token = showAllLeaveBalanceRequest.getToken();
        Employee user = authService.getUser(token);
        showAllLeaveBalanceRequest.setUser(user);
        return adminShowAllService.showAllLeaveBalance(showAllLeaveBalanceRequest);
    }
    @PostMapping(path = "/admin/resetPassword")
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
