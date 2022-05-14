package com.zsl.service.impl;

import com.zsl.common.exception.MessageException;
import com.zsl.entity.RadarDetail;
import com.zsl.entity.vo.RadarVO;
import com.zsl.service.RadarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NetcdfFile;
import ucar.nc2.NetcdfFiles;
import ucar.nc2.Variable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author zsl
 * @Date 2022/1/6 15:35
 */
@Service
@Slf4j
public class RadarServiceImpl implements RadarService {
    @Value("${ncfile.prepath}")
    String prePath;

    @Value("${ncfile.sufpath}")
    String sufPath;

    @Override
    public RadarDetail getRadarDetail(RadarVO radarVO) {
        NetcdfFile ncfile = null;
        try {
            ncfile = NetcdfFiles.open(prePath + getTimePath(radarVO.getTime()) + sufPath);

            Variable rVariable = ncfile.findVariable("R");
            Variable crVariable = ncfile.findVariable("CR");
            Variable vVariable = ncfile.findVariable("V");
            Variable timeVariable = ncfile.findVariable("time");
            Variable lonVariable = ncfile.findVariable("lon");
            Variable latVariable = ncfile.findVariable("lat");
            Variable levelVariable = ncfile.findVariable("level");

            // 处理仰角索引
            int levelIndex = Integer.parseInt(radarVO.getLevel()) - 1;

            // 处理当前经纬度位置
            int lonIndex = getArrIndex(radarVO.getLon(), (double[]) lonVariable.read().copyTo1DJavaArray());
            int latIndex = getArrIndex(radarVO.getLat(), (double[]) latVariable.read().copyTo1DJavaArray());

            // 获取四维 反射率 和径向速度
            int[] origin4d = new int[] {0, levelIndex, latIndex, lonIndex};
            int[] shape4d = new int[] {1, 1, 1, 1};
            float[] r = (float[]) rVariable.read(origin4d, shape4d).copyTo1DJavaArray();
            float[] v = (float[]) vVariable.read(origin4d, shape4d).copyTo1DJavaArray();

            // 获取三维 组合反射率
            int[] origin3d = new int[] {0, latIndex, lonIndex};
            int[] shape3d = new int[] {1, 1, 1};
            float[] cr = (float[]) crVariable.read(origin3d, shape3d).copyTo1DJavaArray();

            // 获取时间
            String time = timeVariable.findAttribute("units").getStringValue().replace("days since ", "");

            return RadarDetail.RadarDetailBuild.start()
                    .cr(cr[0])
                    .r(r[0])
                    .v(v[0])
                    .level(((double[]) levelVariable.read(new int[] {levelIndex}, new int[] {1}).copyTo1DJavaArray())[0])
                    .time(time)
                    .lon(Double.valueOf(radarVO.getLon()))
                    .lat(Double.valueOf(radarVO.getLat()))
                    .build();
        } catch (IOException | InvalidRangeException e) {
            log.debug("getRadarDetail 方法出现异常 e = {}", e.getMessage());
            e.printStackTrace();
            throw new MessageException("获取雷达信息失败");
        }
    }

    /**
     * 处理时间转换
     */
    private String getTimePath(String time) {
        // 2022-01-06T16:31:52.315304700
        String[] ts = time.split("\\.")[0].split("T");
        String date = ts[0].replace("-", "");
        return date + "\\" + date + "." + ts[1].replace(":", "");
    }

    /**
     * 处理经纬度
     */
    private int getArrIndex(String num, double[] doubles) {
        // 判断参数是否有效
        if (doubles == null || doubles.length == 0) {
            return -1;
        }

        // 处理经纬度
        String[] split = num.split("\\.");
        Integer integer = Integer.valueOf(split[0]);
        int decimal = 0;
        if (split.length == 2) {    //  处理小数点位数
            decimal = Integer.parseInt(split[1].substring(0, 2));
        }

        // 查询索引
        double curDouble = Double.parseDouble(integer + "." + decimal);
        int index = -1;
        for (int i = 0; i < doubles.length; i++) {
            if (doubles[i] < curDouble) {
                index = i;
            } else {
                break;
            }
        }

        // 判断临界值
        if (index == 0 || index == doubles.length - 1) {
            if (!String.valueOf(doubles[index]).startsWith(String.valueOf(curDouble))) {
                return -1;
            }
        }
        return index;
    }
}
