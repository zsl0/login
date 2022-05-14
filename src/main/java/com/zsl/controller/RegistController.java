package com.zsl.controller;

import com.zsl.common.http.ResponseResult;
import com.zsl.common.http.ResultStatus;
import com.zsl.entity.User;
import com.zsl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 注册
 * @Author zsl
 * @Date 2021/12/31 11:36
 */
@RestController
@RequestMapping("regist")
public class RegistController {

    @Autowired
    UserService userService;

    @PostMapping("user")
    public ResponseResult<Void> registUser(@RequestBody User user) {
        if (userService.insert(user) == 1) {
            return ResponseResult.success(ResultStatus.REGIST_SUCCESS, null);
        }
        return ResponseResult.success(ResultStatus.REGIST_FAILED, null);
    }
}
