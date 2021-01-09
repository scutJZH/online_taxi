package com.onlinetaxi.userauthority.authority.service;

import com.jzh.online.taxi.commonsdk.entity.PageResult;
import com.jzh.online.taxi.commonsdk.exception.RestException;
import com.onlinetaxi.userauthority.authority.entity.AuthorityQuery;
import com.onlinetaxi.userauthority.authority.entity.bo.Authority;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityInInDTO;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityDTO;

import java.util.List;

public interface IAuthorityService {

    /**
     * 创建新的权限
     * @param authorityInDTO
     * @throws RestException
     */
    void createAuthority(AuthorityInInDTO authorityInDTO) throws RestException;

    /**
     * 按权限id列表获取权限列表
     * @param authorityIds
     * @return
     */
    List<AuthorityDTO> listAuthorityByIds(List<String> authorityIds);

    /**
     * 分页查询权限列表
     * @param query
     * @return
     */
    PageResult<AuthorityDTO> page(AuthorityQuery query);
}
