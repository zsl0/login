package com.zsl.common.exception;

import com.zsl.common.exception.beas.BaseException;

/**
 * @Author zsl
 * @Date 2021/12/31 10:20
 */
public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
