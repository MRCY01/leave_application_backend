package com.example.leaveApp.service;

import com.example.leaveApp.entity.Role;
import com.example.leaveApp.repo.RoleRepository;
import com.example.leaveApp.reqres.role.RoleRequest;
import com.example.leaveApp.reqres.role.RoleResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @SneakyThrows
    public RoleResponse addRole(RoleRequest roleRequest) {
        RoleResponse roleResponse = new RoleResponse();
        String roleName = roleRequest.getRoleName();
        String permission = roleRequest.getPermission();

        Role role = new Role();
        role.setRoleName(roleName);
        role.setPermission(permission);

        roleRepository.save(role);
        if(roleRepository.findById(role.getId()).isEmpty()){
            throw new SQLDataException("id not found");
        }
        try{
            roleResponse.setId(role.getId());
            roleResponse.setRoleName(roleName);
            roleResponse.setPermission(permission);

        }catch(Exception e){
            throw new SQLDataException(e);
        }
        return roleResponse;
    }
}
