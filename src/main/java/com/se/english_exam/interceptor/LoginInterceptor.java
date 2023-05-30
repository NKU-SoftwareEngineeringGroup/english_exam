package com.se.english_exam.interceptor;

import com.se.english_exam.exception.PermissionException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static class RequestInfo {
        private final String uri;
        private final String method;

        public RequestInfo(String uri, String method) {
            this.uri = uri;
            this.method = method;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RequestInfo that = (RequestInfo) o;
            return Objects.equals(uri, that.uri) && Objects.equals(method, that.method);
        }

        @Override
        public int hashCode() {
            return Objects.hash(uri, method);
        }
    }

    private final Map<RequestInfo, String> roleMap;

    public LoginInterceptor() {

        // 创建权限控制列表
        roleMap = new HashMap<>();
        roleMap.put(new RequestInfo("/api/answer", "POST"), "student");
        roleMap.put(new RequestInfo("/api/enroll", "POST"), "student");
        roleMap.put(new RequestInfo("/api/enroll", "DELETE"), "admin");
        roleMap.put(new RequestInfo("/api/exam_paper", "GET"), "student");
        roleMap.put(new RequestInfo("/api/exam_paper", "POST"), "admin");
        roleMap.put(new RequestInfo("/api/judge", "GET"), "teacher");
        roleMap.put(new RequestInfo("/api/judge", "POST"), "teacher");
        roleMap.put(new RequestInfo("/api/score", "GET"), "student");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 表驱动，判断当前角色是否有权限访问该资源
        String role = roleMap.get(new RequestInfo(request.getRequestURI(), request.getMethod()));

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
