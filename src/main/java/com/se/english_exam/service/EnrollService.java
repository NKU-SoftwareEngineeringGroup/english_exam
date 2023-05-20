package com.se.english_exam.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.se.english_exam.mapper.StudentMapper;
import com.se.english_exam.pojo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EnrollService {

    @Resource
    private StudentMapper studentMapper;

    public Boolean enroll(Integer studentId) {
        Student student = studentMapper.selectById(studentId);

        if (student == null) {
            return false;
        }

        student.setEnroll(true);

        return studentMapper.updateById(student) == 1;
    }

    public void reset() {
        // 将所有学生的 enroll 字段设置为 false
        LambdaUpdateWrapper<Student> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Student::getEnroll, false);

        studentMapper.update(null, wrapper);
    }

}
