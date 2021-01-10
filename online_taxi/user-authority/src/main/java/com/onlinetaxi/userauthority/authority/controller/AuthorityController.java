package com.onlinetaxi.userauthority.authority.controller;

import com.jzh.online.taxi.commonsdk.constant.PageConstants;
import com.jzh.online.taxi.commonsdk.entity.PageResult;
import com.jzh.online.taxi.commonsdk.entity.RespResult;
import com.jzh.online.taxi.commonsdk.exception.RestException;
import com.onlinetaxi.userauthority.authority.entity.AuthorityQuery;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityInDTO;
import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityDTO;
import com.onlinetaxi.userauthority.authority.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user_authority")
public class AuthorityController {

    @Autowired
    private IAuthorityService authorityService;

    @PostMapping("/v1/authority")
    public RespResult<Void> createAuthority(@Valid @RequestBody AuthorityInDTO authorityInDTO) throws RestException {
        authorityService.createAuthority(authorityInDTO);
        return new RespResult<>();
    }

    @GetMapping("/v1/authority")
    public RespResult<PageResult<AuthorityDTO>> listAuthority(@RequestParam(value = "page_no", defaultValue = PageConstants.DEFAULT_PAGE_NO) Integer pageNo,
                                                              @RequestParam(value = "page_size", defaultValue = PageConstants.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                              @RequestParam(value = "sort_by", defaultValue = PageConstants.DEFAULT_SORT_BY) List<String> sortByList,
                                                              @RequestParam(value = "sort_direction", defaultValue = PageConstants.DEFAULT_SORT_DIRECTION) List<String> sortDirection) {
        AuthorityQuery query = new AuthorityQuery();
        query.init(pageNo, pageSize, sortByList, sortDirection);
        return new RespResult<>(authorityService.page(query));
    }
}
