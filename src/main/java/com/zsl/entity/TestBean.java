package com.zsl.entity;

import java.time.LocalDateTime;

/**
 * @Author zsl
 * @Date 2022/1/7 10:58
 */
public class TestBean {
    private String prename;
    private String sufname;
    private LocalDateTime time;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getSufname() {
        return sufname;
    }

    public void setSufname(String sufname) {
        this.sufname = sufname;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "prename='" + prename + '\'' +
                ", sufname='" + sufname + '\'' +
                ", time=" + time +
                '}';
    }
}
