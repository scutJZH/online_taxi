package com.jzh.online.taxi.commonsdk.exception;

import lombok.Getter;

@Getter
public class RestException extends Exception {
    private final Integer code;
    private final String message;

    public RestException(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public RestException(Integer code, String message, Exception e) {
        super(e);
        this.code = code;
        this.message = message;
    }
}
