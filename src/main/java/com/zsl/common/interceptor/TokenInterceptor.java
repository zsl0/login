package com.zsl.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zsl
 * @Date 2021/12/30 15:20
 */
@Slf4j
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 对登录接口放行
        String requestURI = request.getRequestURI();
        log.info("requestURI = {}", requestURI);
        if (requestURI.startsWith("/login") || requestURI.startsWith("/regist") || requestURI.startsWith("/swagger")) {
            log.info("登录/注册/swagger放行");
            return true;
        }
        // 注掉token认证
        return true;

        /*// 认证
        String authorization = request.getHeader("Authorization");  // 获取请求头中token
        log.info("header 中 携带 authorization = {}", authorization);
        if (Objects.nonNull(authorization)) {
            // 判断token是否合法
            User authority = TokenUtils.getAuthority(authorization);
            ThreadLocalUtil.CURRENT_USER.set(authority);
            ThreadLocalUtil.LOGGING_INFO.set(ThreadLocalUtil.LOGGING_INFO.get().setLoggingAccessBy(authority.getId()));
            return true;
        } else {
            // 让用户去登陆
            throw new NotLoginException("未登录");
        }*/
    }
}
