package com.se.english_exam.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.se.english_exam.mapper.AnswerMapper;
import com.se.english_exam.pojo.Answer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Service
public class JudgeService {

    private static final Logger logger = Logger.getLogger(JudgeService.class.getName());

    @Resource
    private ValidPaperService validPaperService;

    @Resource
    private AnswerMapper answerMapper;

    public Answer getStudentAnswer() {
        Integer paperId = validPaperService.getValidPaperId();

        logger.info("paperId: " + paperId);

        LambdaQueryWrapper<Answer> wrapper = new LambdaQueryWrapper<>();

        // 查询当前批次考试，还未批改的学生答案
        wrapper.eq(Answer::getPaperId, paperId)
                .isNull(Answer::getSubjective1Score)
                .isNull(Answer::getSubjective2Score);

        return answerMapper.selectOne(wrapper);
    }

    public Boolean judgeStudentAnswer(Answer judgeAnswer) {
        Answer fullAnswer = answerMapper.selectById(judgeAnswer.getId());

        fullAnswer.setSubjective1Score(judgeAnswer.getSubjective1Score());
        fullAnswer.setSubjective2Score(judgeAnswer.getSubjective2Score());

        return answerMapper.updateById(fullAnswer) == 1;
    }
}
