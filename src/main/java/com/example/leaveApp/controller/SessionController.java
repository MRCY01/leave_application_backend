// package com.example.leaveApp.controller;
//
// import com.example.leaveApp.service.SessionService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
//
// @RestController
// @RequestMapping("/session")
// public class SessionController {
//
//     @Autowired
//     private SessionService sessionService;
//
//     @PostMapping("/validate")
//     public ResponseEntity<Void> validateSession(@RequestHeader("Authorization") String token) {
//         if (token == null || !token.startsWith("Bearer ")) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//         }
//
//         String jwtToken = token.substring(7); // Remove "Bearer " prefix
//         String userId = sessionService.getUserIdFromToken(jwtToken);
//         boolean isValidToken = sessionService.isValidToken(jwtToken,userId);
//
//         if (userId != null && sessionService.isValidToken(jwtToken, userId)) {
//             return ResponseEntity.ok().build();
//         } else {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//         }
//     }
//
//     @GetMapping("/user")
//     public ResponseEntity<String> getUserId(@RequestHeader("Authorization") String token) {
//         if (token == null || !token.startsWith("Bearer ")) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//         }
//
//         String jwtToken = token.substring(7); // Remove "Bearer " prefix
//         String userId = sessionService.getUserIdFromToken(jwtToken);
//
//         if (userId != null) {
//             return ResponseEntity.ok(userId);
//         } else {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//         }
//     }
//
//     // You can add more endpoints for session-related operations as needed
// }
