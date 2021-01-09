package com.onlinetaxi.userauthority.role.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ROLE_AUTHORITY_RELATION")
public class RoleAuthorityRelationPO {
    @TableId("ID")
    private String id;
    @TableField("ROLE_ID")
    private String roleId;
    @TableField("AUTHORITY_ID")
    private String authorityId;
}
