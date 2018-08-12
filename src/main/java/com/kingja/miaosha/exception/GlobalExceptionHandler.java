package com.kingja.miaosha.exception;

import com.kingja.miaosha.result.CodeMsg;
import com.kingja.miaosha.result.Result;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:TODO
 * Create Time:2018/8/11 21:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public Result bindExceptionHandler(BindException e) {
        String objectError = e.getAllErrors().get(0).getDefaultMessage();
        return Result.error(CodeMsg.ERROR_BIND.fillArgs(objectError));
    }
    @ExceptionHandler(value = ResultException.class)
    public Result resultExceptionHandler(ResultException e) {
        return Result.error(e.getCodeMsg());
    }
}
