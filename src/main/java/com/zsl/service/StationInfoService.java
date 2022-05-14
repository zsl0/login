package com.zsl.service;

import com.zsl.entity.StationInfo;
import com.zsl.entity.vo.StationInfoVO;
import com.zsl.service.base.BaseService;

import java.util.List;

/**
 * @Author zsl
 * @Date 2022/1/13 17:54
 */
public interface StationInfoService extends BaseService<StationInfo> {
    /**
     * 批量插入
     */
    void insertBatch(List<StationInfo> list);

    /**
     * 按时间、站点查询
     */
    List<StationInfo> selectByTimeAndStationId(StationInfoVO stationInfoVO);

    /**
     * 按时间、经纬度范围查询
     */
    List<StationInfo> selectByTimeAndStationRange(StationInfoVO stationInfoVO);

    /**
     * 按时间区域查询
     */
    List<StationInfo> selectByTimeAndStationArea(StationInfoVO stationInfoVO);
}
