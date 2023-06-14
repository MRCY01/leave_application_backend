package com.example.leaveApp.repo;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.EmployeeRole;
import com.example.leaveApp.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Long> {

//    EmployeeRole findByEmployee(Employee employee);
}
