package com.se.english_exam.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.se.english_exam.mapper.AnswerMapper;
import com.se.english_exam.pojo.Answer;
import com.se.english_exam.pojo.Score;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ScoreService {

    @Resource
    private ValidPaperService validPaperService;

    @Resource
    private AnswerMapper answerMapper;

    public Score getScore(Integer studentId) {
        Integer paperId = validPaperService.getValidPaperId();

        LambdaQueryWrapper<Answer> wrapper = new LambdaQueryWrapper<>();

        // 查找已经批改的学生作答
        wrapper.eq(Answer::getPaperId, paperId)
                .eq(Answer::getStudentId, studentId)
                .isNotNull(Answer::getChoiceScore)
                .isNotNull(Answer::getSubjective1Score)
                .isNotNull(Answer::getSubjective2Score);

        Answer answer = answerMapper.selectOne(wrapper);

        if (answer == null) {
            return null;
        }

        // 抽取答案部分，返回给前端
        return new Score(
                answer.getChoiceScore(),
                answer.getSubjective1Score(),
                answer.getSubjective2Score()
        );
    }
}
