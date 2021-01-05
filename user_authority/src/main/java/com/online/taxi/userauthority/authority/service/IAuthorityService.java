package com.online.taxi.userauthority.authority.service;

import com.online.taxi.userauthority.authority.entity.dto.AuthorityDTO;
import com.online_taxi.exception.RestException;

import java.util.List;

public interface IAuthorityService {

    void createAuthority(AuthorityDTO authorityDTO) throws RestException;

    AuthorityDTO listAuthorityByIds(List<String> authorityIds);
}
