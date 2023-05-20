package com.se.english_exam.controller;

import com.se.english_exam.controller.util.Result;
import com.se.english_exam.pojo.Answer;
import com.se.english_exam.service.AnswerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Resource
    private AnswerService answerService;

    @PostMapping
    public Result submitAnswer(@RequestBody Answer answer) {
        // TODO: 从 session 中获取学生 id
        Integer studentId = 1;

        answer.setStudentId(studentId);
        if (answerService.submitAnswer(answer)) {
            return new Result(Result.STATUS_SUCCESS, "提交成功", null);
        } else {
            return new Result(Result.STATUS_UNSUCCESSFUL, "提交失败", null);
        }
    }
}
