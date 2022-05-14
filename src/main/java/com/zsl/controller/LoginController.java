package com.zsl.controller;

import com.zsl.common.exception.UserNotFoundException;
import com.zsl.common.http.ResponseResult;
import com.zsl.common.http.ResultStatus;
import com.zsl.core.cache.CodeServer;
import com.zsl.entity.User;
import com.zsl.entity.vo.EmailVO;
import com.zsl.entity.vo.UserVO;
import com.zsl.service.UserService;
import com.zsl.util.MailService;
import com.zsl.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zsl
 * @Date 2021/12/29 9:45
 */
@RestController
@RequestMapping("login")
@Api(tags = "登录")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @Autowired
    CodeServer codeServer;

    @ApiOperation("用户名/密码登录")
    @ApiImplicitParam(name = "UserVO", value = "接收参数 username(用户名),password(密码)给 UserVO 对象进行认证")
    @PostMapping
    public ResponseResult<Void> usernamePassword(@RequestBody UserVO user) {
        // 判断用户是否正确
        User userReal = userService.selectByUsername(user.getUsername());
        if (userReal == null) {
            throw new UserNotFoundException("用户不存在");
        }
        if (userReal.getUserPassword().equals(user.getPassword())) {
            // 登录成功
            ThreadLocalUtil.CURRENT_USER.set(userReal);
        }
        return ResponseResult.success(ResultStatus.LOGIN_SUCCESS, null);
    }

    @ApiOperation("邮箱登录")
    @PostMapping("email")
    public ResponseResult<Void> email(@RequestBody EmailVO email) {
        // 判断验证码是否正确
        if (email.getCode().equals(codeServer.get(email.getEmail()))) {
            // 登录成功
            User userReal = userService.selectByEmail(email.getEmail());
            ThreadLocalUtil.CURRENT_USER.set(userReal);
            return ResponseResult.success(ResultStatus.LOGIN_SUCCESS, null);
        }
        return ResponseResult.error(ResultStatus.SEND_EMAIL_CODE_FAILED, null);
    }

    /**
     * 获取邮箱验证码 按钮
     */
    @ApiOperation("获取邮箱验证码")
    @PostMapping("email/getCode")
    public ResponseResult<Void> getEmailCode(@RequestBody EmailVO email) {
        // 查看是否为合法用户邮箱
        User user = userService.selectByEmail(email.getEmail());
        if (user == null) {
            // 用户不存在
            return ResponseResult.error(ResultStatus.SEND_EMAIL_NOT_FOUND, null);
        }
        // 发送邮箱验证码
        mailService.sendEmailCode(user, 6);
        return ResponseResult.success(ResultStatus.SEND_EMAIL_SUCCESS, null);
    }
}
