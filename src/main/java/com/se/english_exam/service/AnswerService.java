package com.se.english_exam.service;

import com.se.english_exam.mapper.AnswerMapper;
import com.se.english_exam.pojo.Answer;
import com.se.english_exam.pojo.ExamPaper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AnswerService {

    @Resource
    ExamPaperService examPaperService;

    @Resource
    AnswerMapper answerMapper;

    private int getChoiceScore(
            String realAnswer,
            String studentAnswer
    ) {
        // 将标准答案和学生答案分割成数组
        String[] realAnswerSplit = realAnswer.split(";");
        String[] studentAnswerSplit = studentAnswer.split(";");

        // 确保两部分答案长度相同
        assert realAnswerSplit.length == studentAnswerSplit.length;

        // 计算选择题得分，每题2分
        int score = 0;
        for (int i = 0; i < realAnswerSplit.length; i++) {
            if (realAnswerSplit[i].equals(studentAnswerSplit[i])) {
                score += 2;
            }
        }

        return score;
    }

    public Boolean submitAnswer(Answer answer) {
        ExamPaper paper = examPaperService.getValidExamPaper();

        // 当前时间不在考试时间范围内（无法获得有效试卷）
        if (paper == null) {
            return false;
        }

        answer.setPaperId(paper.getId());

        // 在学生提交答案时，计算选择题得分
        answer.setChoiceScore(getChoiceScore(
                paper.getAnswer(),
                answer.getChoiceAnswer()
        ));

        return answerMapper.insert(answer) == 1;
    }
}
