package com.example.leaveApp.service.manager;

import com.example.leaveApp.entity.*;
import com.example.leaveApp.exception.ServiceException;
import com.example.leaveApp.repo.ApplicationRepository;
import com.example.leaveApp.repo.LeaveBalanceRepository;
import com.example.leaveApp.repo.LeaveRepository;
import com.example.leaveApp.reqres.manager.ApproveLeaveRequest;
import com.example.leaveApp.reqres.manager.ApproveLeaveResponse;
import com.example.leaveApp.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApproveLeaveService {
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    LeaveRepository leaveRepository;
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;
    @Autowired
    AuthService authService;
    @SneakyThrows
    public ApproveLeaveResponse approveLeave(ApproveLeaveRequest request){
        ApproveLeaveResponse response = new ApproveLeaveResponse();

        try{
            Employee user = request.getUser();
            if (!(authService.hasRole(user, "ADMIN") && authService.hasRole(user, "MANAGER"))) {
                throw new ServiceException("User is not permitted to access" );
            }
            String      appId = request.getAppId();
            Application application = applicationRepository.findById(Long.parseLong(appId))
                    .orElseThrow(()-> new EntityNotFoundException());
            application.setManagerApprove(request.isApprove());
            application.setStatus("manager approved");
            applicationRepository.save(application);

            updateLeaveBalance(request.getAppId());

        }catch (Exception e){
            System.out.println(e);
            throw e;
        }
        response.setMessage("update success");
        return response;
    }

    @SneakyThrows
    public void updateLeaveBalance(String id){
        List<Leave> leaveList = leaveRepository.findByApplicationId(id);
        //iterate through leaveList(with given appId) and make every element to be object leave
        for(Leave l : leaveList ){
            LeaveBalance leaveBalance = leaveBalanceRepository.findById(l.getLeaveBalance().getId())
                    .orElseThrow(()-> new EntityNotFoundException());

            if(!leaveBalance.getBalance().equalsIgnoreCase("0")){
                int cal = Integer.parseInt(leaveBalance.getBalance()) - 1;
                leaveBalance.setBalance(String.valueOf(cal));
            }else{
                String type = leaveBalance.getLeaveType().getLeaveTypeName();
                throw new Exception(type+" no leave left");
            }
            leaveBalanceRepository.save(leaveBalance);
        }
    }
}
