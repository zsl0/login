package com.zsl.common.exception;

import com.zsl.common.exception.beas.BaseException;

/**
 * @Author zsl
 * @Date 2021/12/31 17:59
 */
public class UserExistException extends BaseException {
    public UserExistException(String msg) {
        super(msg);
    }
}
