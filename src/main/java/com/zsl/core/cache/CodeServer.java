package com.zsl.core.cache;

/**
 * @Author zsl
 * @Date 2021/12/31 10:00
 */
public interface CodeServer {
    void set(String key, String value);

    String get(String key);

    void del(String key);
}
