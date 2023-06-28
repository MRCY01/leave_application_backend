package com.example.leaveApp.service.admin.view;

import com.example.leaveApp.entity.Employee;
import com.example.leaveApp.entity.EmployeeRole;
import com.example.leaveApp.entity.LeaveBalance;
import com.example.leaveApp.entity.LeaveType;
import com.example.leaveApp.exception.ServiceException;
import com.example.leaveApp.jwt.JWTUtil;
import com.example.leaveApp.repo.EmployeeRepository;
import com.example.leaveApp.repo.EmployeeRoleRepository;
import com.example.leaveApp.repo.LeaveBalanceRepository;
import com.example.leaveApp.repo.LeaveTypeRepository;
import com.example.leaveApp.reqres.admin.*;
import com.example.leaveApp.service.AuthService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminViewService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;
    @Autowired
    LeaveTypeRepository leaveTypeRepository;
    @Autowired
    EmployeeRoleRepository employeeRoleRepository;
    @Autowired
    AuthService authService;
    @Autowired
    JWTUtil jwtUtil;
    @SneakyThrows
    public ShowAllEmployeeResponse showAllEmployee(ShowAllEmployeeRequest request){
        ShowAllEmployeeResponse response = new ShowAllEmployeeResponse();

        try{
            Employee user = request.getUser();
            if(!authService.hasRole(user,"ADMIN")){
                throw new ServiceException("user is not admin");
            }
            // List<Map<String, Object>> employeeList = employeeRepository.getEmployeeData();
            // showAllEmployeeResponse.setEmployeeList(employeeList);
            if(employeeRepository.findAll().isEmpty()){
                throw  new ServiceException("no employee record found");
            }

            List<Employee> employeeList = employeeRepository.findAll();
            List<AdminShowAllDTO> detailList = new ArrayList<>();
            for (Employee emp: employeeList){
                AdminShowAllDTO details = new AdminShowAllDTO();
                details.setEmployeeId(emp.getId().toString());
                details.setEmployeeName(emp.getEmpName());
                details.setEmail(emp.getEmail());
                details.setPassword(emp.getPassword());
                details.setCreatedDate(emp.getCreatedDate());
                details.setApprovedDate(emp.getApprovedDate());
                details.setActive(emp.isActive());
                details.setAddress(emp.getAddress());
                details.setBod(emp.getBod());
                details.setMaritalStatus(emp.getMaritalStatus());
                details.setPhone(emp.getPhoneNo());

                List<EmployeeRole> employeeRoleList = employeeRoleRepository.findAllByEmployeeId(emp.getId());
                for(EmployeeRole er: employeeRoleList){
                    details.setRole(er.getRole().getRoleName());
                }

                detailList.add(details);
            }

            response.setEmployeeList(detailList);
            response.setMessage("successfully retrieve");
        }catch (ServiceException e){
            throw e;
        }
        return response;
    }

    @SneakyThrows
    public ShowAllLeaveBalanceResponse showAllLeaveBalance(ShowAllLeaveBalanceRequest request){
        ShowAllLeaveBalanceResponse response =  new ShowAllLeaveBalanceResponse();

        try{
            Employee user = request.getUser();
            if(!authService.hasRole(user,"ADMIN")){
                throw new ServiceException("user is not admin");
            }
            if(leaveBalanceRepository.findAll().isEmpty()){
                throw  new ServiceException("no leave balance record found");
            }

            List<Employee>  employeeList = employeeRepository.findAll();
            List<AdminShowLeaveBalanceDTO> adminShowLeaveBalanceDTOList = new ArrayList<>();

            for(Employee e:employeeList){
                AdminShowLeaveBalanceDTO empDetails = new AdminShowLeaveBalanceDTO();
                empDetails.setEmployeeName(e.getEmpName());
                empDetails.setEmployeeId(e.getId().toString());

                LeaveBalance leaveBalance = leaveBalanceRepository.findById(e.getId())
                        .orElseThrow();
                LeaveBalanceDetails lbDetails = new LeaveBalanceDetails();
                lbDetails.setBalance(leaveBalance.getBalance());
                lbDetails.setDescription(leaveBalance.getDescription());
                lbDetails.setDateAssigned(leaveBalance.getDateAssigned());
                lbDetails.setExpiryDate(leaveBalance.getExpiryDate());

                LeaveType leaveType = leaveTypeRepository.findById(leaveBalance.getId())
                        .orElseThrow();
                lbDetails.setLeaveType(leaveType.getLeaveTypeName());

                empDetails.setLeaveBalanceDetails(lbDetails);
                adminShowLeaveBalanceDTOList.add(empDetails);
            }
            response.setAdminShowLeaveBalanceDTOList(adminShowLeaveBalanceDTOList);
            response.setMessage("successfully retrieve leave balance");

        }catch(ServiceException e){
            throw e;
        }
        return response;
    }

}
