package com.onlinetaxi.userauthority.common.exception;

public enum ErrorCodeEnum {

    /**
     * 权限相关
     */
    INVALID_AUTHORITY_NAME(1001, "无效的权限名称"),
    AUTHORITY_NAME_ALREADY_EXISTS(1002, "权限名称已经存在"),
    INVALID_AUTHORITY_ID(1003, "无效的权限id"),
    /**
     * 角色相关
     */
    INVALID_ROLE_NAME(2001, "无效的角色名称"),
    ROLE_NAME_ALREADY_EXISTS(2002, "角色名称已经存在"),
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
