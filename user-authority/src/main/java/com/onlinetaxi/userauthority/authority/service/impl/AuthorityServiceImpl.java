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
import com.onlinetaxi.userauthority.common.exception.ErrorCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements IAuthorityService {

    @Autowired
    private IAuthorityDAO authorityDAO;

    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("^[A-Z_]+$");

    @Override
    public void createAuthority(AuthorityInput authorityInput) throws RestException {
        // 校验权限名称是否符合规则
        if (!UPPERCASE_PATTERN.matcher(authorityInput.getAuthorityName()).matches()) {
            throw new RestException(ErrorCodeEnum.INVALID_AUTHORITY_NAME.getCode(), ErrorCodeEnum.INVALID_AUTHORITY_NAME.getDesc());
        }
        // 校验权限是否已经存在
        AuthorityPO authorityPO = authorityDAO.selectOne(Wrappers.<AuthorityPO>lambdaQuery()
                .eq(AuthorityPO::getAuthorityName, authorityInput.getAuthorityName()));
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
        AuthorityPO authorityPO = new AuthorityPO().toBuilder().Id(UUID.randomUUID().toString()).authorityName(authorityInput.getAuthorityName())
                .description(authorityInput.getDescription()).build();
        authorityPO.setCreateTime(LocalDateTime.now());
        authorityPO.setCreatorId(authorityInput.getOperatorId());
        authorityPO.setCreatorName(authorityInput.getOperatorName());
        authorityPO.setUpdateTime(LocalDateTime.now());
        authorityPO.setUpdaterId(authorityInput.getOperatorId());
        authorityPO.setUpdaterName(authorityInput.getOperatorName());
        authorityPO.setIsDeleted(false);
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
