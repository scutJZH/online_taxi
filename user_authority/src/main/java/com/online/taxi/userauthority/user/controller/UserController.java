package com.online.taxi.userauthority.user.controller;

import com.online.taxi.userauthority.user.service.IUserService;
import com.online_taxi.common.entity.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_authority")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/v1/user")
    public RespResult<Void> register() {

        return new RespResult<>();
    }
}
