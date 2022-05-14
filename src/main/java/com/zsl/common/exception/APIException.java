package com.zsl.common.exception;

/**
 * @Author zsl
 * @Date 2021/12/29 15:28
 */
public class APIException extends RuntimeException {
    private int code;
    private String msg;

    public APIException() {
        this(10001, "接口错误");
    }

    public APIException(String msg) {
        this(10001, msg);
    }

    public APIException(int code, String msg) {
        super(msg);
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
