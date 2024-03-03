package com.ten.ten.services.impl;

import com.ten.ten.commons.ResponseResult;
import com.ten.ten.dtos.NewRoleDto;
import com.ten.ten.entities.RoleEntity;
import com.ten.ten.repositories.RoleRepository;
import com.ten.ten.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<ResponseResult> addNewRole(NewRoleDto newRoleDtoRequest) {
        RoleEntity newRole = new RoleEntity();
        newRole.setNameRole(newRoleDtoRequest.getNameRole());
        newRole.setDescription(newRoleDtoRequest.getDescription());
        roleRepository.save(newRole);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseResult(200, null, "ADD ROLE SUCCESS"));
    }

}
