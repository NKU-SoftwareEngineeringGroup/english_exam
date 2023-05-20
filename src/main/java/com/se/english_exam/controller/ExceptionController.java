package com.se.english_exam.controller;

import com.se.english_exam.controller.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    private static final Logger logger =
            LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        logger.warn("服务器错误", e);
        return new Result(Result.STATUS_UNSUCCESSFUL, "服务器错误", null);
    }

}
