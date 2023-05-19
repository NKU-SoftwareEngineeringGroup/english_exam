package com.se.english_exam.controller.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    public static final Integer STATUS_SUCCESS = 0;
    public static final Integer STATUS_UNSUCCESSFUL = -1;

    private Integer status;
    private String msg;
    private Object data;
}
