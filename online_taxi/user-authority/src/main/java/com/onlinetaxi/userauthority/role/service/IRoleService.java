package com.onlinetaxi.userauthority.role.service;

import com.jzh.online.taxi.commonsdk.entity.PageResult;
import com.jzh.online.taxi.commonsdk.exception.RestException;
import com.onlinetaxi.userauthority.role.entity.RoleQuery;
import com.onlinetaxi.userauthority.role.entity.dto.RoleDTO;
import com.onlinetaxi.userauthority.role.entity.dto.RoleInput;

import java.util.List;

public interface IRoleService {

    /**
     * 创建新的角色
     * @param roleInput
     */
    void createRole(RoleInput roleInput) throws RestException;

    /**
     * 通过角色id获取角色列表
     * @param roleIds
     * @return
     */
    List<RoleDTO> listRoleByIds(List<String> roleIds);

    /**
     * 分页查询角色
     * @param query
     * @return
     */
    PageResult<RoleDTO> page(RoleQuery query);
}
