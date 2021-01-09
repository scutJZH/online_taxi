package com.jzh.online.taxi.commonsdk.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseInDTO {
    @NonNull
    private Operator operator;
}
