package com.example.leaveApp.service.manager;

import com.example.leaveApp.entity.Application;
import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.Leave;
import com.example.leaveApp.repo.ApplicationRepository;
import com.example.leaveApp.repo.LeaveRepository;
import com.example.leaveApp.reqres.manager.showEmployeeApplication.ApplicationDetails;
import com.example.leaveApp.reqres.manager.showEmployeeApplication.LeaveDetails;
import com.example.leaveApp.reqres.manager.showEmployeeApplication.ShowEmployeeApplicationRequest;
import com.example.leaveApp.reqres.manager.showEmployeeApplication.ShowEmployeeApplicationResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerShowAllService {
    @Autowired
    LeaveRepository leaveRepository;
    @Autowired
    ApplicationRepository applicationRepository;

    @SneakyThrows
    public ShowEmployeeApplicationResponse showEmployeeApplication (ShowEmployeeApplicationRequest showEmployeeApplicationRequest){
        ShowEmployeeApplicationResponse showEmployeeApplicationResponse = new ShowEmployeeApplicationResponse();
        // List<Application> applicationList = applicationRepository.findAll();
        String managerId = showEmployeeApplicationRequest.getManagerId();
        Employee manager = new Employee();
        manager.setId(Long.parseLong(managerId));
        List<Application> applicationList = applicationRepository.findByManagerId(manager);
        List<ApplicationDetails> applicationDetailsList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try{
            for(Application application: applicationList){
                ApplicationDetails applicationDetails = new ApplicationDetails();
                applicationDetails.setAppId(application.getId().toString());
                applicationDetails.setStatus(application.getStatus());
                applicationDetails.setSubmitDate(application.getSubmitDate());
                applicationDetails.setManagerApprove(application.getManagerApprove());

                List<Leave> leaveList= leaveRepository.findByApplicationId(showEmployeeApplicationRequest.getAppId());
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
                applicationDetails.setLeaveDetailsList(leaveDetailsList);

                applicationDetailsList.add(applicationDetails);
            }

        }catch (Exception e){
            System.out.println(e);
            throw e;
        }

        showEmployeeApplicationResponse.setApplicationDetailsList(applicationDetailsList);
        return showEmployeeApplicationResponse;
    }
}
