package com.example.leaveApp.service.admin.update;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.LeaveType;
import com.example.leaveApp.exception.ServiceException;
import com.example.leaveApp.repo.LeaveTypeRepository;
import com.example.leaveApp.reqres.admin.update.NewLeaveTypeRequest;
import com.example.leaveApp.reqres.admin.update.NewLeaveTypeResponse;
import com.example.leaveApp.service.AuthService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateLeaveTypeService {
    @Autowired
    LeaveTypeRepository leaveTypeRepository;
    @Autowired
    AuthService authService;

    @SneakyThrows
    public NewLeaveTypeResponse addNewLeaveType(NewLeaveTypeRequest request){
            NewLeaveTypeResponse response = new NewLeaveTypeResponse();
        try {
            Employee user = request.getUser();
            if(!authService.hasRole(user,"ADMIN")){
                throw new ServiceException("user is not admin");
            }
            String newLeaveType = request.getNewLeaveType();
            boolean isActive = request.isActive();
            LeaveType leaveType = new LeaveType();
            leaveType.setLeaveTypeName(newLeaveType);
            leaveType.setActive(isActive);

            leaveTypeRepository.save(leaveType);
            response.setMessage("success update new Leave Type");

        }catch (Exception e){
            throw e;
        }

        return response;
    }
}
