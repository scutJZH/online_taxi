package com.online.taxi.userauthority.authority.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityPO {
    @TableId("ID")
    private String Id;

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
