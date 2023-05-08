package com.example.leaveApp.service;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.EmployeeRole;
import com.example.leaveApp.entity.Role;
import com.example.leaveApp.repo.EmployeeRepository;
import com.example.leaveApp.repo.EmployeeRoleRepository;
import com.example.leaveApp.repo.RoleRepository;
import com.example.leaveApp.reqres.empRole.EmployeeRoleRequest;
import com.example.leaveApp.reqres.empRole.EmployeeRoleResponse;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;

@Service
@Log
public class EmployeeRoleService {

    @Autowired
    private EmployeeRoleRepository employeeRoleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;

    @SneakyThrows
    public EmployeeRoleResponse insertData(EmployeeRoleRequest employeeRoleRequest) {
        EmployeeRoleResponse employeeRoleResponse = new EmployeeRoleResponse();

        Employee employee = new Employee();
        Role role = new Role();
        Long empId = employeeRoleRequest.getEmpId();
        Long roleId = employeeRoleRequest.getRoleId();

        EmployeeRole employeeRole = new EmployeeRole();
        employee.setId(empId);
        role.setId(roleId);
        employeeRole.setEmployee(employee);
        employeeRole.setRole(role);

        employeeRoleRepository.save(employeeRole);

        try{
            employeeRoleResponse.setId(employeeRole.getId());
            employeeRoleResponse.setEmpId(empId);
            employeeRoleResponse.setRoleId(roleId);
            employeeRoleResponse.setEmpName(employeeRepository.findById(empId).get().getEmpName());
            employeeRoleResponse.setRole(roleRepository.findById(roleId).get().getRoleName());

        }catch (Exception e){
            throw new SQLDataException(e);
        }
        return employeeRoleResponse;
    }
}
