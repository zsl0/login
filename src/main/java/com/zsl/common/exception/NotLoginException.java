package com.zsl.common.exception;

import com.zsl.common.exception.beas.BaseException;

/**
 * 未登录异常
 * @Author zsl
 * @Date 2021/12/30 15:30
 */
public class NotLoginException extends BaseException {
    public NotLoginException(String msg) {
        super(msg);
    }
}
