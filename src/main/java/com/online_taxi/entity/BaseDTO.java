package com.online_taxi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO {
    @NonNull
    private String operatorId;
    @NonNull
    private String operatorName;
}
