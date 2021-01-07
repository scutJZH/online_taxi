package com.onlinetaxi.userauthority.common.exception;

public enum ErrorCodeEnum {

    INVALID_AUTHORITY_NAME(1001, "无效的权限名称，权限名称只能");

    private Integer code;
    private String desc;

    ErrorCodeEnum(Integer code, String desc) {}

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
