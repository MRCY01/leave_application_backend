package com.example.leaveApp.service;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.EmployeeRole;
import com.example.leaveApp.entity.Role;
import com.example.leaveApp.jwt.JWTUtil;
import com.example.leaveApp.repo.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JWTUtil jwtUtil;

    @SneakyThrows
    public String getToken(String email, String password) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee.getEmail().isEmpty()) {
            throw new Exception("Invalid email");
        }
        if(!employee.getPassword().equalsIgnoreCase(password)){
            throw new Exception("incorrect password");
        }

        // Generate a token to store user
        String token = jwtUtil.generateToken(employee.getId().toString(), employee.getEmail());
        return token;
    }

    @SneakyThrows
    public List<String> getUserRole(String token) {
        // Extract the user ID from the token
        String userId = jwtUtil.extractUserId(token);

        // String roleName = "";
        List<String> roleList = new ArrayList<>();
        Employee employee = employeeRepository.findById(Long.parseLong(userId))
                .orElseThrow(()->new EntityNotFoundException());
        if (employee == null) {
            throw new Exception("User not found");
        }

        List<EmployeeRole> empRoles = employee.getEmpRole();
        for (EmployeeRole empRole : empRoles) {
            Role role = empRole.getRole();
            roleList.add(role.getRoleName());
        }

        return roleList;
    }
    @SneakyThrows
    public Employee getUser(String token) {
        // Extract the user ID from the token
        String userId = jwtUtil.extractUserId(token);

        Employee employee = employeeRepository.findById(Long.parseLong(userId))
                .orElseThrow(()->new EntityNotFoundException());


        return employee;
    }

    // public boolean isCorrectRole(String role,String token){
    //     Employee user = getUser(token);
    //     List<String> roleList = getUserRole(token);
    //     jwtUtil.validateToken(token, jwtUtil.extractUserId(token));
    //
    //     return !roleList.contains(role);
    // }
    public boolean hasRole(Employee employee, String role){
        if(employee==null){
            return false;
        }
        List<EmployeeRole> employeeRoleList = employee.getEmpRole();

        List<String> roleList = employeeRoleList.stream()
                .map(EmployeeRole::getRole)
                .map(Role::getRoleName)
                .collect(Collectors.toList());


        // jwtUtil.validateToken(token, jwtUtil.extractUserId(token));

        // if(roleList.contains(role)){
        //     return true;
        // }else{
        //     return false;
        // }
        return roleList.contains(role);
    }
}
