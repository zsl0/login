package com.zsl.entity.vo;

import java.time.LocalDateTime;

/**
 * @Author zsl
 * @Date 2022/1/6 15:33
 */
public class RadarVO {
    private String lon;
    private String lat;
    private String level;
    private String time;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "RadarVO{" +
                "lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                ", level='" + level + '\'' +
                ", time=" + time +
                '}';
    }
}
