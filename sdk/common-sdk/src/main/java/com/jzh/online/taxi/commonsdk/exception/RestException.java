package com.jzh.online.taxi.commonsdk.exception;

import lombok.Getter;

@Getter
public class RestException extends Exception {
    private final Integer code;
    private final String message;
    private Object[] params;

    public RestException(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
    public RestException(Integer code, String message, Object ... params) {
        super();
        this.code = code;
        this.message = message;
        this.params = params;
    }

    public RestException(Integer code, String message, Exception e) {
        super(e);
        this.code = code;
        this.message = message;
    }

    public RestException(Integer code, String message, Exception e, Object ... params) {
        super(e);
        this.code = code;
        this.message = message;
        this.params = params;
    }
}
