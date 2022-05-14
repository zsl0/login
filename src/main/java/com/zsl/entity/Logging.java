package com.zsl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zsl.util.ThreadLocalUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author zsl
 * @Date 2022/1/4 9:30
 */
@TableName("t_logging")
public class Logging implements Serializable {
    private Long id;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime loggingAccessTime;
    private Long loggingAccessBy;
    private String loggingUrl;
    private String loggingMethod;
    private String loggingInfo;

    public Long getId() {
        return id;
    }

    public Logging setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getLoggingAccessTime() {
        return loggingAccessTime;
    }

    public Logging setLoggingAccessTime(LocalDateTime loggingAccessTime) {
        this.loggingAccessTime = loggingAccessTime;
        return this;
    }

    public Long getLoggingAccessBy() {
        return loggingAccessBy;
    }

    public Logging setLoggingAccessBy(Long loggingAccessBy) {
        this.loggingAccessBy = loggingAccessBy;
        return this;
    }

    public String getLoggingUrl() {
        return loggingUrl;
    }

    public Logging setLoggingUrl(String loggingUrl) {
        this.loggingUrl = loggingUrl;
        return this;
    }

    public String getLoggingMethod() {
        return loggingMethod;
    }

    public Logging setLoggingMethod(String loggingMethod) {
        this.loggingMethod = loggingMethod;
        return this;
    }

    public String getLoggingInfo() {
        return loggingInfo;
    }

    public Logging setLoggingInfo(String loggingInfo) {
        this.loggingInfo = loggingInfo;
        return this;
    }

    @Override
    public String toString() {
        return "Logging{" +
                "id=" + id +
                ", loggingAccessTime=" + loggingAccessTime +
                ", loggingAccessBy=" + loggingAccessBy +
                ", loggingUrl='" + loggingUrl + '\'' +
                ", loggingMethod='" + loggingMethod + '\'' +
                ", loggingInfo='" + loggingInfo + '\'' +
                '}';
    }

    public Logging build(HttpServletRequest request) {
        User user = ThreadLocalUtil.CURRENT_USER.get();
        Long id = (user == null) ? 0L : user.getId();
        this.loggingAccessBy = id;
        this.loggingAccessTime = LocalDateTime.now();
        this.loggingUrl = request.getRequestURL().toString();
        this.loggingMethod = request.getMethod();
        this.loggingInfo = "正常执行";
        return this;
    }
}
