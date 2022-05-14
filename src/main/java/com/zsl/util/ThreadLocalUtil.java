package com.zsl.util;

import com.zsl.entity.Logging;
import com.zsl.entity.User;

/**
 * @Author zsl
 * @Date 2022/1/4 10:09
 */
public class ThreadLocalUtil {
    // 当前用户信息
    public static ThreadLocal<User> CURRENT_USER = new ThreadLocal<>();
    // 日志信息
    public static ThreadLocal<Logging> LOGGING_INFO = new ThreadLocal<>();
}
