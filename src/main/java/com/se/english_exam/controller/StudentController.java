package com.se.english_exam.controller;

import com.se.english_exam.controller.util.Result;
import com.se.english_exam.pojo.Student;
import com.se.english_exam.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 学生注册控制器
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 学生注册
     */
    @PostMapping
    public Result register(@RequestBody Student student) {
        if (studentService.register(student)) {
            return new Result(Result.STATUS_SUCCESS, "注册成功", null);
        } else {
            return new Result(Result.STATUS_UNSUCCESSFUL, "注册失败", null);
        }
    }
}
