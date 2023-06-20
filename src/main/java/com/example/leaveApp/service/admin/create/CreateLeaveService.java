package com.example.leaveApp.service.admin.create;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.LeaveBalance;
import com.example.leaveApp.entity.LeaveType;
import com.example.leaveApp.exception.ServiceException;
import com.example.leaveApp.repo.EmployeeRepository;
import com.example.leaveApp.repo.LeaveBalanceRepository;
import com.example.leaveApp.repo.LeaveTypeRepository;
import com.example.leaveApp.reqres.admin.AssignTotalLeaveRequest;
import com.example.leaveApp.reqres.admin.AssignTotalLeaveResponse;
import com.example.leaveApp.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class CreateLeaveService {

    @Autowired
    AuthService authService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    LeaveTypeRepository leaveTypeRepository;
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;
    @SneakyThrows
    public AssignTotalLeaveResponse createLeave(AssignTotalLeaveRequest assignTotalLeaveRequest) {


        AssignTotalLeaveResponse assignTotalLeaveResponse = new AssignTotalLeaveResponse();
        try {
            Employee user = assignTotalLeaveRequest.getUser();
            if(!authService.hasRole(user,"ADMIN")){
                throw new ServiceException("user is not admin");
            }
            Employee employee = employeeRepository.findById(assignTotalLeaveRequest.getEmpId())
                    .orElseThrow(() -> new EntityNotFoundException());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            employee.setLeaveBalanceList(
                    assignTotalLeaveRequest
                            .getLeaveBalanceList()
                            .stream()
                            .map((leaveBalanceItem) -> {
                                LeaveBalance leaveBalance = new LeaveBalance();
                                leaveBalance.setEmployee(employee);
                                LeaveType leaveType = leaveTypeRepository.findById(leaveBalanceItem.getLeaveTypeId())
                                        .orElseThrow(() -> new EntityNotFoundException("Not Found LeaveType"));
                                leaveBalance.setLeaveType(leaveType);
                                leaveBalance.setBalance(leaveBalanceItem.getBalance());
                                leaveBalance.setDateAssigned(LocalDateTime.now().format(formatter).toString());
                                leaveBalance.setDescription(leaveBalanceItem.getDescription());
                                leaveBalance.setExpiryDate(leaveBalanceItem.getDateExpired());
                                leaveBalanceRepository.save(leaveBalance);
                                return leaveBalance;
                            }).collect(Collectors.toList()));

            employeeRepository.save(employee);
            assignTotalLeaveResponse.setDate(LocalDateTime.now().format(formatter));
            assignTotalLeaveResponse.setMessage(" successfully assign leaves");
        } catch (Exception e) {
            System.out.println(e);
        }


        return assignTotalLeaveResponse;
    }
}
