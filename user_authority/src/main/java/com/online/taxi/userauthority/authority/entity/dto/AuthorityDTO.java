package com.online.taxi.userauthority.authority.entity.dto;

import com.online_taxi.entity.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDTO extends BaseDTO {
    private String Id;
    @NonNull
    @Size(min = 1, max = 50)
    private String authorityName;
    @NonNull
    @Size(max = 200)
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
