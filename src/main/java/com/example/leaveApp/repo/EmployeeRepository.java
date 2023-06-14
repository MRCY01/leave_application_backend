package com.example.leaveApp.repo;

import com.example.leaveApp.entity.Employee;
//import com.example.leaveApp.entity.Manager;
import com.example.leaveApp.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Employee findByPassword(String email, String password);
    Employee findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT new map(e.id as empId,e.approvedDate as approvedDate, e.empName as empName, e.email as email, e.address as address, e.bod as bod, e.createdDate as createdDate, e.active as active, e.maritalStatus as maritalStatus, e.password as password, e.phoneNo as phoneNo) FROM Employee e")
    List<Map<String, Object>> getEmployeeData();

//
//    boolean existsByEmpName(String loginName);

//    Employee findByManager(Manager manager);
}
