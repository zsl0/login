package com.zsl.common.exception.beas;

/**
 * @Author zsl
 * @Date 2021/12/31 10:18
 */
public class BaseException extends RuntimeException {
    private String msg;

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
