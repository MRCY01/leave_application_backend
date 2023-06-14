package com.example.leaveApp.reqres.employee.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String loginEmail;
    private String password;
}
