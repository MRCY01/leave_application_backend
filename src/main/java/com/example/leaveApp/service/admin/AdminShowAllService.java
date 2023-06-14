package com.example.leaveApp.service.admin;

import com.example.leaveApp.exception.ServiceException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AdminShowAllService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;
    @Autowired
    AuthService authService;
    @SneakyThrows
    public ShowAllEmployeeResponse showAllEmployee(ShowAllEmployeeRequest showAllEmployeeRequest){
        ShowAllEmployeeResponse showAllEmployeeResponse = new ShowAllEmployeeResponse();

        List<String> roleList = authService.getUserRole(showAllEmployeeRequest.getToken());
        if(!roleList.contains("ADMIN")){
            showAllEmployeeResponse.setMessage("you are not admin");
            throw new ServiceException("you are not admin");
        }
        List<Map<String, Object>> employeeList = employeeRepository.getEmployeeData();
        showAllEmployeeResponse.setEmployeeList(employeeList);
        showAllEmployeeResponse.setMessage("successfully show");
        return showAllEmployeeResponse;
    }

    public ShowAllLeaveBalanceResponse showAllLeaveBalance(ShowAllLeaveBalanceRequest showAllLeaveBalanceRequest){
        ShowAllLeaveBalanceResponse showAllLeaveBalanceResponse =  new ShowAllLeaveBalanceResponse();

        List<Map<String,Object>>leaveBalanceList= leaveBalanceRepository.findLeaveBalanceData();
        showAllLeaveBalanceResponse.setLeaveBalanceList(leaveBalanceList);
        return showAllLeaveBalanceResponse;
    }

}
