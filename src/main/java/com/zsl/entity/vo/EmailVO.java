package com.zsl.entity.vo;

/**
 * @Author zsl
 * @Date 2021/12/30 17:57
 */
public class EmailVO {
    private String email;
    private String code;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "EmailVO{" +
                "email='" + email + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
