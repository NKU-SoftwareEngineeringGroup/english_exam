package com.se.english_exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private Integer id;
    private Integer paperId;
    private Integer studentId;
    private String choiceAnswer;
    private String subjective1Answer;
    private String subjective2Answer;
    private Integer choiceScore;
    private Integer subjective1Score;
    private Integer subjective2Score;
}
