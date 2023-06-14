package com.example.leaveApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
// @ToString
@Entity
@Builder
@Setter
@Getter
@Table(name = "employee_role")
public class EmployeeRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="emp_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    @Column(name="assigned_date")
    private String assignedDate;

}
