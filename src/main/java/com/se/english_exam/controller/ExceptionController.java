package com.se.english_exam.controller;

import com.se.english_exam.controller.util.Result;
import com.se.english_exam.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(PermissionException.class)
    @ResponseBody
    public Result handlePermissionException(PermissionException e) {
        log.warn("权限错误", e);
        return new Result(Result.STATUS_UNSUCCESSFUL, "权限错误", null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        log.warn("服务器错误", e);
        return new Result(Result.STATUS_UNSUCCESSFUL, "服务器错误", null);
    }

}
