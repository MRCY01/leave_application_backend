package com.example.leaveApp.repo;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.LeaveBalance;
import com.example.leaveApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {

    LeaveBalance findByEmployee(Employee employee);

    @Query("SELECT new map(l.id as leaveBalanceId, l.balance as balance, l.dateAssigned as dateAssigned, l.description as description, l.expiryDate as expiryDate, l.employee.id as empId, l.leaveType.id as leaveTypeId) FROM LeaveBalance l")
    List<Map<String, Object>> findLeaveBalanceData();

}
