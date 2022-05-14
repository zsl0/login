package com.zsl.service;

import com.zsl.entity.RadarDetail;
import com.zsl.entity.vo.RadarVO;

/**
 * @Author zsl
 * @Date 2022/1/6 15:34
 */
public interface RadarService {

    RadarDetail getRadarDetail(RadarVO radarVO);


}
