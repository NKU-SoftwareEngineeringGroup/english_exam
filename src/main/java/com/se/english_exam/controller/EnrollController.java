package com.se.english_exam.controller;

import com.se.english_exam.controller.util.Result;
import com.se.english_exam.service.EnrollService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/enroll")
public class EnrollController {

    @Resource
    private EnrollService enrollService;

    @PostMapping
    public Result enroll() {
        // TODO: 从 session 中获取学生 id
        Integer studentId = 1;

        if (enrollService.enroll(studentId)) {
            return new Result(Result.STATUS_SUCCESS, "报名成功", null);
        } else {
            return new Result(Result.STATUS_UNSUCCESSFUL, "报名失败", null);
        }
    }

    @DeleteMapping
    public Result reset() {
        enrollService.reset();
        return new Result(Result.STATUS_SUCCESS, "重置成功", null);
    }

}
