package com.example.leaveApp.service.manager;

import com.example.leaveApp.entity.Application;
import com.example.leaveApp.entity.Leave;
import com.example.leaveApp.entity.LeaveBalance;
import com.example.leaveApp.entity.LeaveType;
import com.example.leaveApp.repo.ApplicationRepository;
import com.example.leaveApp.repo.LeaveBalanceRepository;
import com.example.leaveApp.repo.LeaveRepository;
import com.example.leaveApp.reqres.manager.ApproveLeaveRequest;
import com.example.leaveApp.reqres.manager.ApproveLeaveResponse;
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
    @SneakyThrows
    public ApproveLeaveResponse approveLeave(ApproveLeaveRequest approveLeaveRequest){
        ApproveLeaveResponse response = new ApproveLeaveResponse();

        try{
            String      appId = approveLeaveRequest.getAppId();
            Application application = applicationRepository.findById(Long.parseLong(appId))
                    .orElseThrow(()-> new EntityNotFoundException());
            application.setManagerApprove(approveLeaveRequest.isApprove());
            application.setStatus("manager approved");
            applicationRepository.save(application);

            updateLeaveBalance(approveLeaveRequest.getAppId());

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
