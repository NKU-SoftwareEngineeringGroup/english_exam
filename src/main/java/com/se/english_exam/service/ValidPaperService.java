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
        // 更新validPaper表中的id字段
        // 注：validPaper表中只有一条记录
        LambdaUpdateWrapper<ValidPaper> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ValidPaper::getId, id);
        return validPaperMapper.update(null, wrapper) == 1;
    }

    public Integer getValidPaperId() {
        // 查询validPaper表中的id字段
        // 注：validPaper表中只有一条记录
        ValidPaper validPaper = validPaperMapper.selectOne(null);
        assert validPaper != null;
        return validPaper.getId();
    }
}
