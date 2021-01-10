package com.jzh.online.taxi.commonsdk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseInDTO {
    @Valid
    @NotNull
    private Operator operator;
}
