package com.example.leaveApp.service.employee;

import com.example.leaveApp.entity.Application;
import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.Leave;
import com.example.leaveApp.repo.ApplicationRepository;
import com.example.leaveApp.repo.EmployeeRepository;
import com.example.leaveApp.repo.LeaveRepository;
import com.example.leaveApp.reqres.employee.employeeShowAll.ShowLeaveHistoryRequest;
import com.example.leaveApp.reqres.employee.employeeShowAll.ShowLeaveHistoryResponse;
import com.example.leaveApp.reqres.manager.showEmployeeApplication.ApplicationDetails;
import com.example.leaveApp.reqres.manager.showEmployeeApplication.LeaveDetails;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeShowAllService {
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    LeaveRepository leaveRepository;
    @SneakyThrows
    public ShowLeaveHistoryResponse showLeaveHistory(ShowLeaveHistoryRequest showLeaveHistoryRequest){
        ShowLeaveHistoryResponse response = new ShowLeaveHistoryResponse();
        Employee employee = new Employee();
        employee.setId(Long.parseLong(showLeaveHistoryRequest.getEmpId()));
        List<Application> appList  = applicationRepository.findByEmpId(employee);
        List<ApplicationDetails> applicationDetailsList = new ArrayList<>();
        for(Application application: appList){
            ApplicationDetails applicationDetails = new ApplicationDetails();
            applicationDetails.setAppId(application.getId().toString());
            applicationDetails.setStatus(application.getStatus());
            applicationDetails.setSubmitDate(application.getSubmitDate());
            applicationDetails.setManagerApprove(application.getManagerApprove());

            List<Leave> leaveList = leaveRepository.findByApplicationId(showLeaveHistoryRequest.getEmpId());
            List<LeaveDetails> leaveDetailsList = new ArrayList<>();
            for(Leave l:leaveList){
                LeaveDetails leaveDetails = new LeaveDetails();
                leaveDetails.setReason(l.getReason());
                leaveDetails.setApplyDate(l.getApplyDate().toString());
                leaveDetails.setLeaveBalanceId(l.getLeaveBalance().getId().toString());
                leaveDetails.setLeaveId(l.getId().toString());
                leaveDetails.setLeaveTypeName(l.getLeaveBalance().getLeaveType().getLeaveTypeName());
                leaveDetailsList.add(leaveDetails);
            }
            applicationDetailsList.add(applicationDetails);
            applicationDetails.setLeaveDetailsList(leaveDetailsList);

        }

        response.setApplicationList(applicationDetailsList);

        return response;
    }

    public ShowLeaveHistoryResponse showLeaveBalance(){
        return null;
    }
}
