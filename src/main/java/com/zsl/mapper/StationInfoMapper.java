package com.zsl.mapper;

import com.zsl.entity.StationInfo;
import com.zsl.entity.vo.StationInfoVO;
import com.zsl.mapper.base.MyBaseMapper;

import java.util.List;

/**
 * @Author zsl
 * @Date 2022/1/13 14:53
 */
public interface StationInfoMapper extends MyBaseMapper<StationInfo> {
    List<StationInfo> selectByTimeAndStationId(StationInfoVO stationInfoVO, String datetime);

    List<StationInfo> selectByTimeAndStationRange(StationInfoVO stationInfoVO, String datetime);

    List<StationInfo> selectByTimeAndStationArea(StationInfoVO stationInfoVO, String datetime);

    int insertOne(StationInfo stationInfo, String datetime);

    int insertList(List<StationInfo> list, String datetime);
}
