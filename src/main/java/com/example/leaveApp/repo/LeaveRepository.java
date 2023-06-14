package com.example.leaveApp.repo;

import com.example.leaveApp.entity.Leave;
import com.example.leaveApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByApplicationId (String id);
}