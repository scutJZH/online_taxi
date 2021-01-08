package com.onlinetaxi.userauthority.authority.controller;

import com.jzh.online.taxi.commonsdk.entity.RespResult;
import com.jzh.online.taxi.commonsdk.exception.RestException;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityInput;
import com.onlinetaxi.userauthority.authority.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user_authority")
public class AuthorityController {

    @Autowired
    private IAuthorityService authorityService;

    @PostMapping("/v1/authority")
    public RespResult<Void> createAuthority(@Valid @RequestBody AuthorityInput authorityInput) throws RestException {
        authorityService.createAuthority(authorityInput);
        return new RespResult<>();
    }
}
