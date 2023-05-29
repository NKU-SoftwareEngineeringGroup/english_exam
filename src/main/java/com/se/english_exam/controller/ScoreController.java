package com.se.english_exam.controller;

import com.se.english_exam.controller.util.Result;
import com.se.english_exam.pojo.Score;
import com.se.english_exam.pojo.Student;
import com.se.english_exam.service.ScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

    @Resource
    private ScoreService scoreService;

    @Resource
    private HttpServletRequest request;

    @GetMapping
    public Result getScore() {
        Student student = (Student) request.getSession().getAttribute("student");
        Integer studentId = student.getId();

        Score score = scoreService.getScore(studentId);

        if (score != null) {
            return new Result(Result.STATUS_SUCCESS, "查询成功", score);
        } else {
            return new Result(Result.STATUS_UNSUCCESSFUL, "当前无成绩", null);
        }
    }
}
