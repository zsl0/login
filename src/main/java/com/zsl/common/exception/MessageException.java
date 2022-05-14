package com.zsl.common.exception;

import com.zsl.common.exception.beas.BaseException;

/**
 * @Author zsl
 * @Date 2022/1/7 9:06
 */
public class MessageException extends BaseException {
    public MessageException(String msg) {
        super(msg);
    }
}
