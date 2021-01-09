package com.onlinetaxi.userauthority.authority.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onlinetaxi.userauthority.authority.entity.po.AuthorityPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAuthorityDAO extends BaseMapper<AuthorityPO> {
}
