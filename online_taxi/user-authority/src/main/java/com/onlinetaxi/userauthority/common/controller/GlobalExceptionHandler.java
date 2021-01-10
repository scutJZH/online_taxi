package com.onlinetaxi.userauthority.common.controller;

import com.jzh.online.taxi.commonsdk.constant.CommonConstants;
import com.jzh.online.taxi.commonsdk.entity.RespResult;
import com.jzh.online.taxi.commonsdk.exception.RestException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    @ExceptionHandler(RestException.class)
    public RespResult<Void> restExceptionHandler(RestException restException) {
        RespResult<Void> respResult = new RespResult<>();
        respResult.setCode(restException.getCode());
        respResult.setMessage(restException.getMessage());
        return respResult;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespResult<Void> handleArgumentNotValidException(MethodArgumentNotValidException e) {
        String errors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + fieldError.getDefaultMessage())
                .collect(Collectors.joining(CommonConstants.COMMA_DELIMITER));

        RespResult<Void> respResult = new RespResult<>();
        respResult.setCode(CommonConstants.METHOD_ARG_EX_CODE);
        respResult.setMessage(errors);
        return respResult;
    }

    @ExceptionHandler(value = Exception.class)
    public RespResult<Void> exceptionHandler() {
        RespResult<Void> respResult = new RespResult<>();
        respResult.setCode(CommonConstants.EXCEPTION_CODE);
        respResult.setMessage(CommonConstants.EXCEPTION_MSG);
        return respResult;
    }
}
