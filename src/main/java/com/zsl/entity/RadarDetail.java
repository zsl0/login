package com.zsl.entity;

import java.io.Serializable;

/**
 * @Author zsl
 * @Date 2022/1/6 15:35
 */
public class RadarDetail implements Serializable {
    private Double lon;
    private Double lat;
    private Double level;
    private Float r;
    private Float cr;
    private Float v;
    private String time;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public Float getR() {
        return r;
    }

    public void setR(Float r) {
        this.r = r;
    }

    public Float getCr() {
        return cr;
    }

    public void setCr(Float cr) {
        this.cr = cr;
    }

    public Float getV() {
        return v;
    }

    public void setV(Float v) {
        this.v = v;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "RadarDetail{" +
                "lon=" + lon +
                ", lat=" + lat +
                ", level=" + level +
                ", r=" + r +
                ", cr=" + cr +
                ", v=" + v +
                ", time=" + time +
                '}';
    }

//----------------------------------build----------------------------------------------

    public static RadarDetail build() {
        return new RadarDetail();
    }

    public static class RadarDetailBuild {
        private RadarDetail radarDetail = new RadarDetail();

        public RadarDetail build() {
            RadarDetail ref = radarDetail;
            radarDetail = null;
            return ref;
        }

        public RadarDetail.RadarDetailBuild lon(Double lon) {
            radarDetail.setLon(lon);
            return this;
        }

        public RadarDetail.RadarDetailBuild lat(Double lat) {
            radarDetail.setLat(lat);
            return this;
        }

        public RadarDetail.RadarDetailBuild level(Double level) {
            radarDetail.setLevel(level);
            return this;
        }

        public RadarDetail.RadarDetailBuild r(Float r) {
            radarDetail.setR(r);
            return this;
        }

        public RadarDetail.RadarDetailBuild cr(Float cr) {
            radarDetail.setCr(cr);
            return this;
        }

        public RadarDetail.RadarDetailBuild v(Float v) {
            radarDetail.setV(v);
            return this;
        }

        public RadarDetail.RadarDetailBuild time(String time) {
            radarDetail.setTime(time);
            return this;
        }

        public static RadarDetail.RadarDetailBuild start() {
            return new RadarDetail.RadarDetailBuild();
        }
    }
}
