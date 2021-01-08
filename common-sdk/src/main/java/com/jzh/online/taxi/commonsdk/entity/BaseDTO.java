package com.jzh.online.taxi.commonsdk.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BaseDTO {
    @NonNull
    private String operatorId;
    @NonNull
    private String operatorName;
}
