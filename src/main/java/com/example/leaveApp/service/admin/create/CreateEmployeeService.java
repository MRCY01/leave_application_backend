package com.example.leaveApp.service.admin.create;

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
import java.util.regex.Pattern;

@Service
public class CreateEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ManagerEmployeeRepository managerEmployeeRepository;
    @Autowired
    AuthService authService;

    private static final String  EMAIL_PATTERN = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    @SneakyThrows
    public CreateEmployeeResponse createEmployee(CreateEmployeeRequest request) throws ServiceException {
        CreateEmployeeResponse createEmployeeResponse = new CreateEmployeeResponse();

        try{
            Employee user = request.getUser();
            if(!authService.hasRole(user,"ADMIN")){
                throw new ServiceException("user is not admin");
            }
            String email = request.getEmail();
            if(!pattern.matcher(email).matches()){
                throw new ServiceException("wrong email format");
            }
            if(employeeRepository.existsByEmail(email)){
                throw new ServiceException("user already exist");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Employee employee = new Employee();
            employee.setEmpName(request.getEmpName());
            employee.setPassword(request.getPassword());
            employee.setEmail(request.getEmail());
            employee.setBod(request.getBod());
            employee.setMaritalStatus(request.getMaritalStatus());
            employee.setPhoneNo(request.getPhoneNo());
            employee.setAddress(request.getAddress());
            employee.setCreatedDate(LocalDateTime.now().format(formatter).toString());
            employee.setGrouped(request.getGroup());
            employee.setFirstTimeLogin(true);
            employee.setActive(false);

            employeeRepository.save(employee);

            ManagerEmployee managerEmployee = new ManagerEmployee();
            Employee manager = new Employee();
            String managerId = request.getManagerId();

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
