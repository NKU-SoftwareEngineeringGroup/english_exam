package com.se.english_exam.interceptor;

import com.se.english_exam.exception.PermissionException;
import javafx.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final Map<Pair<String, String>, String> roleMap;

    public LoginInterceptor() {

        // 创建权限控制列表
        roleMap = new HashMap<>();
        roleMap.put(new Pair<>("/api/answer", "POST"), "student");
        roleMap.put(new Pair<>("/api/enroll", "POST"), "student");
        roleMap.put(new Pair<>("/api/enroll", "DELETE"), "admin");
        roleMap.put(new Pair<>("/api/exam_paper", "GET"), "student");
        roleMap.put(new Pair<>("/api/exam_paper", "POST"), "admin");
        roleMap.put(new Pair<>("/api/judge", "GET"), "teacher");
        roleMap.put(new Pair<>("/api/judge", "POST"), "teacher");
        roleMap.put(new Pair<>("/api/score", "GET"), "student");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 表驱动，判断当前角色是否有权限访问该资源
        String role = roleMap.get(new Pair<>(request.getRequestURI(), request.getMethod()));

        // 非受限资源（如login相关接口），直接放行
        // 受限资源，判断当前角色是否有权限访问该资源
        if (role != null) {
            if (request.getSession().getAttribute(role) != null) {
                return true;
            } else {
                throw new PermissionException("权限错误");
            }
        }

        return true;
    }
}
