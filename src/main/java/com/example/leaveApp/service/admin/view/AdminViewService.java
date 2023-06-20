package com.example.leaveApp.service.admin.view;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.exception.ServiceException;
import com.example.leaveApp.jwt.JWTUtil;
import com.example.leaveApp.repo.EmployeeRepository;
import com.example.leaveApp.repo.LeaveBalanceRepository;
import com.example.leaveApp.reqres.admin.ShowAllLeaveBalanceRequest;
import com.example.leaveApp.reqres.admin.ShowAllLeaveBalanceResponse;
import com.example.leaveApp.reqres.admin.ShowAllEmployeeRequest;
import com.example.leaveApp.reqres.admin.ShowAllEmployeeResponse;
import com.example.leaveApp.service.AuthService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminViewService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;
    @Autowired
    AuthService authService;
    @Autowired
    JWTUtil jwtUtil;
    @SneakyThrows
    public ShowAllEmployeeResponse showAllEmployee(ShowAllEmployeeRequest showAllEmployeeRequest){
        ShowAllEmployeeResponse showAllEmployeeResponse = new ShowAllEmployeeResponse();

        try{
            Employee user = showAllEmployeeRequest.getUser();
            if(!authService.hasRole(user,"ADMIN")){
                throw new ServiceException("user is not admin");
            }

            String token = showAllEmployeeRequest.getToken();
            List<String> roleList = authService.getUserRole(token);
            jwtUtil.validateToken(token, jwtUtil.extractUserId(token));

            List<Map<String, Object>> employeeList = employeeRepository.getEmployeeData();
            showAllEmployeeResponse.setEmployeeList(employeeList);
            showAllEmployeeResponse.setMessage("successfully show");
        }catch (ServiceException e){
            throw e;
        }
        return showAllEmployeeResponse;
    }

    @SneakyThrows
    public ShowAllLeaveBalanceResponse showAllLeaveBalance(ShowAllLeaveBalanceRequest showAllLeaveBalanceRequest){
        ShowAllLeaveBalanceResponse showAllLeaveBalanceResponse =  new ShowAllLeaveBalanceResponse();

        try{
            Employee user = showAllLeaveBalanceRequest.getUser();
            if(!authService.hasRole(user,"ADMIN")){
                throw new ServiceException("user is not admin");
            }
            if(leaveBalanceRepository.findAll().isEmpty()){
                throw  new ServiceException("no leave balance record found");
            }
            List<Map<String,Object>>leaveBalanceList= leaveBalanceRepository.findLeaveBalanceData();
            showAllLeaveBalanceResponse.setLeaveBalanceList(leaveBalanceList);

        }catch(ServiceException e){
            throw e;
        }
        return showAllLeaveBalanceResponse;
    }

}
