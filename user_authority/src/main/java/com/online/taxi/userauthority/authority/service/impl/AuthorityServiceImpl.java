package com.online.taxi.userauthority.authority.service.impl;

import com.online.taxi.userauthority.authority.dao.IAuthorityDAO;
import com.online.taxi.userauthority.authority.entity.dto.AuthorityDTO;
import com.online.taxi.userauthority.authority.service.IAuthorityService;
import com.online.taxi.userauthority.common.exception.ErrorCodeEnum;
import com.online_taxi.exception.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class AuthorityServiceImpl implements IAuthorityService {

    @Autowired
    private IAuthorityDAO authorityDAO;

    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("^[A-Z_]+$");

    @Override
    public void createAuthority(AuthorityDTO authorityDTO) throws RestException {
        // 校验权限名称是否符合规则
        if (!UPPERCASE_PATTERN.matcher(authorityDTO.getAuthorityName()).matches()) {
            throw new RestException(ErrorCodeEnum.INVALID_AUTHORITY_NAME.getCode(), ErrorCodeEnum.INVALID_AUTHORITY_NAME.getDesc() + "：");
        }
        // 校验权限是否已经存在



    }

    @Override
    public AuthorityDTO listAuthorityByIds(List<String> authorityIds) {
        return null;
    }
}
