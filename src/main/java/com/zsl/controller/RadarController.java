package com.zsl.controller;

import com.zsl.entity.RadarDetail;
import com.zsl.entity.TestBean;
import com.zsl.entity.vo.RadarVO;
import com.zsl.service.RadarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zsl
 * @Date 2022/1/6 15:31
 */
@RestController
@RequestMapping("radar")
public class RadarController {
    @Autowired
    RadarService radarService;

    @PostMapping
    public RadarDetail getRadarData(@RequestBody RadarVO radarVO) {
        return radarService.getRadarDetail(radarVO);
    }

    @PostMapping("test")
    public void test(@RequestBody TestBean testBean) {
        System.out.println(testBean);
    }
}
