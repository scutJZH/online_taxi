package com.onlinetaxi.userauthority.role.entity.dto;

import com.jzh.online.taxi.commonsdk.entity.BaseInDTO;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleInput extends BaseInDTO {
    @NonNull
    @Size(min = 1, max = 50)
    private String roleName;
    @Size(max = 200)
    private String description;
    @NonNull
    private List<String> authorityIdList;
}
