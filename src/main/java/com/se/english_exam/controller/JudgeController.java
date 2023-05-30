package com.se.english_exam.controller;

import com.se.english_exam.controller.util.Result;
import com.se.english_exam.pojo.Answer;
import com.se.english_exam.service.JudgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 判卷控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/judge")
public class JudgeController {

    @Resource
    private JudgeService judgeService;

    /**
     * 老师获取学生作答
     */
    @GetMapping
    public Result getStudentAnswer() {
        log.info("老师获取学生作答");

        Answer answer = judgeService.getStudentAnswer();
        if (answer != null) {
            return new Result(Result.STATUS_SUCCESS, "查询成功", answer);
        } else {
            return new Result(Result.STATUS_UNSUCCESSFUL, "判卷结束", null);
        }
    }

    /**
     * 老师对学生主观题打分
     */
    @PostMapping
    public Result judgeStudentAnswer(@RequestBody Answer answer) {
        log.info("老师对学生主观题打分");

        if (judgeService.judgeStudentAnswer(answer)) {
            return new Result(Result.STATUS_SUCCESS, "判卷成功", null);
        } else {
            return new Result(Result.STATUS_UNSUCCESSFUL, "判卷失败", null);
        }
    }
}
