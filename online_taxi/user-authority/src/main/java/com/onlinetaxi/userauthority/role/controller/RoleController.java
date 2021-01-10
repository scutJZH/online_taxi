package com.onlinetaxi.userauthority.role.controller;

import com.jzh.online.taxi.commonsdk.entity.RespResult;
import com.jzh.online.taxi.commonsdk.exception.RestException;
import com.onlinetaxi.userauthority.role.entity.dto.RoleInput;
import com.onlinetaxi.userauthority.role.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user_authority")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping("/v1/role")
    public RespResult<Void> createRole(@Valid @RequestBody RoleInput roleInput) throws RestException {
        roleService.createRole(roleInput);
        return new RespResult<>();
    }

    @PutMapping("/v1/role")
    public RespResult<Void> updateRole(@Valid @RequestBody RoleInput roleInput) {
        roleService.updateRole(roleInput);
        return new RespResult<>();
    }
}
