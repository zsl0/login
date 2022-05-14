package com.zsl.core.cache;

/**
 * @Author zsl
 * @Date 2021/12/30 11:53
 */
public interface TokenServer {
    /**
     * 保存token
     */
    void set(String key, String value);

    /**
     * 获取token
     */
    String get(String key);

    /**
     * 删除token
     */
    void delete(String key);

    /**
     * 设置过期时间
     */
    void expire(String key, long timeout);

    /**
     * 判断是否存在
     */
    boolean contains(String key);

}
