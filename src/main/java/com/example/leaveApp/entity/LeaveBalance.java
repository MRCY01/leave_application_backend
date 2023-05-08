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
@Table(name = "leave_balance")
public class LeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="emp_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="leave_type_id")
    private LeaveType leaveType;

    @OneToMany(mappedBy = "leaveBalance")
    private List<Leave> empLeave;

}
