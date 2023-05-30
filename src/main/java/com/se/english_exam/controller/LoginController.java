package com.se.english_exam.controller;

import com.se.english_exam.controller.util.Result;
import com.se.english_exam.pojo.Admin;
import com.se.english_exam.pojo.LoginToken;
import com.se.english_exam.pojo.Student;
import com.se.english_exam.pojo.Teacher;
import com.se.english_exam.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {

    @Resource
    HttpServletRequest request;

    @Resource
    LoginService loginService;

    /**
     * 学生登录
     */
    @PostMapping("/login/student")
    public Result studentLogin(@RequestBody LoginToken token) {
        log.info("学生登录，用户名：{}", token.getUsername());

        Student student =
                loginService.studentLogin(token.getUsername(), token.getPassword());

        if (student == null) {
            return new Result(Result.STATUS_UNSUCCESSFUL, "登录失败", null);
        } else {
            request.getSession().setAttribute("student", student);
            return new Result(Result.STATUS_SUCCESS, "登录成功", null);
        }
    }

    /**
     * 老师登录
     */
    @PostMapping("/login/teacher")
    public Result teacherLogin(@RequestBody LoginToken token) {
        log.info("老师登录，用户名：{}", token.getUsername());

        Teacher teacher =
                loginService.teacherLogin(token.getUsername(), token.getPassword());

        if (teacher == null) {
            return new Result(Result.STATUS_UNSUCCESSFUL, "登录失败", null);
        } else {
            request.getSession().setAttribute("teacher", teacher);
            return new Result(Result.STATUS_SUCCESS, "登录成功", null);
        }
    }

    /**
     * 管理员登录
     */
    @PostMapping("/login/admin")
    public Result adminLogin(@RequestBody LoginToken token) {
        log.info("管理员登录，用户名：{}", token.getUsername());

        Admin admin =
                loginService.adminLogin(token.getUsername(), token.getPassword());

        if (admin == null) {
            return new Result(Result.STATUS_UNSUCCESSFUL, "登录失败", null);
        } else {
            request.getSession().setAttribute("admin", admin);
            return new Result(Result.STATUS_SUCCESS, "登录成功", null);
        }
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public Result logout() {
        log.info("登出");

        request.getSession().invalidate();
        return new Result(Result.STATUS_SUCCESS, "登出成功", null);
    }

    /**
     * 获取已登录用户的名称
     */
    @GetMapping("/username")
    public Result getUserName() {
        log.info("获取已登录用户的名称");

        Student student = (Student) request.getSession().getAttribute("student");
        if (student != null) {
            return new Result(Result.STATUS_SUCCESS, "获取学生名称成功", student.getName());
        }

        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        if (teacher != null) {
            return new Result(Result.STATUS_SUCCESS, "获取教师名称成功", teacher.getName());
        }

        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null) {
            return new Result(Result.STATUS_SUCCESS, "获取管理员名称成功", admin.getName());
        }

        return new Result(Result.STATUS_UNSUCCESSFUL, "获取名称失败", null);
    }
}
