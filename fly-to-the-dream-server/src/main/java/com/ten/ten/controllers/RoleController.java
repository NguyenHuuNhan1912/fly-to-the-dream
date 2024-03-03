package com.ten.ten.controllers;


import com.ten.ten.commons.ResponseResult;
import com.ten.ten.constants.URLConstant;
import com.ten.ten.dtos.NewRoleDto;
import com.ten.ten.entities.RoleEntity;
import com.ten.ten.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URLConstant.BASE_URL_V1)
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(URLConstant.AUTH.ADD_NEW_ROLE)
    public ResponseEntity<ResponseResult> addNewRole(@RequestBody NewRoleDto newRoleDtoRequest) {
        return roleService.addNewRole(newRoleDtoRequest);
    }
}
