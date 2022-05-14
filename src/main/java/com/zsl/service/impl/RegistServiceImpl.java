package com.zsl.service.impl;

import com.zsl.entity.User;
import com.zsl.mapper.UserMapper;
import com.zsl.mapper.UserRoleMapper;
import com.zsl.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zsl
 * @Date 2021/12/31 16:56
 */
@Service
public class RegistServiceImpl implements RegistService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Transactional
    @Override
    public int regist(User user, long roleId) {
//        userMapper.insert()
        return 0;
    }
}
