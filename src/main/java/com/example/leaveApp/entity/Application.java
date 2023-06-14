package com.example.leaveApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Setter
@Getter
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "application")
    private List<Leave> leave;
    @ManyToOne
    @JoinColumn(name="emp_id")
    private Employee empId;
    @ManyToOne
    @JoinColumn(name="manager_id")
    private Employee managerId;
    @Column(name = "manager_approve")
    // @ManyToOne
    // @JoinColumn(name="admin_id")
    // private Employee adminId;
    private Boolean managerApprove;
    // private Boolean adminApprove;
    @Column(name = "submit_date")
    private String submitDate;
    private String status;
}
