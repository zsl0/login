package com.zsl.controller;

import com.zsl.entity.User;
import com.zsl.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zsl
 * @Date 2021/12/29 14:07
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "查询所有用户")
    @GetMapping("getUsers")
    public List<User> getUsers() {
        return userService.selectUserAndRoleAll();
    }

    @ApiOperation("查询所有用户与绑定文章")
    @GetMapping("getUserAndArticle")
    public List<User> getUserAndArticle() {
        return userService.selectUserAndArticleAll();
    }
}
