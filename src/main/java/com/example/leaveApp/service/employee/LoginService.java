package com.example.leaveApp.service.employee;

import com.example.leaveApp.entity.Employee;
//import com.example.leaveApp.entity.Manager;
//import com.example.leaveApp.jwt.JWTUtil;
import com.example.leaveApp.jwt.JWTUtil;
import com.example.leaveApp.repo.EmployeeRepository;
import com.example.leaveApp.repo.EmployeeRoleRepository;
//import com.example.leaveApp.repo.ManagerRepository;
import com.example.leaveApp.repo.RoleRepository;
import com.example.leaveApp.reqres.employee.login.LoginResponse;
import com.example.leaveApp.reqres.employee.login.LoginRequest;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class LoginService {
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EmployeeRoleRepository employeeRoleRepository;
    @SneakyThrows
    public LoginResponse login(LoginRequest loginRequest) {
        String loginEmail = loginRequest.getLoginEmail();
        String password = loginRequest.getPassword();

        if(loginEmail.equals("") || loginEmail == null || password.equals("") || password == null) {
            System.out.println("credential not complete");
        }

        Employee employee = employeeRepository.findByEmail(loginEmail);
        if (employee == null) {
            throw new Exception("email not found");
        }

        if(!employee.getPassword().equalsIgnoreCase(password)){
            throw new Exception("incorrect password");
        }

        String token = jwtUtil.generateToken(employee.getId().toString(), employee.getEmail());


        LoginResponse loginResponse = new LoginResponse();
        String message = "successful, check DB";

        loginResponse.setMessage(message);
        loginResponse.setToken(token);
        return loginResponse;
    }


    // @SneakyThrows
    // public void checkCredential(String loginName, String loginPassword) {
    //     Employee employee = employeeRepository.findByEmpName(loginName);
    //     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //     String dbPassword = employee.getPassword();
    //     String dbSalt = employee.getSalt();
    //     String hashedPassword = loginPassword+dbSalt;
    //
    //     if (passwordEncoder.matches(hashedPassword, dbPassword)) {
    //         // Password is correct, user is authenticated
    //         System.out.println("true");
    //     } else {
    //         // Password is incorrect
    //         System.out.println("password wrong");
    //         throw new Exception();
    //     }
    // }
//    public void showToken(String empName){
//        JWTUtil j = new JWTUtil();
//        Employee employee = new Employee();
//        String id = employeeRepository.findByEmpName(empName).getId().toString();
//        employee.setId(Long.parseLong(id));
//        String name = empName;
//        String roleName = employeeRoleRepository.findByEmployee(employee).getRole().getRoleName();
//        String token = j.generateToken(id,  name );
//        System.out.println("role "+roleName);
//        System.out.println("token "+token);
//    }
}
