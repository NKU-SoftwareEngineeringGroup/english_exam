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

/**
 * 报名信息控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/enroll")
public class EnrollController {

    @Resource
    private EnrollService enrollService;

    @Resource
    private HttpServletRequest request;

    /**
     * 学生报名
     */
    @PostMapping
    public Result enroll() {
        Student student = (Student) request.getSession().getAttribute("student");
        Integer studentId = student.getId();
        log.info("学生 id = {} 报名", studentId);

        if (enrollService.enroll(studentId)) {
            return new Result(Result.STATUS_SUCCESS, "报名成功", null);
        } else {
            return new Result(Result.STATUS_UNSUCCESSFUL, "报名失败", null);
        }
    }

    /**
     * 管理员清理报名信息
     */
    @DeleteMapping
    public Result reset() {
        log.info("管理员清理报名信息");

        enrollService.reset();
        return new Result(Result.STATUS_SUCCESS, "重置成功", null);
    }

}
