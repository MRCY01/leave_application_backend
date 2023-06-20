package com.example.leaveApp;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.EmployeeRole;
import com.example.leaveApp.entity.LeaveType;
import com.example.leaveApp.entity.Role;
import com.example.leaveApp.exception.ServiceException;
import com.example.leaveApp.repo.EmployeeRepository;
import com.example.leaveApp.repo.EmployeeRoleRepository;
import com.example.leaveApp.repo.LeaveTypeRepository;
import com.example.leaveApp.repo.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataLoader implements ApplicationRunner {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;
    @Autowired
    private EmployeeRoleRepository employeeRoleRepository;
    public void run(ApplicationArguments args) {
        initialRole();
        initialLeaveType();
        initialAdmin();
    }

    @SneakyThrows
    private Employee initialAdmin(){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            {
                if(!employeeRepository.findAll().isEmpty()){
                    return null;
                }

                Employee adminDetails = new Employee();
                adminDetails.setActive(true);
                adminDetails.setEmail("admin@gmail.com");
                adminDetails.setBod("1990-5-6");
                adminDetails.setPassword("Pass");
                adminDetails.setEmpName("admin");
                adminDetails.setAddress("134, jln 33");
                adminDetails.setGrouped("IT");
                adminDetails.setMaritalStatus("married");
                adminDetails.setPhoneNo("0105613876");
                adminDetails.setEmploymentStatus("admin account");
                adminDetails.setCreatedDate(LocalDateTime.now().format(formatter));

                EmployeeRole tmp = new EmployeeRole();
                tmp.setEmployee(adminDetails);
                tmp.setRole(roleRepository.findByRoleName("ADMIN")
                        .orElseThrow( () -> new EntityNotFoundException("ADMIN not found")));
                tmp.setAssignedDate(LocalDateTime.now().format(formatter));
                employeeRepository.save(adminDetails);
                employeeRoleRepository.save(tmp);
                System.out.println("admin created");
                return adminDetails;
            }
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }
    private void initialRole(){
        try{
            if(roleRepository.findAll().isEmpty()){
                String[] arr = new String[]{"ADMIN","MANAGER","EMPLOYEE"};
                for (String str: arr){
                    Role role = new Role();
                    role.setRoleName(str);
                    roleRepository.save(role);
                }
            }
        }catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }
    private void initialLeaveType(){
        try{
            if(leaveTypeRepository.findAll().isEmpty()){
                String[] activeLeave = new String[]{"Annual Leave","Medical Leave","Birthday Leave","Replacement Leave"};
                for (String leave: activeLeave){
                    LeaveType leaveType = new LeaveType();
                    leaveType.setLeaveTypeName(leave);
                    leaveType.setActive(true);
                    leaveTypeRepository.save(leaveType);
                }
            }
        }catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }
}
