package com.onlinetaxi.userauthority.role.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jzh.online.taxi.commonsdk.exception.RestException;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityOutput;
import com.onlinetaxi.userauthority.authority.service.IAuthorityService;
import com.onlinetaxi.userauthority.role.dao.IRoleAuthorityRelationDAO;
import com.onlinetaxi.userauthority.role.dao.IRoleDAO;
import com.onlinetaxi.userauthority.role.entity.dto.RoleInput;
import com.onlinetaxi.userauthority.role.entity.po.RoleAuthorityRelationPO;
import com.onlinetaxi.userauthority.role.entity.po.RolePO;
import com.onlinetaxi.userauthority.role.service.IRoleService;
import com.onlinetaxi.userauthority.common.constant.UserAuthorityConstants;
import com.onlinetaxi.userauthority.common.exception.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
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
            throw new RestException(ErrorCodeEnum.ROLE_NAME_ALREADY_EXISTS.getCode(), ErrorCodeEnum.ROLE_NAME_ALREADY_EXISTS.getDesc());
        }
        // 校验权限是否存在
        if (CollectionUtils.isNotEmpty(roleInput.getAuthorityIdList())) {
            List<AuthorityOutput> authorityOutputs = authorityService.listAuthorityByIds(roleInput.getAuthorityIdList());
            List<String> invalidAuthorityIds = roleInput.getAuthorityIdList().stream()
                    .filter(authorityId -> !authorityOutputs.stream().map(AuthorityOutput::getId).collect(Collectors.toList()).contains(authorityId))
                    .collect(Collectors.toList());

            if (CollectionUtils.isNotEmpty(invalidAuthorityIds)) {
                throw new RestException(ErrorCodeEnum.INVALID_AUTHORITY_ID.getCode(), ErrorCodeEnum.INVALID_AUTHORITY_ID.getDesc()
                        + "，分别为：" + invalidAuthorityIds.toString());
            }
        }

        rolePO = generateNewRole(roleInput);
        roleDAO.insert(rolePO);

        if (CollectionUtils.isNotEmpty(roleInput.getAuthorityIdList())) {
            String roleId = rolePO.getId();
            roleInput.getAuthorityIdList().forEach(authorityId -> roleAuthorityRelationDAO.insert(new RoleAuthorityRelationPO(UUID.randomUUID().toString(), roleId, authorityId)));
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
}
