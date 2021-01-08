package com.onlinetaxi.userauthority.role.service;

import com.jzh.online.taxi.commonsdk.exception.RestException;
import com.onlinetaxi.userauthority.role.entity.dto.RoleInput;

public interface IRoleService {

    /**
     * 创建新的角色
     * @param roleInput
     */
    void createRole(RoleInput roleInput) throws RestException;
}
