package com.onlinetaxi.userauthority.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onlinetaxi.userauthority.role.entity.po.RolePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IRoleDAO extends BaseMapper<RolePO> {
}
