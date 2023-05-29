package com.se.english_exam.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.se.english_exam.mapper.AdminMapper;
import com.se.english_exam.mapper.StudentMapper;
import com.se.english_exam.mapper.TeacherMapper;
import com.se.english_exam.pojo.Admin;
import com.se.english_exam.pojo.Student;
import com.se.english_exam.pojo.Teacher;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class LoginService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private AdminMapper adminMapper;

    public Student studentLogin(String idCard, String password) {
        String hashPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Student::getIdCard, idCard);
        wrapper.eq(Student::getPassword, hashPassword);

        return studentMapper.selectOne(wrapper);
    }

    public Teacher teacherLogin(String username, String password) {
        String hashPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Teacher::getName, username);
        wrapper.eq(Teacher::getPassword, hashPassword);

        return teacherMapper.selectOne(wrapper);
    }

    public Admin adminLogin(String username, String password) {
        String hashPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Admin::getName, username);
        wrapper.eq(Admin::getPassword, hashPassword);

        return adminMapper.selectOne(wrapper);
    }
}
