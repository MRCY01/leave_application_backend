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
@Table(name = "manager_employee")
public class ManagerEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="emp_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name="manager_id")
    private Employee manager;
}
