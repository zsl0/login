package com.zsl.service;

import com.zsl.entity.User;

/**
 * @Author zsl
 * @Date 2021/12/31 16:55
 */
public interface RegistService {
    int regist(User user, long roleId);
}
