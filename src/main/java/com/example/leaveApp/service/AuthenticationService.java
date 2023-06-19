// package com.example.leaveApp.service;
//
// import com.example.leaveApp.entity.Employee;
// import com.example.leaveApp.entity.User;
// import com.example.leaveApp.jwt.JWTService;
// import com.example.leaveApp.repo.EmployeeRepository;
// import com.example.leaveApp.repo.UserRepository;
// import com.example.leaveApp.reqres.AuthenticationRequest;
// import com.example.leaveApp.reqres.AuthenticationResponse;
// import com.example.leaveApp.reqres.RegisterRequest;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;
//
// @Service
// @RequiredArgsConstructor
// public class AuthenticationService {
//
//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final JWTService jwtService;
//     private  final AuthenticationManager authenticationManager;
//     @Autowired
//     EmployeeRepository employeeRepository;
//     public AuthenticationResponse register(RegisterRequest request) {
//
//         var user = User.builder()
//                 .email(request.getEmail())
//                 .password(passwordEncoder.encode(request.getPassword()))
//                 .build();
//         userRepository.save(user);
//         Employee employee = new Employee();
//         employee.setEmail(request.getEmail());
//         employee.setPassword(passwordEncoder.encode(request.getPassword()));
//         employeeRepository.save(employee);
//
//         var token = jwtService.generateToken(user);
//         return AuthenticationResponse
//                 .builder()
//                 .token(token)
//                 .build();
//     }
//
//     public AuthenticationResponse authenticate(AuthenticationRequest request) {
//         authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(
//                         request.getEmail(),
//                         request.getPassword()
//                 )
//         );
//         var token = userRepository.findByEmail(request.getEmail())
//                 .orElseThrow();
//
//         return AuthenticationResponse
//                 .builder()
//                 .build();
//     }
// }
