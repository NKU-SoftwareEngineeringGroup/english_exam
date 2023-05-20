package com.se.english_exam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    private Integer choiceScore;
    private Integer subjective1Score;
    private Integer subjective2Score;
}
