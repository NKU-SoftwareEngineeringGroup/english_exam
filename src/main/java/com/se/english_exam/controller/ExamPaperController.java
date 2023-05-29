package com.se.english_exam.controller;

import com.se.english_exam.controller.util.Result;
import com.se.english_exam.pojo.ExamPaper;
import com.se.english_exam.service.ExamPaperService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * 试卷控制器
 */
@RestController
@RequestMapping("/api/exam_paper")
public class ExamPaperController {

    @Resource
    private ExamPaperService service;

    /**
     * 学生获取试卷信息
     */
    @GetMapping
    public Result getValidExamPaper() {
        ExamPaper paper = service.getValidExamPaper();
        if (paper != null) {
            return new Result(Result.STATUS_SUCCESS, "查询成功", paper);
        } else {
            return new Result(Result.STATUS_UNSUCCESSFUL, "当前无考试", null);
        }
    }

    /**
     * 管理员上传试卷
     */
    @PostMapping
    public Result createExamPaper(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("answer") String answer,
            @RequestParam("startTime") Long startTime,
            @RequestParam("endTime") Long endTime
    ) throws IOException {
        if (service.createExamPaper(
                file,
                name,
                answer,
                new Date(startTime),
                new Date(endTime)
        )) {
            return new Result(Result.STATUS_SUCCESS, "创建成功", null);
        } else {
            return new Result(Result.STATUS_UNSUCCESSFUL, "创建失败", null);
        }
    }

}
