package com.onlinetaxi.userauthority.authority.entity.po;

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
@TableName("Authority")
public class AuthorityPO extends BasePO {
    @TableId("ID")
    private String id;

    /**
     * 权限名称
     */
    @TableField("AUTHORITY_NAME")
    private String authorityName;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;
}
