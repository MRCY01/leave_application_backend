// package com.example.leaveApp;
//
// import com.example.leaveApp.repo.EmployeeRepository;
// import com.example.leaveApp.repo.TestRepository;
// import jakarta.persistence.EntityNotFoundException;
// import lombok.RequiredArgsConstructor;
// import lombok.SneakyThrows;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
// @Configuration
// @RequiredArgsConstructor
// public class ApplicationConfig {
//
//     private final UserResporitory userResporitory;
//    @Bean
//    @SneakyThrows
//    public UserDetailsService userDetailsService(){
//        final EmployeeRepository employeeRepository = null;
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUserId(Long userId) throws Exception {
//                return employeeRepository.findById(userId).orElseThrow(()->new EntityNotFoundException());
//            }
//        }
//    }
// }
