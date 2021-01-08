package com.onlinetaxi.userauthority.role.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuthorityRelationPO {
    private String id;
    private String roleId;
    private String authorityId;
}
