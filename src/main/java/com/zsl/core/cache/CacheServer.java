package com.zsl.core.cache;

import java.util.concurrent.TimeUnit;

/**
 * @Author zsl
 * @Date 2021/12/29 11:08
 */
public interface CacheServer {
    /**
     * 设置字符串缓存
     */
    void set(String key, String value);

    /**
     * 设置带过期时间的字符串缓存
     */
    void set(String key, String value, long timeout);

    /**
     * 获取字符串缓存
     */
    String get(String key);

    /**
     * 设置缓存
     */
    void setObject(Object key, Object value);

    /**
     * 设置带过期时间的缓存
     */
    void setObject(Object key, Object value, long timeout);

    /**
     * 获取存
     */
    Object getObject(Object key);

    /**
     * 删除缓存
     */
    void delete(String key);

    /**
     * 设置缓存过期时间
     */
    void expire(String key, long timeout);

    /**
     * 查看缓存是否存在
     */
    boolean contains(String key);
}
