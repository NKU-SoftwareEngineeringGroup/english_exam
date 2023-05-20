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
        String[] realAnswerSplit = realAnswer.split(";");
        String[] studentAnswerSplit = studentAnswer.split(";");
        assert realAnswerSplit.length == studentAnswerSplit.length;

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
        answer.setChoiceScore(getChoiceScore(
                paper.getAnswer(),
                answer.getChoiceAnswer()
        ));

        return answerMapper.insert(answer) == 1;
    }
}
