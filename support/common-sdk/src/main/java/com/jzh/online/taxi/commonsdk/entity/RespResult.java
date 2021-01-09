package com.jzh.online.taxi.commonsdk.entity;

import lombok.Data;

@Data
public class RespResult<T> {
    private static final Integer SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "OK";

    private Integer code;
    private T data;
    private String message;

    public RespResult() {
        this.code = SUCCESS_CODE;
        this.message = "OK";
    }

    public RespResult(T data) {
        this.code = SUCCESS_CODE;
        this.message = "OK";
        this.data = data;
    }
}
