package com.zsl.common.scheduling;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zsl.entity.StationInfo;
import com.zsl.entity.cimiss.CimissResponseEntity;
import com.zsl.entity.dto.StationInfoDTO;
import com.zsl.entity.dto.handler.StationInfoDTOHandler;
import com.zsl.service.StationInfoService;
import com.zsl.util.JsonUtils;
import com.zsl.util.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author zsl
 * @Date 2022/1/12 14:36
 */
@Component
@Slf4j
public class GlobalScheduling {
    @Autowired
    SqlUtils sqlUtils;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    StationInfoService stationInfoService;

    /**
     * 定时创建 cimiss 数据表，每天的 02：30 创建第二天的数据表
     */
    @Scheduled(cron = "0 30 2 1 * *")
    public void createStationTable() {
        String now = LocalDate.now().plusDays(1L).toString().replace("-", ""); // 明天的时间
        String sql = "create table t_station_info_" + now + "(\n" +
                "            id bigint primary key auto_increment comment \"主键\",\n" +
                "\n" +
                "            station_id_d bigint comment \"区站号(数字)\",\n" +
                "            station_id_c varchar(16) comment \"区站号(字符)\",\n" +
                "            station_name varchar(64) comment \"站名\",\n" +
                "            station_lon decimal(8, 2) comment \"经度\",\n" +
                "            station_lat decimal(8, 2) comment \"纬度\",\n" +
                "            station_province varchar(32) comment \"省份\",\n" +
                "            station_city varchar(32) comment \"地市\",\n" +
                "            station_cnty varchar(32) comment \"区县\",\n" +
                "            station_town varchar(32) comment \"乡镇\",\n" +
                "            station_time datetime comment \"时间\",\n" +
                "            station_win_d_avg_2mi int(11) comment \"2分钟平均风向\",\n" +
                "            station_win_s_avg_2mi decimal(8, 2) comment \"2分钟平均风速\",\n" +
                "\n" +
                "            create_time datetime default CURRENT_TIMESTAMP comment \"创建时间\",\n" +
                "            create_by bigint(20) comment \"创建者\",\n" +
                "            update_time datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment \"更新时间\",\n" +
                "            update_by bigint(20) comment \"更新者\"\n" +
                "    );";
        sqlUtils.createTable(sql);
        log.info("创建t_station_info_{} 完成， datetime = {}", now, LocalDateTime.now().toString());
    }

    @Value("${cimiss.userId}")
    String userId;

    @Value("${cimiss.pwd}")
    String pwd;

    @Value("${cimiss.url}")
    String cimissUrl;


    /**
     * 定时获取 15 分钟前的数据
     */
    @Scheduled(cron = "10 * * * * *")
    public void timerGetWindBy2Min() {
        // 获取查询时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm00");
        String datetime = LocalDateTime.now().minusHours(8L).minusMinutes(15L).format(dateTimeFormatter);
        // 远程接口调用
        String url = cimissUrl + "?" +
                "userId=" + userId +
                "&pwd=" + pwd +
                "&interfaceId=getSurfEleByTime" +
                "&dataCode=SURF_CHN_OTHER_MIN" +
                "&elements=Station_Name,Station_Id_C,Station_Id_d,WIN_S_Avg_2mi,WIN_D_Avg_2mi,Datetime,Province,City,Cnty,Town,Lon,Lat,Year,Mon,Day,Hour,Min" +
                "&times=" + datetime +
                "&orderby=Datetime:DESC" +
                "&dataFormat=json";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String jsonStr = responseEntity.getBody();
        // 处理响应体
        CimissResponseEntity<StationInfoDTO> cimissResponseEntity = JsonUtils.str2Obj(jsonStr, new TypeReference<CimissResponseEntity<StationInfoDTO>>() {});
        List<StationInfoDTO> ds = cimissResponseEntity.getDS();
        // DTOHandler
        List<StationInfo> stationInfos = StationInfoDTOHandler.dtoToBean(ds);
        // 世界时转北京时
        stationInfos.forEach(new Consumer<StationInfo>() {
            @Override
            public void accept(StationInfo stationInfo) {
                stationInfo.setStationTime(stationInfo.getStationTime().plusHours(8L));
            }
        });
        // 批量插入
        stationInfoService.insertBatch(stationInfos);
    }
}
