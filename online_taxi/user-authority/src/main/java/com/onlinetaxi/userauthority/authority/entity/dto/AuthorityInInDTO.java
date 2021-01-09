package com.onlinetaxi.userauthority.authority.entity.dto;

import com.jzh.online.taxi.commonsdk.entity.BaseInDTO;
import lombok.*;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthorityInInDTO extends BaseInDTO {
    @NonNull
    @Size(min = 1, max = 50)
    private String authorityName;
    @NonNull
    @Size(max = 200)
    private String description;
}
