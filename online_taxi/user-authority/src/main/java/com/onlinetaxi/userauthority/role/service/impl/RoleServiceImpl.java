package com.onlinetaxi.userauthority.role.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.jzh.online.taxi.commonsdk.constant.CommonConstants;
import com.jzh.online.taxi.commonsdk.entity.PageResult;
import com.jzh.online.taxi.commonsdk.exception.RestException;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityDTO;
import com.onlinetaxi.userauthority.authority.service.IAuthorityService;
import com.onlinetaxi.userauthority.role.dao.IRoleAuthorityRelationDAO;
import com.onlinetaxi.userauthority.role.dao.IRoleDAO;
import com.onlinetaxi.userauthority.role.entity.RoleQuery;
import com.onlinetaxi.userauthority.role.entity.dto.RoleDTO;
import com.onlinetaxi.userauthority.role.entity.dto.RoleInput;
import com.onlinetaxi.userauthority.role.entity.po.RoleAuthorityRelationPO;
import com.onlinetaxi.userauthority.role.entity.po.RolePO;
import com.onlinetaxi.userauthority.role.service.IRoleService;
import com.onlinetaxi.userauthority.common.constant.UserAuthorityConstants;
import com.onlinetaxi.userauthority.common.exception.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IAuthorityService authorityService;

    @Autowired
    private IRoleDAO roleDAO;

    @Autowired
    private IRoleAuthorityRelationDAO roleAuthorityRelationDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRole(RoleInput roleInput) throws RestException {
        // 校验角色名称是否符合规则
        if (!UserAuthorityConstants.UPPERCASE_AND_UNDERLINE_PATTERN.matcher(roleInput.getRoleName()).matches()) {
            throw new RestException(ErrorCodeEnum.INVALID_ROLE_NAME.getCode(), ErrorCodeEnum.INVALID_ROLE_NAME.getDesc());
        }
        // 校验角色名称是否重复
        RolePO rolePO = roleDAO.selectOne(Wrappers.<RolePO>lambdaQuery()
                .eq(RolePO::getRoleName, roleInput.getRoleName()).eq(RolePO::getIsDeleted, false));
        if (!Objects.isNull(rolePO)) {
            throw new RestException(ErrorCodeEnum.ROLE_NAME_ALREADY_EXISTED.getCode(), ErrorCodeEnum.ROLE_NAME_ALREADY_EXISTED.getDesc());
        }
        // 检查权限是否存在
        checkAuthority(roleInput);

        rolePO = generateNewRole(roleInput);
        roleDAO.insert(rolePO);

        if (CollectionUtils.isNotEmpty(roleInput.getAuthorityIdList())) {
            String roleId = rolePO.getId();
            roleInput.getAuthorityIdList().forEach(authorityId ->
                    roleAuthorityRelationDAO.insert(new RoleAuthorityRelationPO(UUID.randomUUID().toString(), roleId, authorityId)));
        }
    }

    @Override
    public List<RoleDTO> listRoleByIds(List<String> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        List<RoleDTO> roleDTOList = new ArrayList<>();
        Lists.partition(roleIds, CommonConstants.MAX_IN_SIZE).forEach(partIds -> roleDTOList.addAll(roleDAO.listRoleByIds(partIds)));
        return roleDTOList;
    }

    @Override
    public PageResult<RoleDTO> page(RoleQuery query) {
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        PageInfo<RoleDTO> pageInfo = new PageInfo<>(roleDAO.listRoleByConditions(query));
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void updateRole(String roleId, RoleInput roleInput) throws RestException {
        RolePO rolePO = roleDAO.selectById(roleId);
        if (Objects.isNull(rolePO)) {
            throw new RestException(ErrorCodeEnum.ROLE_NOT_EXISTS.getCode(), ErrorCodeEnum.ROLE_NOT_EXISTS.getDesc(), roleId);
        }
        // 校验角色名称是否重复
        RolePO sameNameRole = roleDAO.selectOne(Wrappers.<RolePO>lambdaQuery()
                .eq(RolePO::getRoleName, roleInput.getRoleName()).eq(RolePO::getIsDeleted, false).ne(RolePO::getId, roleId));
        if (!Objects.isNull(sameNameRole)) {
            throw new RestException(ErrorCodeEnum.ROLE_NAME_ALREADY_EXISTED.getCode(), ErrorCodeEnum.ROLE_NAME_ALREADY_EXISTED.getDesc());
        }
        // 检查权限是否存在
        checkAuthority(roleInput);

        rolePO.setRoleName(roleInput.getRoleName());
        rolePO.setDescription(roleInput.getDescription());
        roleDAO.insert(rolePO);

        roleAuthorityRelationDAO.delete(Wrappers.<RoleAuthorityRelationPO>lambdaQuery().eq(RoleAuthorityRelationPO::getRoleId, roleId));
        if (CollectionUtils.isNotEmpty(roleInput.getAuthorityIdList())) {
            roleInput.getAuthorityIdList().forEach(authorityId ->
                    roleAuthorityRelationDAO.insert(new RoleAuthorityRelationPO(UUID.randomUUID().toString(), roleId, authorityId)));
        }
    }

    /**
     * 生成新的角色PO
     * @param roleInput
     * @return RolePO
     */
    private RolePO generateNewRole(RoleInput roleInput) {
        RolePO rolePO = new RolePO().toBuilder().id(UUID.randomUUID().toString()).roleName(roleInput.getRoleName())
                .description(roleInput.getDescription()).build();
        rolePO.initPO(roleInput);
        return rolePO;
    }

    /**
     * 检查权限是否符合要求
     * @param roleInput
     */
    private void checkAuthority(RoleInput roleInput) throws RestException {
        // 校验权限是否存在
        if (CollectionUtils.isEmpty(roleInput.getAuthorityIdList())) {
            return;
        }
        List<AuthorityDTO> authorityDTOS = authorityService.listAuthorityByIds(roleInput.getAuthorityIdList());
        List<String> invalidAuthorityIds = roleInput.getAuthorityIdList().stream()
                .filter(authorityId -> !authorityDTOS.stream().map(AuthorityDTO::getId).collect(Collectors.toList()).contains(authorityId))
                .collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(invalidAuthorityIds)) {
            throw new RestException(ErrorCodeEnum.INVALID_AUTHORITY_ID.getCode(), ErrorCodeEnum.INVALID_AUTHORITY_ID.getDesc()
                    + "，分别为：" + invalidAuthorityIds.toString());
        }
    }
}
