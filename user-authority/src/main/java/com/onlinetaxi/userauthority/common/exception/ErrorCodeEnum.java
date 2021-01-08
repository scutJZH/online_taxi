package com.onlinetaxi.userauthority.common.exception;

public enum ErrorCodeEnum {

    INVALID_AUTHORITY_NAME(1001, "无效的权限名称、"),
    AUTHORITY_NAME_ALREADY_EXISTS(1002, "权限名称已经存在"),
    ;

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
