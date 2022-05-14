package com.zsl.core.cache.impl;

import com.zsl.core.cache.CacheServer;
import com.zsl.core.cache.TokenServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author zsl
 * @Date 2021/12/30 11:59
 */
@Component
public class TokenServerImpl implements TokenServer {
    @Autowired
    CacheServer cacheServer;

    @Value("${token.timeout:1800}")
    long timeout;

    private final static String PRE_TOKEN = "loginUser:";

    @Override
    public void set(String key, String value) {
        cacheServer.set(PRE_TOKEN  + key, value, timeout);
    }

    @Override
    public String get(String key) {
        return cacheServer.get(PRE_TOKEN  + key);
    }

    @Override
    public void delete(String key) {
        cacheServer.delete(PRE_TOKEN  + key);
    }

    @Override
    public void expire(String key, long timeout) {
        cacheServer.expire(PRE_TOKEN  + key, timeout);
    }

    @Override
    public boolean contains(String key) {
        return cacheServer.contains(PRE_TOKEN  + key);
    }
}
