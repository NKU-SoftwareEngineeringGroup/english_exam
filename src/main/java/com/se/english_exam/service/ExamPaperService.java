package com.se.english_exam.service;

import com.se.english_exam.mapper.ExamPaperMapper;
import com.se.english_exam.pojo.ExamPaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class ExamPaperService {

    private static final Logger logger =
            LoggerFactory.getLogger(ExamPaperService.class);

    @Resource
    private ValidPaperService validPaperService;

    @Resource
    private ExamPaperMapper examPaperMapper;

    private void createFileDir() {
        File path = new File("file");
        if (!path.exists()) {
            path.mkdirs();
        }
    }

    private Boolean isInTimeRange(ExamPaper examPaper) {
        // 获得当前时间
        Date currentTime = new Date();

        // 判断当前时间是否在考试时间范围内
        return currentTime.after(examPaper.getStartTime()) &&
                currentTime.before(examPaper.getEndTime());
    }

    public ExamPaper getValidExamPaper() {
        // 获取当前试卷
        ExamPaper examPaper = examPaperMapper.selectById(
                validPaperService.getValidPaperId());
        assert examPaper != null;

        return isInTimeRange(examPaper) ? examPaper : null;
    }

    public Boolean createExamPaper(
            MultipartFile file,
            String name,
            String answer,
            Date startTime,
            Date endTime
    ) throws IOException {
        String uuid = UUID.randomUUID().toString();

        // 获取原始文件扩展名
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String extension = originalFilename.substring(
                originalFilename.lastIndexOf(".")
        );

        // 保存文件
        createFileDir();
        String filename = uuid + extension;
        File dest = new File("file", filename);
        file.transferTo(dest.getAbsoluteFile());
        logger.info("file saved: " + dest.getAbsolutePath());

        // 创建试卷
        ExamPaper examPaper = new ExamPaper();
        examPaper.setName(name);
        examPaper.setPath(filename);
        examPaper.setAnswer(answer);
        examPaper.setStartTime(startTime);
        examPaper.setEndTime(endTime);

        // 插入数据库，并获取试卷id
        if (examPaperMapper.insert(examPaper) != 1) {
            return false;
        }

        // 设置当前试卷
        return validPaperService.setValidPaperId(examPaper.getId());
    }
}
