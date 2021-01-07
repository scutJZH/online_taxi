package com.onlinetaxi.userauthority.user.controller;

import com.jzh.online.taxi.commonsdk.entity.RespResult;
import com.onlinetaxi.userauthority.user.service.IUserService;
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
