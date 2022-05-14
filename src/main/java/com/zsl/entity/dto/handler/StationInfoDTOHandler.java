package com.zsl.entity.dto.handler;

import com.zsl.entity.StationInfo;
import com.zsl.entity.dto.StationInfoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author zsl
 * @Date 2022/1/14 9:16
 */
public class StationInfoDTOHandler {

    public static StationInfo dtoToBean(StationInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        StationInfo stationInfo = new StationInfo();
        stationInfo.setStationName(dto.getStationName());
        stationInfo.setStationIdD(dto.getStationIdD());
        stationInfo.setStationIdC(dto.getStationIdC());
        stationInfo.setStationLat(dto.getStationLat());
        stationInfo.setStationLon(dto.getStationLon());
        stationInfo.setStationProvince(dto.getStationProvince());
        stationInfo.setStationCity(dto.getStationCity());
        stationInfo.setStationCnty(dto.getStationCnty());
        stationInfo.setStationTown(dto.getStationTown());
        stationInfo.setStationTime(dto.getStationTime());
        stationInfo.setStationWinDAvg2Mi(dto.getStationWinDAvg2Mi());
        stationInfo.setStationWinSAvg2Mi(dto.getStationWinSAvg2Mi());
        return stationInfo;
    }

    public static StationInfoDTO beanToDTO(StationInfo stationInfo) {
        if (stationInfo == null) {
            return null;
        }
        StationInfoDTO stationInfoDTO = new StationInfoDTO();
        stationInfoDTO.setStationName(stationInfo.getStationName());
        stationInfoDTO.setStationIdD(stationInfo.getStationIdD());
        stationInfoDTO.setStationIdC(stationInfo.getStationIdC());
        stationInfoDTO.setStationLat(stationInfo.getStationLat());
        stationInfoDTO.setStationLon(stationInfo.getStationLon());
        stationInfoDTO.setStationProvince(stationInfo.getStationProvince());
        stationInfoDTO.setStationCity(stationInfo.getStationCity());
        stationInfoDTO.setStationCnty(stationInfo.getStationCnty());
        stationInfoDTO.setStationTown(stationInfo.getStationTown());
        stationInfoDTO.setStationTime(stationInfo.getStationTime());
        stationInfoDTO.setStationWinDAvg2Mi(stationInfo.getStationWinDAvg2Mi());
        stationInfoDTO.setStationWinSAvg2Mi(stationInfo.getStationWinSAvg2Mi());
        return stationInfoDTO;
    }

    public static List<StationInfo> dtoToBean(List<StationInfoDTO> list) {
        List<StationInfo> stationInfos = new ArrayList<>(list.size());
        list.forEach(new Consumer<StationInfoDTO>() {
            @Override
            public void accept(StationInfoDTO dto) {
                stationInfos.add(dtoToBean(dto));
            }
        });
        return stationInfos;
    }

    public static List<StationInfoDTO> beanToDTO(List<StationInfo> list) {
        List<StationInfoDTO> stationInfoDTOS = new ArrayList<>(list.size());
        list.forEach(new Consumer<StationInfo>() {
            @Override
            public void accept(StationInfo stationInfo) {
                stationInfoDTOS.add(beanToDTO(stationInfo));
            }
        });
        return stationInfoDTOS;
    }
}
