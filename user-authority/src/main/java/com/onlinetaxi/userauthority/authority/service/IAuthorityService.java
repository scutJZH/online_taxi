package com.onlinetaxi.userauthority.authority.service;

import com.jzh.online.taxi.commonsdk.exception.RestException;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityInput;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityOutput;

import java.util.List;

public interface IAuthorityService {

    /**
     * 创建新的权限
     * @param authorityInput
     * @throws RestException
     */
    void createAuthority(AuthorityInput authorityInput) throws RestException;

    /**
     * 按权限id列表获取权限列表
     * @param authorityIds
     * @return
     */
    List<AuthorityOutput> listAuthorityByIds(List<String> authorityIds);
}
