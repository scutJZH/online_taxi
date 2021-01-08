package com.onlinetaxi.userauthority.authority.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.jzh.online.taxi.commonsdk.constant.CommonConstants;
import com.jzh.online.taxi.commonsdk.exception.RestException;
import com.onlinetaxi.userauthority.authority.dao.IAuthorityDAO;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityInput;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityOutput;
import com.onlinetaxi.userauthority.authority.entity.po.AuthorityPO;
import com.onlinetaxi.userauthority.authority.service.IAuthorityService;
import com.onlinetaxi.userauthority.common.constant.UserAuthorityConstants;
import com.onlinetaxi.userauthority.common.exception.ErrorCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements IAuthorityService {

    @Autowired
    private IAuthorityDAO authorityDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAuthority(AuthorityInput authorityInput) throws RestException {
        // 校验权限名称是否符合规则
        if (!UserAuthorityConstants.UPPERCASE_AND_UNDERLINE_PATTERN.matcher(authorityInput.getAuthorityName()).matches()) {
            throw new RestException(ErrorCodeEnum.INVALID_AUTHORITY_NAME.getCode(), ErrorCodeEnum.INVALID_AUTHORITY_NAME.getDesc());
        }
        // 校验权限是否已经存在
        AuthorityPO authorityPO = authorityDAO.selectOne(Wrappers.<AuthorityPO>lambdaQuery()
                .eq(AuthorityPO::getAuthorityName, authorityInput.getAuthorityName()).eq(AuthorityPO::getIsDeleted, false));
        if (!Objects.isNull(authorityPO)) {
            throw new RestException(ErrorCodeEnum.AUTHORITY_NAME_ALREADY_EXISTS.getCode(), ErrorCodeEnum.AUTHORITY_NAME_ALREADY_EXISTS.getDesc());
        }

        authorityPO = generateNewAuthority(authorityInput);
        authorityDAO.insert(authorityPO);
    }

    @Override
    public List<AuthorityOutput> listAuthorityByIds(List<String> authorityIds) {
        if (CollectionUtils.isEmpty(authorityIds)) {
            return Collections.emptyList();
        }
        List<AuthorityPO> authorityPOList = new ArrayList<>();
        List<List<String>> partition = Lists.partition(authorityIds, CommonConstants.MAX_IN_SIZE);
        partition.forEach(ids -> authorityPOList.addAll(authorityDAO.selectList(Wrappers.<AuthorityPO>lambdaQuery()
                .eq(AuthorityPO::getIsDeleted, false).in(AuthorityPO::getAuthorityName))));

        return authorityPOList.stream().map(this::transAuthorityPO2Output).collect(Collectors.toList());
    }

    /**
     * 生成新的权限
     * @param authorityInput
     * @return
     */
    private AuthorityPO generateNewAuthority(AuthorityInput authorityInput) {
        AuthorityPO authorityPO = new AuthorityPO().toBuilder().id(UUID.randomUUID().toString())
                .authorityName(authorityInput.getAuthorityName()).description(authorityInput.getDescription()).build();
        authorityPO.initPO(authorityInput);
        return authorityPO;
    }

    /**
     * 将权限PO转化为Output对象
     * @param authorityPO
     * @return
     */
    private AuthorityOutput transAuthorityPO2Output(AuthorityPO authorityPO) {
        AuthorityOutput authorityOutput = new AuthorityOutput();
        BeanUtils.copyProperties(authorityPO, authorityOutput);
        return authorityOutput;
    }
}
