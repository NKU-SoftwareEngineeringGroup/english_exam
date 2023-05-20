package com.se.english_exam.controller;

import com.se.english_exam.controller.util.Result;
import com.se.english_exam.pojo.Answer;
import com.se.english_exam.service.JudgeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/judge")
public class JudgeController {

    @Resource
    private JudgeService judgeService;

    @GetMapping
    public Result getStudentAnswer() {
        Answer answer = judgeService.getStudentAnswer();
        if (answer != null) {
            return new Result(Result.STATUS_SUCCESS, "查询成功", answer);
        } else {
            return new Result(Result.STATUS_UNSUCCESSFUL, "判卷结束", null);
        }
    }

    @PostMapping
    public Result judgeStudentAnswer(@RequestBody Answer answer) {
        if (judgeService.judgeStudentAnswer(answer)) {
            return new Result(Result.STATUS_SUCCESS, "判卷成功", null);
        } else {
            return new Result(Result.STATUS_UNSUCCESSFUL, "判卷失败", null);
        }
    }
}
