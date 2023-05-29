package com.se.english_exam.controller;

import com.se.english_exam.controller.util.Result;
import com.se.english_exam.pojo.Student;
import com.se.english_exam.service.EnrollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/enroll")
public class EnrollController {

    @Resource
    private EnrollService enrollService;

    @Resource
    private HttpServletRequest request;

    @PostMapping
    public Result enroll() {
        Student student = (Student) request.getSession().getAttribute("student");
        Integer studentId = student.getId();
        log.info("student enroll with id: " + studentId);

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
