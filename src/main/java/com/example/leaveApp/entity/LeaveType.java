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
@Table(name = "leave_type")
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "leaveType")
    private List<LeaveBalance> leaveBalance;

    @Column(name = "Name")
    private String leaveTypeName;
    @Column(name = "is_active")
    private boolean isActive;

}
