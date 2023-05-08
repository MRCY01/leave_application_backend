package com.example.leaveApp.repo;

import com.example.leaveApp.entity.LeaveBalance;
import com.example.leaveApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {

}
