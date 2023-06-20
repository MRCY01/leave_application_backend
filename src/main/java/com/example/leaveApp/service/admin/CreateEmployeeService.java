package com.example.leaveApp.service.admin;

import com.example.leaveApp.entity.Employee;
//import com.example.leaveApp.entity.Manager;
import com.example.leaveApp.entity.ManagerEmployee;
import com.example.leaveApp.exception.ServiceException;
import com.example.leaveApp.repo.EmployeeRepository;
//import com.example.leaveApp.repo.ManagerRepository;
import com.example.leaveApp.repo.ManagerEmployeeRepository;
import com.example.leaveApp.reqres.admin.CreateEmployeeRequest;
import com.example.leaveApp.reqres.admin.CreateEmployeeResponse;
import com.example.leaveApp.service.AuthService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CreateEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ManagerEmployeeRepository managerEmployeeRepository;
    @Autowired
    AuthService authService;

    @SneakyThrows
    public CreateEmployeeResponse addEmployee(CreateEmployeeRequest createEmployeeRequest) throws ServiceException {
        CreateEmployeeResponse createEmployeeResponse = new CreateEmployeeResponse();

        try{
            Employee user = createEmployeeRequest.getUser();
            if(!authService.hasRole(user,"ADMIN")){
                throw new ServiceException("user is not admin");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Employee employee = new Employee();
            employee.setEmpName(createEmployeeRequest.getEmpName());
            employee.setPassword(createEmployeeRequest.getPassword());
            employee.setEmail(createEmployeeRequest.getEmail());
            employee.setBod(createEmployeeRequest.getBod());
            employee.setMaritalStatus(createEmployeeRequest.getMaritalStatus());
            employee.setPhoneNo(createEmployeeRequest.getPhoneNo());
            employee.setAddress(createEmployeeRequest.getAddress());
            employee.setCreatedDate(LocalDateTime.now().format(formatter).toString());
            employee.setGrouped(createEmployeeRequest.getGroup());
            employee.setFirstTimeLogin(true);
            employee.setActive(false);

            if(employeeRepository.existsByEmail(createEmployeeRequest.getEmail())){
                throw new ServiceException("user already exist");
            }

            employeeRepository.save(employee);

            ManagerEmployee managerEmployee = new ManagerEmployee();
            Employee manager = new Employee();
            String managerId = createEmployeeRequest.getManagerId();

            // if(!managerEmployeeRepository.existsById(1L)){
            //     managerEmployee.setId(1L);
            //     return ;
            // }
            // if(!managerEmployeeRepository.existsByManagerId(Long.parseLong(managerId))){
            //     throw new ServiceException("manager not found");
            // }
            manager.setId(Long.parseLong(managerId));
            managerEmployee.setManager(manager);
            managerEmployee.setEmployee(employee);

            managerEmployeeRepository.save(managerEmployee);

            createEmployeeResponse.setMessage("successful insert");
            createEmployeeResponse.setStatus("");
            createEmployeeResponse.setEmpId(employee.getId());


        } catch (ServiceException e){
            System.out.println(e);
            throw e;
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }


        return createEmployeeResponse;
    }
}
