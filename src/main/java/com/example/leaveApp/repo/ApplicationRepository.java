package com.example.leaveApp.repo;

import com.example.leaveApp.entity.Application;
import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByEmpId(Employee employee);

    List<Application> findByManagerId(Employee employee);
}
