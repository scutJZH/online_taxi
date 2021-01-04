package com.online_taxi.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespResult<T> {
    private Integer code;
    private T data;
    private String message;
}
