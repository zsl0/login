package com.zsl.controller;

import com.zsl.entity.StationInfo;
import com.zsl.entity.vo.StationInfoVO;
import com.zsl.service.StationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zsl
 * @Date 2022/1/14 15:48
 */
@RestController
@RequestMapping("stationInfo")
public class StationInfoController {
    @Autowired
    StationInfoService stationInfoService;

    @GetMapping("byTimeAndStationId")
    public List<StationInfo> getStationDateByTimeAndStationId(StationInfoVO stationInfoVO) {
        return stationInfoService.selectByTimeAndStationId(stationInfoVO);
    }

    @GetMapping("byTimeAndStationRange")
    public List<StationInfo> getStationDateByTimeAndStationRange(StationInfoVO stationInfoVO) {
        return stationInfoService.selectByTimeAndStationRange(stationInfoVO);
    }

    @GetMapping("byTimeAndStationArea")
    public List<StationInfo> getStationDateByTimeAndStationArea(StationInfoVO stationInfoVO) {
        return stationInfoService.selectByTimeAndStationArea(stationInfoVO);
    }
}
