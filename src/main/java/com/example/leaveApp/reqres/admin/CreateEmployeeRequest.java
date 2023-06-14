package com.example.leaveApp.reqres.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {
    @NotBlank
    private String empName;
    @NotBlank
    private String password;
    @NotNull
    private boolean isHired;
    private String address;
    private String bod;
    private String maritalStatus;
    private String phoneNo;
    @NotBlank
    // @Pattern(".*@.*")
    private String email;
    private String group;
    private String managerId;

}
