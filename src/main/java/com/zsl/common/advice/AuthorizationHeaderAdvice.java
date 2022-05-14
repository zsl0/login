package com.zsl.common.advice;

import com.zsl.common.exception.APIException;
import com.zsl.entity.User;
import com.zsl.util.ServletUtils;
import com.zsl.util.ThreadLocalUtil;
import com.zsl.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 设置 响应头 携带 token
 * @Author zsl
 * @Date 2021/12/30 17:44
 */
@RestControllerAdvice
@Aspect
@Slf4j
public class AuthorizationHeaderAdvice {

    @Around("execution(* com.zsl.controller.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            Object proceed = joinPoint.proceed();
            log.info("AuthorizationHeader exec...");
            // 设置 token 响应头
            User user = ThreadLocalUtil.CURRENT_USER.get();
            if (user != null) {
                String value = TokenUtil.createToken(user);
                ServletUtils.getResponse().addHeader("Authorization", value);
            }
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new APIException("AuthorizationHeader AOP 失败");
        }
    }
}
