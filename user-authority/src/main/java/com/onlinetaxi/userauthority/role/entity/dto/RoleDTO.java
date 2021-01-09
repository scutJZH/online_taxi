package com.onlinetaxi.userauthority.role.entity.dto;

import com.onlinetaxi.userauthority.authority.entity.dto.AuthorityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 权限列表
     */
    private List<AuthorityDTO> authorityList;

    private LocalDateTime createTime;
    private String creatorId;
    private String creatorName;
    private LocalDateTime updateTime;
    private String updaterId;
    private String updaterName;
}
