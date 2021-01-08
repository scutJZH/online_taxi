package com.jzh.online.taxi.commonsdk.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BasePO {
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 创建者ID
     */
    @TableField("CREATOR_ID")
    private String creatorId;

    /**
     * 创建者名称
     */
    @TableField("CREATOR_NAME")
    private String creatorName;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 更新者id
     */
    @TableField("UPDATER_ID")
    private String updaterId;

    /**
     * 更新者名称
     */
    @TableField("UPDATER_NAME")
    private String updaterName;

    /**
     * 是否删除
     */
    @TableField("IS_DELETED")
    private Boolean isDeleted;
    
    public void initPO(BaseDTO baseDTO) {
        this.setCreateTime(LocalDateTime.now());
        this.setCreatorId(baseDTO.getOperatorId());
        this.setCreatorName(baseDTO.getOperatorName());
        this.setUpdateTime(LocalDateTime.now());
        this.setUpdaterId(baseDTO.getOperatorId());
        this.setUpdaterName(baseDTO.getOperatorName());
        this.setIsDeleted(false);
    }
}
