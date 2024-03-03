package com.ten.ten.services;

import com.ten.ten.commons.ResponseResult;
import com.ten.ten.dtos.NewRoleDto;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    ResponseEntity<ResponseResult> addNewRole(NewRoleDto newRoleDtoRequest);

}
