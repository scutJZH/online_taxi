package com.onlinetaxi.userauthority.authority.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDTO {
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
