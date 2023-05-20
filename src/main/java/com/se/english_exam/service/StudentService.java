package com.se.english_exam.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.se.english_exam.mapper.StudentMapper;
import com.se.english_exam.pojo.Student;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class StudentService {

    @Resource
    private StudentMapper studentMapper;

    public Boolean register(Student student) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getIdCard, student.getIdCard());

        // 如果已经存在该身份证号，则注册失败
        if (studentMapper.selectOne(wrapper) != null) {
            return false;
        }

        // 将学生信息插入数据库，密码使用md5加密
        student.setPassword(DigestUtils.md5DigestAsHex(
                student.getPassword().getBytes()));

        student.setEnroll(false);
        return studentMapper.insert(student) == 1;
    }
}
