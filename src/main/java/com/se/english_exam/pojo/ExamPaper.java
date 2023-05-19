package com.se.english_exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamPaper {
    private Integer id;
    private String name;
    private String path;
    private String answer;
    private Date startTime;
    private Date endTime;
}
