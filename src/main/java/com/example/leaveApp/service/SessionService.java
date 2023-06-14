// package com.example.leaveApp.service;
//
// import com.example.leaveApp.entity.Employee;
// import com.example.leaveApp.jwt.JWTUtil;
// import com.example.leaveApp.repo.EmployeeRepository;
// import jakarta.persistence.EntityNotFoundException;
// import lombok.SneakyThrows;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// import java.time.LocalDateTime;
// import java.time.ZoneOffset;
// import java.time.format.DateTimeFormatter;
// import java.util.UUID;
//
// @Service
// public class SessionService {
//
//    @Autowired
//    EmployeeRepository employeeRepository;
//    // @SneakyThrows
//    // public void createSession(Employee employee, User user){
//    //     String sessionId  = UUID.randomUUID().toString();
//    //     user.setSID(sessionId);
//    //     user.setEmployeeId(employeeRepository.findById(employee.getId())
//    //             .orElseThrow(()-> new EntityNotFoundException("employee not exist")));
//    // }
//    //
//    // public void isSessionExpired(User user){
//    //     boolean isPresent = sessionRepository.findById(Long.parseLong(user.getSID())).isPresent();
//    //     if(!isPresent){
//    //         System.out.println("Expired");
//    //     }
//    // }
//    //  public void removeSession(User user){
//    //     sessionRepository.deleteById(Long.parseLong(user.getSID()));
//    //  }
//    @Autowired
//    private JWTUtil jwtUtil;
//
//     public boolean isValidToken(String token, String userId) {
//         return jwtUtil.validateToken(token, userId);
//     }
//
//     public String getUserIdFromToken(String token) {
//         return jwtUtil.extractUserId(token);
//     }
//    public String getCurrentTime(){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String timeDate = LocalDateTime
//                .ofEpochSecond(System.currentTimeMillis()/1000, 0, ZoneOffset.ofHours(8))
//                .format(formatter);
//        return timeDate;
//    }
//
//
// }
