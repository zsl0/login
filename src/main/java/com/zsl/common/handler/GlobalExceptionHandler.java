package com.zsl.common.handler;

import com.zsl.common.http.ResponseResult;
import com.zsl.common.http.ResultStatus;
import com.zsl.common.exception.APIException;
import com.zsl.service.LoggingService;
import com.zsl.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author zsl
 * @Date 2021/12/29 15:33
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    LoggingService loggingService;

    @ExceptionHandler(APIException.class)
    public ResponseResult<Object> apiException(APIException e) {
        ThreadLocalUtil.LOGGING_INFO.get().setLoggingInfo(e.getMsg());
        return ResponseResult.newInstance(e.getCode(), e.getMsg(), null);
    }

    @ExceptionHandler({Throwable.class})
    public ResponseResult<Object> throwable(Throwable t) {
        if (t.getMessage() != null && t.getMessage().getBytes().length > 255) {
            ThreadLocalUtil.LOGGING_INFO.get().setLoggingInfo(t.getMessage().substring(0, 255));
        } else {
            ThreadLocalUtil.LOGGING_INFO.get().setLoggingInfo(t.getMessage());
        }
        return ResponseResult.newInstance(ResultStatus.ERROR, t.getMessage());
    }
}
