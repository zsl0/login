package com.zsl.core.cache.impl;

import com.zsl.core.cache.CacheServer;
import com.zsl.core.cache.CodeServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author zsl
 * @Date 2021/12/31 10:01
 */
@Component
public class CodeServerImpl implements CodeServer {
    @Autowired
    CacheServer cacheServer;

    @Value("${spring.mail.codeExpire:3}")
    long codeExpire;

    private static final String PRE_EMAIL_CODE = "emailCode:";

    @Override
    public void set(String key, String value) {
        cacheServer.set(PRE_EMAIL_CODE + key, value, codeExpire * 60);
    }

    @Override
    public String get(String key) {
        return cacheServer.get(PRE_EMAIL_CODE + key);
    }

    @Override
    public void del(String key) {
        cacheServer.delete(PRE_EMAIL_CODE + key);
    }
}
