package com.example.leaveApp.repo;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Long> {

}
