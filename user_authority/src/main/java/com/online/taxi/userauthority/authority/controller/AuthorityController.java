package com.online.taxi.userauthority.authority.controller;

import com.online.taxi.userauthority.authority.entity.dto.AuthorityDTO;
import com.online.taxi.userauthority.authority.service.IAuthorityService;
import com.online_taxi.entity.RespResult;
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
    public RespResult<Void> createAuthority(@Valid @RequestBody AuthorityDTO authorityDTO) {
        authorityService.createAuthority(authorityDTO);
        return new RespResult<>();
    }
}
