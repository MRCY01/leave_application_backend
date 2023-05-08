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
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "role")
    private List<EmployeeRole> empRole;

    @Column(name = "role_name")
    private String roleName;
    @Column(name = "permission")
    private String permission;

}
