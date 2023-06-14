package com.example.leaveApp.service.employee;

import com.example.leaveApp.entity.*;
import com.example.leaveApp.repo.*;
import com.example.leaveApp.reqres.employee.applyLeave.ApplyLeaveRequest;
import com.example.leaveApp.reqres.employee.applyLeave.ApplyLeaveResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class ApplyLeaveService {
    @Autowired
    EmployeeRepository     employeeRepository;
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;
    @Autowired
    LeaveRepository        leaveRepository;
    @Autowired
    ApplicationRepository  applicationRepository;
    @Autowired
    LeaveTypeRepository    leaveTypeRepository;

    @SneakyThrows
    public ApplyLeaveResponse createLeaveApplication(ApplyLeaveRequest newLeaveApplicationReq) {

        ApplyLeaveResponse newLeaveApplicationRes = new ApplyLeaveResponse();
        String status = "Manager Pending";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(formatter).toString();
        try {

            Employee employee = employeeRepository.findById(newLeaveApplicationReq.getEmpId())
                    .orElseThrow(() -> new EntityNotFoundException("employee not found"));
            Employee manager = employeeRepository.findById(newLeaveApplicationReq.getManagerId())
                    .orElseThrow(() -> new EntityNotFoundException("manager not found"));

            Application application = new Application();
            application.setManagerId(manager);
            application.setEmpId(employee);
            application.setManagerApprove(newLeaveApplicationReq.isManagerApproved());
            application.setStatus(status);
            application.setSubmitDate(time);

            application.setLeave(
                    newLeaveApplicationReq.getApplicationDetails().stream().map(
                            applicationItem -> {
                                Leave leave = new Leave();
                                leave.setReason(applicationItem.getReason());
                                leave.setApplyDate(applicationItem.getLeaveApply());
                                LeaveType leaveType = leaveTypeRepository.findById(applicationItem.getLeaveTypeId())
                                        .orElseThrow(() -> new EntityNotFoundException());
                                LeaveBalance leaveBalance = leaveBalanceRepository.findById(applicationItem.getLeaveTypeId())
                                        .orElseThrow(() -> new EntityNotFoundException());
                                leave.setLeaveBalance(leaveBalance);
                                leaveRepository.save(leave);
                                leave.setApplication(application);
                                applicationRepository.save(application);
                                return leave;
                            }
                    ).collect(Collectors.toList())
            );


        }catch (EntityNotFoundException e) {
            System.out.println(e);
            throw e;
        }catch (Exception e) {
            System.out.println(e);
            throw e;
        }

        newLeaveApplicationRes.setDateSubmit(time);
        newLeaveApplicationRes.setStatus(status);

        return newLeaveApplicationRes;
    }
}
