package com.jzh.online.taxi.commonsdk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operator {
    @NotNull
    private String operatorId;
    @NotNull
    private String operatorName;
}
