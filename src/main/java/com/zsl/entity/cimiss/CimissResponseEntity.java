package com.zsl.entity.cimiss;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author zsl
 * @Date 2022/1/13 16:28
 */
public class CimissResponseEntity<T> implements Serializable {
    // 返回码
    private String returnCode;
    // 返回信息
    private String returnMessage;
    // 行数
    private Long rowCount;
    // 列数
    private Long colCount;
    // 请求参数
    private String requestParams;
    // 请求时间
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime requestTime;
    // 响应时间
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime responseTime;
    // 响应时长
    private Double takeTime;
    // 所有字段
    private String fieldNames;
    // 字段单位
    private String fieldUnits;
    // 数据
    @JsonProperty("DS")
    private List<T> DS;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Long getRowCount() {
        return rowCount;
    }

    public void setRowCount(Long rowCount) {
        this.rowCount = rowCount;
    }

    public Long getColCount() {
        return colCount;
    }

    public void setColCount(Long colCount) {
        this.colCount = colCount;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public LocalDateTime getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(LocalDateTime responseTime) {
        this.responseTime = responseTime;
    }

    public Double getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Double takeTime) {
        this.takeTime = takeTime;
    }

    public String getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(String fieldNames) {
        this.fieldNames = fieldNames;
    }

    public String getFieldUnits() {
        return fieldUnits;
    }

    public void setFieldUnits(String fieldUnits) {
        this.fieldUnits = fieldUnits;
    }

    public List<T> getDS() {
        return DS;
    }

    public void setDS(List<T> DS) {
        this.DS = DS;
    }

    @Override
    public String toString() {
        return "CimissResponse{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMessage='" + returnMessage + '\'' +
                ", rowCount=" + rowCount +
                ", colCount=" + colCount +
                ", requestParams='" + requestParams + '\'' +
                ", requestTime=" + requestTime +
                ", responseTime=" + responseTime +
                ", takeTime=" + takeTime +
                ", fieldNames='" + fieldNames + '\'' +
                ", fieldUnits='" + fieldUnits + '\'' +
                ", data=" + DS +
                '}';
    }
}
