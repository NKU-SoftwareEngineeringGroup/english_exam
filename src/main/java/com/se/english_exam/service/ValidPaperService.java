package com.se.english_exam.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.se.english_exam.mapper.ValidPaperMapper;
import com.se.english_exam.pojo.ValidPaper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ValidPaperService {

    @Resource
    ValidPaperMapper validPaperMapper;

    public Boolean setValidPaperId(Integer id) {
        LambdaUpdateWrapper<ValidPaper> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ValidPaper::getId, id);
        return validPaperMapper.update(null, wrapper) == 1;
    }

    public Integer getValidPaperId() {
        ValidPaper validPaper = validPaperMapper.selectOne(null);
        assert validPaper != null;
        return validPaper.getId();
    }
}
