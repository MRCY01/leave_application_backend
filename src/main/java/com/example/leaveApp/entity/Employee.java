package com.example.leaveApp.entity;

import com.example.leaveApp.repo.EmployeeRoleRepository;
import com.example.leaveApp.repo.RoleRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
// @ToString
@Entity
@Builder
@Setter
@Getter
@Table(name = "employees")
public class Employee {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "employee", cascade = { CascadeType.PERSIST})
    private List<EmployeeRole> empRole;

    @Column(name = "active", nullable = false)
    private boolean active;
    @Column(name = "emp_name", nullable = false)
    private String empName;

    @OneToMany(mappedBy = "employee", cascade = { CascadeType.PERSIST})
    private List<LeaveBalance> leaveBalanceList;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "approved_date")
    private String approvedDate;

    @Column(name= "password", nullable = false)
    private String password;
    @Column(name= "salt")
    private String salt;
    @Column(name = "bod", nullable = false)
    private String bod;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "marital_status", nullable = false)
    private String maritalStatus;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone_no", nullable = false)
    private String phoneNo;
    @Column(name="grouped", nullable = false)
    private String grouped;

    @Column(name = "employment_status")
    private String employmentStatus;
    @Column(name="firstTimeLogin")
    private boolean isFirstTimeLogin;

    // @ManyToOne
    // @JoinColumn(name="manager_id")
    // private ManagerEmployee manager;

    @OneToMany(mappedBy = "manager")
    private List<ManagerEmployee> managerEmployeeList;

   // @ManyToOne
   // @JoinColumn(name = "manager_id")
   // private Employee manager;

    // @OneToMany(mappedBy = "manager")
    // private List<Employee> employees;

//    @Autowired
//    RoleRepository roleRepository;
//    @Autowired
//    EmployeeRoleRepository employeeRoleRepository;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        if (empRole != null) {
//            for (EmployeeRole employeeRole : empRole) {
//                authorities.add(new SimpleGrantedAuthority(employeeRole.getRole().getRoleName()));
//            }
//        }
//
//        return authorities;
//    }
//
//    @Override
//    public String getUsername() {
//        return getEmpName();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
