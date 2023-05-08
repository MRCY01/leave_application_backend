package com.example.leaveApp.service;

import com.example.leaveApp.entity.*;
import com.example.leaveApp.repo.EmployeeRepository;
import com.example.leaveApp.repo.EmployeeRoleRepository;
import com.example.leaveApp.repo.RoleRepository;
import com.example.leaveApp.reqres.createEmp.CreateEmployeeRequest;
import com.example.leaveApp.reqres.createEmp.CreateEmployeeResponse;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CreateEmployeeService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeRoleRepository employeeRoleRepository;
    public void createNewEmployee(CreateEmployeeRequest createEmployeeRequest){
        CreateEmployeeResponse createEmployeeResponse = new CreateEmployeeResponse();

        Employee employee = new Employee();
        Role role = new Role();
        EmployeeRole employeeRole = new EmployeeRole();

        String empName = createEmployeeRequest.getEmpName();
        String status = createEmployeeRequest.getStatus();
        Date dateCreated = createEmployeeRequest.getDateCreated();
        List<LeaveBalance> leaveBalanceList= createEmployeeRequest.getLeaveBalanceList();
        String roleName = createEmployeeRequest.getRole();

        for(LeaveBalance leaveBalance: leaveBalanceList){
           LeaveType leaveType = new LeaveType();

           leaveType.setLeaveType();
        }
        //create emp
        try{

            employee.setEmpName(empName);
            employee.setEmpStatus(status);
            employee = employeeRepository.save(employee);
            employeeRole.setEmployee(employee);

            checkRoleId(roleName,role);
            employeeRole.setRole(role);



        }catch (Exception e){
            System.out.println(e);
        }

        employeeRoleRepository.save(employeeRole);

//        createEmployeeResponse.getMessage("roleRepository.findByRoleName(roleName) "+);
//        return createEmployeeResponse;
    }

    public void checkRoleId(String roleName, Role role){
        if(roleRepository.count()!=0) {
            switch (roleName) {
                case "employee":
                    role.setId(1L);
                    break;
                case "manager":
                    role.setId(2L);
                    break;
                case "admin":
                    role.setId(3L);
                    break;
                default:
                    break;
            }
            role = roleRepository.save(role);
        }else{
            throw new IllegalArgumentException("role doesnt exist");
        }
    }

}
