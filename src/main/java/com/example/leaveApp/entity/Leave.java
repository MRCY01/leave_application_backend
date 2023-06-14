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
@Table(name = "emp_leave")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="leave_balance_id")
    private LeaveBalance leaveBalance;

    @ManyToOne
    @JoinColumn(name="application_id")
    private Application application;


    private Date applyDate;
    private String reason;
    // private Boolean isFullDay;
//    private Boolean amPm;


}
