package com.zsl.service;

import com.zsl.entity.User;
import com.zsl.service.base.BaseService;

import java.util.List;

/**
 * @Author zsl
 * @Date 2021/12/29 11:49
 */
public interface UserService extends BaseService<User> {
     User selectByUsername(String username);

     User selectByEmail(String email);

     List<User> selectUserAndRoleAll();

     List<User> selectUserAndArticleAll();
}
