package com.onlinetaxi.userauthority.authority.service;

import com.jzh.online.taxi.commonsdk.exception.RestException;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityDTO;

import java.util.List;

public interface IAuthorityService {

    void createAuthority(AuthorityDTO authorityDTO) throws RestException;

    AuthorityDTO listAuthorityByIds(List<String> authorityIds);
}
