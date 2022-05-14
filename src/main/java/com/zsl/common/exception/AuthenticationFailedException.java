package com.zsl.common.exception;

import com.zsl.common.exception.beas.BaseException;

/**
 * 没有认证异常
 * @Author zsl
 * @Date 2021/12/30 14:56
 */
public class AuthenticationFailedException extends BaseException {
    public AuthenticationFailedException(String msg) {
        super(msg);
    }
}
