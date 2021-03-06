package com.onlinetaxi.userauthority.user.entiry.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPO {
    private String id;
    private String username;
    private String password;
    private Boolean isActive;
    private Boolean isForbidden;
    private LocalDateTime forbiddenStartTime;
    private LocalDateTime forbiddenEndTime;
    private Boolean isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
