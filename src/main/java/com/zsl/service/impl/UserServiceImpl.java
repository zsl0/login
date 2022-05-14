package com.zsl.service.impl;

import com.zsl.common.exception.UserExistException;
import com.zsl.entity.User;
import com.zsl.mapper.UserMapper;
import com.zsl.service.UserService;
import com.zsl.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zsl
 * @Date 2021/12/29 11:50
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService {

    @Override
    public int insert(User user) {
        // todo 判断用户名是否存在
        if (baseMapper.selectByUsername(user.getUserUsername()) != null) {
            throw new UserExistException("用户名已存在");
        }
        return super.insert(user);
    }

    @Override
    public User selectByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

    @Override
    public User selectByEmail(String email) {
        return baseMapper.selectByEmail(email);
    }

    @Override
    public List<User> selectUserAndRoleAll() {
        return baseMapper.selectUserAndRoleAll();
    }

    @Override
    public List<User> selectUserAndArticleAll() {
        return baseMapper.selectUserAndArticleAll();
    }
}
