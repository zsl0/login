package com.zsl.common.scheduling;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zsl.entity.StationInfo;
import com.zsl.entity.cimiss.CimissResponseEntity;
import com.zsl.entity.dto.StationInfoDTO;
import com.zsl.entity.dto.handler.StationInfoDTOHandler;
import com.zsl.service.StationInfoService;
import com.zsl.util.JsonUtils;
import com.zsl.util.SqlUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author zsl
 * @Date 2022/1/15 10:45
 */
@SpringBootTest
class GlobalSchedulingTest {
    @Autowired
    SqlUtils sqlUtils;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    StationInfoService stationInfoService;

    @Value("${cimiss.userId}")
    String userId;

    @Value("${cimiss.pwd}")
    String pwd;

    @Value("${cimiss.url}")
    String cimissUrl;


    @Test
    public void test01() {
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
//                "&staIds=54512";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String jsonStr = responseEntity.getBody();
        // 处理响应体
        CimissResponseEntity<StationInfoDTO> cimissResponseEntity = JsonUtils.str2Obj(jsonStr, new TypeReference<CimissResponseEntity<StationInfoDTO>>() {});
        List<StationInfoDTO> ds = cimissResponseEntity.getDS();
        // DTOHandler
        List<StationInfo> stationInfos = StationInfoDTOHandler.dtoToBean(ds);
        // 批量插入
        stationInfoService.insertBatch(stationInfos);
    }

}