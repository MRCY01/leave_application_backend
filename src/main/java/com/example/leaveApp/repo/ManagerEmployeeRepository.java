package com.example.leaveApp.repo;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.ManagerEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerEmployeeRepository extends JpaRepository<ManagerEmployee,Long> {
    boolean existsByManagerId (Long id);
}
