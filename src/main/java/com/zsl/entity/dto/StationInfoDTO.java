package com.zsl.entity.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author zsl
 * @Date 2022/1/13 16:43
 */
@JsonIgnoreProperties(ignoreUnknown = true) // 忽略未知类型
public class StationInfoDTO implements Serializable {
    // 区站号(数字)
    @JsonProperty("Station_Id_d")
    private java.lang.Long stationIdD;
    // 区站号(字符)
    @JsonProperty("Station_Id_C")
    private String stationIdC;
    // 站名
    @JsonProperty("Station_Name")
    private String stationName;
    // 经度
    @JsonProperty("Lon")
    private java.math.BigDecimal stationLon;
    // 纬度
    @JsonProperty("Lat")
    private java.math.BigDecimal stationLat;
    // 省份
    @JsonProperty("Province")
    private String stationProvince;
    // 地市
    @JsonProperty("City")
    private String stationCity;
    // 区县
    @JsonProperty("Cnty")
    private String stationCnty;
    // 乡镇
    @JsonProperty("Town")
    private String stationTown;
    // 时间
    @JsonProperty("Datetime")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.time.LocalDateTime stationTime;
    // 2分钟平均风向
    @JsonProperty("WIN_D_Avg_2mi")
    private java.lang.Long stationWinDAvg2Mi;
    // 2分钟平均风速
    @JsonProperty("WIN_S_Avg_2mi")
    private java.math.BigDecimal stationWinSAvg2Mi;

    public Long getStationIdD() {
        return stationIdD;
    }

    public void setStationIdD(Long stationIdD) {
        this.stationIdD = stationIdD;
    }

    public String getStationIdC() {
        return stationIdC;
    }

    public void setStationIdC(String stationIdC) {
        this.stationIdC = stationIdC;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public BigDecimal getStationLon() {
        return stationLon;
    }

    public void setStationLon(BigDecimal stationLon) {
        this.stationLon = stationLon;
    }

    public BigDecimal getStationLat() {
        return stationLat;
    }

    public void setStationLat(BigDecimal stationLat) {
        this.stationLat = stationLat;
    }

    public String getStationProvince() {
        return stationProvince;
    }

    public void setStationProvince(String stationProvince) {
        this.stationProvince = stationProvince;
    }

    public String getStationCity() {
        return stationCity;
    }

    public void setStationCity(String stationCity) {
        this.stationCity = stationCity;
    }

    public String getStationCnty() {
        return stationCnty;
    }

    public void setStationCnty(String stationCnty) {
        this.stationCnty = stationCnty;
    }

    public String getStationTown() {
        return stationTown;
    }

    public void setStationTown(String stationTown) {
        this.stationTown = stationTown;
    }

    public LocalDateTime getStationTime() {
        return stationTime;
    }

    public void setStationTime(LocalDateTime stationTime) {
        this.stationTime = stationTime;
    }

    public Long getStationWinDAvg2Mi() {
        return stationWinDAvg2Mi;
    }

    public void setStationWinDAvg2Mi(Long stationWinDAvg2Mi) {
        this.stationWinDAvg2Mi = stationWinDAvg2Mi;
    }

    public BigDecimal getStationWinSAvg2Mi() {
        return stationWinSAvg2Mi;
    }

    public void setStationWinSAvg2Mi(BigDecimal stationWinSAvg2Mi) {
        this.stationWinSAvg2Mi = stationWinSAvg2Mi;
    }

    @Override
    public String toString() {
        return "StationInfoDTO{" +
                "stationIdD=" + stationIdD +
                ", stationIdC='" + stationIdC + '\'' +
                ", stationName='" + stationName + '\'' +
                ", stationLon=" + stationLon +
                ", stationLat=" + stationLat +
                ", stationProvince='" + stationProvince + '\'' +
                ", stationCity='" + stationCity + '\'' +
                ", stationCnty='" + stationCnty + '\'' +
                ", stationTown='" + stationTown + '\'' +
                ", stationTime=" + stationTime +
                ", stationWinDAvg2Mi=" + stationWinDAvg2Mi +
                ", stationWinSAvg2Mi=" + stationWinSAvg2Mi +
                '}';
    }
}
