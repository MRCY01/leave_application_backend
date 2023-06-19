// package com.example.leaveApp.controller;
//
// import com.example.leaveApp.reqres.AuthenticationRequest;
// import com.example.leaveApp.reqres.AuthenticationResponse;
// import com.example.leaveApp.reqres.RegisterRequest;
// import com.example.leaveApp.service.AuthenticationService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// @RestController
// @RequestMapping("/api/auth")
// @RequiredArgsConstructor
// public class AuthenticationController {
//
//     private final AuthenticationService service;
//     @PostMapping("/register")
//     public ResponseEntity<AuthenticationResponse>register(@RequestBody RegisterRequest request){
//         return ResponseEntity.ok(service.register(request));
//     }
//     @PostMapping("/authenticate")
//     public ResponseEntity<AuthenticationResponse>register(@RequestBody AuthenticationRequest request){
//         return ResponseEntity.ok(service.authenticate(request));
//     }
//
// }
