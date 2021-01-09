package com.onlinetaxi.userauthority.role.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jzh.online.taxi.commonsdk.entity.BasePO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@TableName("`ROLE`")
public class RolePO extends BasePO {
    @TableId("ID")
    private String id;

    /**
     * 角色名称
     */
    @TableField("ROLE_NAME")
    private String roleName;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;
}
