package com.zsl.common.interceptor;

import com.zsl.entity.Logging;
import com.zsl.service.LoggingService;
import com.zsl.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zsl
 * @Date 2022/1/4 9:10
 */
@Slf4j
@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    LoggingService loggingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 日志处理
        ThreadLocalUtil.LOGGING_INFO.set(new Logging().build(request));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 写入日志信息
        Logging logging = ThreadLocalUtil.LOGGING_INFO.get();
        log.info("logging = {}", logging);
        loggingService.insert(logging);
    }
}
