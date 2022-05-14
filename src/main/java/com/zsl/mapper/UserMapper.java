package com.zsl.mapper;

import com.zsl.entity.User;
import com.zsl.mapper.base.MyBaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author zsl
 * @Date 2021/12/29 11:51
 */
public interface UserMapper extends MyBaseMapper<User> {
    @Select("select * from t_user where user_username = #{username}")
    User selectByUsername(String username);

    @Select("select * from t_user where user_email = #{email}")
    User selectByEmail(String email);

    List<User> selectUserAndRoleAll();

    List<User> selectUserAndArticleAll();
}
