package com.onlinetaxi.userauthority.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onlinetaxi.userauthority.role.entity.dto.RoleDTO;
import com.onlinetaxi.userauthority.role.entity.po.RolePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IRoleDAO extends BaseMapper<RolePO> {
    /**
     * 通过id查询角色列表
     * @param roleIds
     * @return
     */
    List<RoleDTO> listRoleByIds(@Param("roleIds") List<String> roleIds);
}
