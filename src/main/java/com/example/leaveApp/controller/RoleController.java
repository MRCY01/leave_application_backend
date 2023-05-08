package com.example.leaveApp.controller;

import com.example.leaveApp.reqres.role.RoleRequest;
import com.example.leaveApp.reqres.role.RoleResponse;
import com.example.leaveApp.service.RoleService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class RoleController {
    @Autowired
    RoleService roleService;
    @PostMapping(path = "/role/add")
    public RoleResponse addRole(@RequestBody RoleRequest roleRequest) {
         return roleService.addRole(roleRequest);
    }
}
