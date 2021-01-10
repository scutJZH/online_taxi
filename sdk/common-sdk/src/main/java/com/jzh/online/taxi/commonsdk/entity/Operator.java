package com.jzh.online.taxi.commonsdk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operator {
    @NonNull
    private String operatorId;
    @NonNull
    private String operatorName;
}