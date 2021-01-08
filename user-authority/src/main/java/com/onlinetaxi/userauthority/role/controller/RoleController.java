package com.onlinetaxi.userauthority.role.controller;

import com.jzh.online.taxi.commonsdk.entity.RespResult;
import com.onlinetaxi.userauthority.role.entity.dto.RoleInput;
import com.onlinetaxi.userauthority.role.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_authority")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping("/v1/role")
    public RespResult<Void> createRole(RoleInput roleInput) {
        roleService.createRole(roleInput);
        return new RespResult<>();
    }
}
