package com.zsl.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zsl.entity.cimiss.CimissResponseEntity;
import com.zsl.entity.dto.StationInfoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

/**
 * @Author zsl
 * @Date 2022/1/13 15:06
 */
@SpringBootTest
class SqlUtilsTest {

    @Autowired
    SqlUtils sqlUtils;

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void getConn() {
//        sqlUtils.getConn();
        String url = "http://10.181.89.55/cimiss-web/api?userId=BEXN_FWZX_qxfwzx&pwd=qxfwzx&interfaceId=getSurfEleByTimeRangeAndStaID&dataCode=SURF_CHN_OTHER_MIN&elements=Station_Name,Station_Id_C,Station_Id_d,WIN_S_Avg_2mi,WIN_D_Avg_2mi,Datetime,Province,City,Cnty,Town,Lon,Lat,Year,Mon,Day,Hour,Min&timeRange=(20220113000000,20220114000000]&orderby=Datetime:DESC&dataFormat=json&staIds=54512";
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String body = entity.getBody();
        System.out.println(body);
        CimissResponseEntity<StationInfoDTO> stationInfoVOCimissResponseEntity = JsonUtils.str2Obj(body, new TypeReference<CimissResponseEntity<StationInfoDTO>>() {});
        System.out.println(stationInfoVOCimissResponseEntity);
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().plusDays(19L).toString());
    }
}