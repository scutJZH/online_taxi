package com.onlinetaxi.userauthority.authority.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    private String id;
    private String authorityName;
    private String description;
    private LocalDateTime createTime;
    private String creatorId;
    private String creatorName;
    private LocalDateTime updateTime;
    private String updaterId;
    private String updaterName;
}
