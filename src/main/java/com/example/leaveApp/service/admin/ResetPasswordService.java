package com.example.leaveApp.service.admin;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.repo.EmployeeRepository;
import com.example.leaveApp.reqres.admin.ResetPasswordRequest;
import com.example.leaveApp.reqres.admin.ResetPasswordResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService {
    @Autowired
    EmployeeRepository employeeRepository;

    @SneakyThrows
    public ResetPasswordResponse resetPassword(ResetPasswordRequest resetPasswordRequest){
        String email = resetPasswordRequest.getEmail();
        String defaultPassword = "P@SSW0RD123";
        try{
            if(!employeeRepository.existsByEmail(email)){
                throw new Exception("user not exist");
            }
            Employee employee = employeeRepository.findByEmail(email);

            //replace password from admin
            employee.setPassword(defaultPassword);

            employeeRepository.save(employee);
        }catch (Exception e){
            System.out.println(e);
            throw e;
        }

        ResetPasswordResponse response = new ResetPasswordResponse();

        response.setMessage("successfull reset to password to default password = P@SSW0RD123 ");
        return response;
    }

}
