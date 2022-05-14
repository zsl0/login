package com.zsl.common.filter;

import com.zsl.util.ThreadLocalUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zsl
 * @Date 2021/12/30 14:51
 */
@WebFilter
public class GlobalDestroyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
        // 线程结束 执行 清理工作
        ThreadLocalUtil.CURRENT_USER.remove();
        logger.info("清理 TokenUtils.CURRENT_USER (ThreadLocal) 完成");
    }
}
