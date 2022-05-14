package com.zsl.common.http;

/**
 * @Author zsl
 * @Date 2021/12/29 11:40
 */
public class ResponseResult<T> {
    private int code;
    private String msg;
    private T data;

    private ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 提供给异常处理自定义返回类型
     */
    public static <T> ResponseResult<T> newInstance(int code, String msg, T data) {
        return new ResponseResult<T>(code, msg, data);
    }

    public static <T> ResponseResult<T> newInstance(ResultStatus resultStatus, T data) {
        return new ResponseResult<T>(resultStatus.getCode(), resultStatus.getMsg(), data);
    }

    /**
     * 操作成功，无返回数据
     */
    public static <T> ResponseResult<T> success() {
        return newInstance(ResultStatus.OK, null);
    }

    /**
     * 操作成功，携带返回数据
     */
    public static <T> ResponseResult<T> success(T data) {
        return newInstance(ResultStatus.OK, data);
    }

    /**
     * 自定义成功返回消息
     */
    public static <T> ResponseResult<T> success(ResultStatus resultStatus, T data) {
        return newInstance(resultStatus, data);
    }

    /**
     * 操作失败，无返回数据
     */
    public static <T> ResponseResult<T> error() {
        return newInstance(ResultStatus.ERROR, null);
    }

    /**
     * 操作失败，携带返回数据
     */
    public static <T> ResponseResult<T> error(T data) {
        return newInstance(ResultStatus.ERROR, data);
    }

    /**
     * 自定义失败返回消息
     */
    public static <T> ResponseResult<T> error(ResultStatus resultStatus, T data) {
        return newInstance(resultStatus, data);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
