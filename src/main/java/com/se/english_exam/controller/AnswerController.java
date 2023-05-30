package com.se.english_exam.controller;

import com.se.english_exam.controller.util.Result;
import com.se.english_exam.pojo.Answer;
import com.se.english_exam.pojo.Student;
import com.se.english_exam.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 作答信息控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Resource
    private AnswerService answerService;

    @Resource
    private HttpServletRequest request;

    /**
     * 学生提交答案
     */
    @PostMapping
    public Result submitAnswer(@RequestBody Answer answer) {
        Student student = (Student) request.getSession().getAttribute("student");
        Integer studentId = student.getId();
        log.info("学生 id = {} 提交答案", studentId);

        answer.setStudentId(studentId);
        if (answerService.submitAnswer(answer)) {
            return new Result(Result.STATUS_SUCCESS, "提交成功", null);
        } else {
            return new Result(Result.STATUS_UNSUCCESSFUL, "提交失败", null);
        }
    }
}
