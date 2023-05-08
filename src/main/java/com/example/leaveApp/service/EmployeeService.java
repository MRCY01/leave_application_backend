package com.example.leaveApp.service;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.repo.EmployeeRepository;
import com.example.leaveApp.reqres.employee.EmployeeRequest;
import com.example.leaveApp.reqres.employee.EmployeeResponse;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
//import org.springframework.web.bind.annotation.ControllerAdvice;

//import java.util.List;

@Service
@Log
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    public Employee addEmployee(Employee employee) {
//
//        log.info(employee.toString());
////        employeeRepository.findAllById("id");
//        return employeeRepository.save(employee);
//    }

    @SneakyThrows
    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
        String empName = employeeRequest.getEmpName();
        String empStatus = employeeRequest.getEmpStatus();

        Employee employee = new Employee();
        employee.setEmpName(empName);
        employee.setEmpStatus(empStatus);
        EmployeeResponse employeeResponse = new EmployeeResponse();

        employeeRepository.save(employee);

//        System.out.println(list);
        if(employeeRepository.findById(employee.getId()).isEmpty()){
            throw new SQLDataException("id not found");
        }
        try{
            employeeResponse.setId(employee.getId());
            employeeResponse.setEmpName(empName);
            employeeResponse.setEmpStatus(empStatus);

        }catch(Exception e){
            throw new SQLDataException(e);
        }

        return employeeResponse;
    }
}
