package com.zsl.common.http;

/**
 * @Author zsl
 * @Date 2021/12/29 11:38
 */
public enum ResultStatus {
    OK(20000, "操作成功"),
    ERROR(40000, "操作失败"),

    // 邮箱登录
    SEND_EMAIL_SUCCESS(20100, "验证码发送成功"),
    SEND_EMAIL_FAILED(40100, "验证码发送失败"),
    SEND_EMAIL_NOT_FOUND(40110, "邮箱不存在"),
    SEND_EMAIL_CODE_FAILED(40120, "邮箱验证码错误"),

    // 账号密码登录
    LOGIN_SUCCESS(20200, "登陆成功"),
    LOGIN_FAILED(40200, "登陆失败"),

    // 注册
    REGIST_SUCCESS(20300, "用户注册成功"),
    REGIST_FAILED(40300, "用户注册失败"),
    ;
    private int code;
    private String msg;

    ResultStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
