package com.se.english_exam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.se.english_exam.mapper")
public class EnglishExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnglishExamApplication.class, args);
    }

}
