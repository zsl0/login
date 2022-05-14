package com.zsl.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zsl.entity.base.BaseBean;

import java.io.Serializable;

public class StationInfo extends BaseBean implements Serializable {
  // 区站号(数字)
  private java.lang.Long stationIdD;
  // 区站号(字符)
  private String stationIdC;
  // 站名
  private String stationName;
  // 经度
  private java.math.BigDecimal stationLon;
  // 纬度
  private java.math.BigDecimal stationLat;
  // 省份
  private String stationProvince;
  // 地市
  private String stationCity;
  // 区县
  private String stationCnty;
  // 乡镇
  private String stationTown;
  // 时间
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
  private java.time.LocalDateTime stationTime;
  // 2分钟平均风向
  private java.lang.Long stationWinDAvg2Mi;
  // 2分钟平均风速
  private java.math.BigDecimal stationWinSAvg2Mi;

  public java.lang.Long getStationIdD() {
    return stationIdD;
  }

  public void setStationIdD(java.lang.Long stationIdD) {
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


  public java.math.BigDecimal getStationLon() {
    return stationLon;
  }

  public void setStationLon(java.math.BigDecimal stationLon) {
    this.stationLon = stationLon;
  }


  public java.math.BigDecimal getStationLat() {
    return stationLat;
  }

  public void setStationLat(java.math.BigDecimal stationLat) {
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


  public java.time.LocalDateTime getStationTime() {
    return stationTime;
  }

  public void setStationTime(java.time.LocalDateTime stationTime) {
    this.stationTime = stationTime;
  }


  public java.lang.Long getStationWinDAvg2Mi() {
    return stationWinDAvg2Mi;
  }

  public void setStationWinDAvg2Mi(java.lang.Long stationWinDAvg2Mi) {
    this.stationWinDAvg2Mi = stationWinDAvg2Mi;
  }


  public java.math.BigDecimal getStationWinSAvg2Mi() {
    return stationWinSAvg2Mi;
  }

  public void setStationWinSAvg2Mi(java.math.BigDecimal stationWinSAvg2Mi) {
    this.stationWinSAvg2Mi = stationWinSAvg2Mi;
  }

  @Override
  public String toString() {
    return "StationInfo{" +
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
