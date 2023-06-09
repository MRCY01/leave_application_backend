package com.example.leaveApp.service.admin.update;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.EmployeeRole;
//import com.example.leaveApp.entity.Manager;
import com.example.leaveApp.entity.Role;
import com.example.leaveApp.exception.ServiceException;
import com.example.leaveApp.repo.EmployeeRepository;
import com.example.leaveApp.repo.EmployeeRoleRepository;
//import com.example.leaveApp.repo.ManagerRepository;
import com.example.leaveApp.repo.RoleRepository;
import com.example.leaveApp.reqres.admin.ApproveEmployeeRequest;
import com.example.leaveApp.reqres.admin.ApproveEmployeeResponse;
import com.example.leaveApp.reqres.admin.AssignRoleRequest;
import com.example.leaveApp.reqres.admin.AssignRoleResponse;
import com.example.leaveApp.service.AuthService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UpdateStatusService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeRoleRepository employeeRoleRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthService authService;
    @SneakyThrows
    public AssignRoleResponse assignRole(AssignRoleRequest assignRoleRequest) {
        Employee user = assignRoleRequest.getUser();
        if(!authService.hasRole(user,"ADMIN")){
            throw new ServiceException("user is not admin");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        AssignRoleResponse assignRoleResponse = new AssignRoleResponse();
        EmployeeRole employeeRole = new EmployeeRole();
        try{
            Employee employee = employeeRepository.findById(assignRoleRequest.getEmpId()).
                    orElseThrow(() -> new ChangeSetPersister.NotFoundException());
            employeeRole.setEmployee(employee);

            Role role = roleRepository.findById(assignRoleRequest.getRoleId()).
                    orElseThrow(() -> new ChangeSetPersister.NotFoundException());

            employeeRole.setRole(role);
            employeeRole.setAssignedDate(LocalDateTime.now().format(formatter).toString());

//            employee.setApprovedDate(LocalDateTime.now().format(formatter).toString());
//            employee.setHired(true);

            employeeRoleRepository.save(employeeRole);
            employeeRepository.save(employee);

            assignRoleResponse.setMessage("successfully approved");
        }catch(Exception e){
            System.out.println(e);
        }
        return assignRoleResponse;
    }

    @SneakyThrows
    public ApproveEmployeeResponse updateHiredStatus(ApproveEmployeeRequest approveEmployeeRequest){
        Employee user = approveEmployeeRequest.getUser();
        if(!authService.hasRole(user,"ADMIN")){
            throw new ServiceException("user is not admin");
        }
        ApproveEmployeeResponse approveEmployeeResponse = new ApproveEmployeeResponse();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try{
            Employee employee = employeeRepository.findById(approveEmployeeRequest.getEmpId()).
                    orElseThrow(() -> new ChangeSetPersister.NotFoundException());

            employee.setApprovedDate(LocalDateTime.now().format(formatter).toString());
            employee.setActive(approveEmployeeRequest.isActive());
            employee.setEmploymentStatus("Hired");

            employeeRepository.save(employee);

            approveEmployeeResponse.setMessage("Employment status updated successfully");
            approveEmployeeResponse.setEmpId(employee.getId().toString());
        }catch (Exception e){
            System.out.println(e);
        }


        return approveEmployeeResponse;
    }
}
