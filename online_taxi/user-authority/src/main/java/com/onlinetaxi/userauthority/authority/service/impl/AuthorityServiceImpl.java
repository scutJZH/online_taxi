package com.onlinetaxi.userauthority.authority.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.jzh.online.taxi.commonsdk.constant.CommonConstants;
import com.jzh.online.taxi.commonsdk.entity.PageResult;
import com.jzh.online.taxi.commonsdk.exception.RestException;
import com.onlinetaxi.userauthority.authority.dao.IAuthorityDAO;
import com.onlinetaxi.userauthority.authority.entity.AuthorityQuery;
import com.onlinetaxi.userauthority.authority.entity.bo.Authority;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityInInDTO;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityDTO;
import com.onlinetaxi.userauthority.authority.entity.po.AuthorityPO;
import com.onlinetaxi.userauthority.authority.service.IAuthorityService;
import com.onlinetaxi.userauthority.common.constant.UserAuthorityConstants;
import com.onlinetaxi.userauthority.common.exception.ErrorCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class

AuthorityServiceImpl implements IAuthorityService {

    @Autowired
    private IAuthorityDAO authorityDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAuthority(AuthorityInInDTO authorityInDTO) throws RestException {
        // 校验权限名称是否符合规则
        if (!UserAuthorityConstants.UPPERCASE_AND_UNDERLINE_PATTERN.matcher(authorityInDTO.getAuthorityName()).matches()) {
            throw new RestException(ErrorCodeEnum.INVALID_AUTHORITY_NAME.getCode(), ErrorCodeEnum.INVALID_AUTHORITY_NAME.getDesc());
        }
        // 校验权限是否已经存在
        AuthorityPO authorityPO = authorityDAO.selectOne(Wrappers.<AuthorityPO>lambdaQuery()
                .eq(AuthorityPO::getAuthorityName, authorityInDTO.getAuthorityName()).eq(AuthorityPO::getIsDeleted, false));
        if (!Objects.isNull(authorityPO)) {
            throw new RestException(ErrorCodeEnum.AUTHORITY_NAME_ALREADY_EXISTS.getCode(), ErrorCodeEnum.AUTHORITY_NAME_ALREADY_EXISTS.getDesc());
        }

        authorityPO = generateNewAuthority(authorityInDTO);
        authorityDAO.insert(authorityPO);
    }

    @Override
    public List<AuthorityDTO> listAuthorityByIds(List<String> authorityIds) {
        if (CollectionUtils.isEmpty(authorityIds)) {
            return Collections.emptyList();
        }
        List<AuthorityPO> authorityPOList = new ArrayList<>();
        List<List<String>> partition = Lists.partition(authorityIds, CommonConstants.MAX_IN_SIZE);
        partition.forEach(ids -> authorityPOList.addAll(authorityDAO.selectList(Wrappers.<AuthorityPO>lambdaQuery()
                .eq(AuthorityPO::getIsDeleted, false).in(AuthorityPO::getId))));

        return authorityPOList.stream().map(this::transAuthorityPO2Output).collect(Collectors.toList());
    }

    @Override
    public PageResult<AuthorityDTO> page(AuthorityQuery query) {
        IPage<AuthorityPO> pageCondition = new Page<>(query.getPageNo(), query.getPageSize());
        IPage<AuthorityPO> authorityPOPage = authorityDAO.selectPage(pageCondition, Wrappers.<AuthorityPO>lambdaQuery().eq(AuthorityPO::getIsDeleted, false));
        List<AuthorityDTO> authorityDTOList = authorityPOPage.getRecords().stream().map(this::transAuthorityPO2Output).collect(Collectors.toList());
        return new PageResult<>(authorityPOPage.getTotal(), authorityDTOList);
    }

    /**
     * 生成新的权限
     * @param authorityInDTO
     * @return
     */
    private AuthorityPO generateNewAuthority(AuthorityInInDTO authorityInDTO) {
        AuthorityPO authorityPO = new AuthorityPO().toBuilder().id(UUID.randomUUID().toString())
                .authorityName(authorityInDTO.getAuthorityName()).description(authorityInDTO.getDescription()).build();
        authorityPO.initPO(authorityInDTO);
        return authorityPO;
    }

    /**
     * 将权限PO转化为Output对象
     * @param authorityPO
     * @return
     */
    private AuthorityDTO transAuthorityPO2Output(AuthorityPO authorityPO) {
        AuthorityDTO authorityDTO = new AuthorityDTO();
        BeanUtils.copyProperties(authorityPO, authorityDTO);
        return authorityDTO;
    }
}
