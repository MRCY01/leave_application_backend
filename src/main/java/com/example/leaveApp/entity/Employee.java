package com.example.leaveApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @OneToMany(mappedBy = "employee")
    private List<EmployeeRole> empRole;

    @Column(name = "emp_status")
    private String empStatus;
    @Column(name = "emp_name")
    private String empName;

}
