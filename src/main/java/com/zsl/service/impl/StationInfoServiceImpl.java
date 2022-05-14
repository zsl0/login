package com.zsl.service.impl;

import com.zsl.entity.StationInfo;
import com.zsl.entity.vo.StationInfoVO;
import com.zsl.mapper.StationInfoMapper;
import com.zsl.service.StationInfoService;
import com.zsl.service.base.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author zsl
 * @Date 2022/1/13 17:54
 */
@Service
@Slf4j
public class StationInfoServiceImpl extends BaseServiceImpl<StationInfo, StationInfoMapper> implements StationInfoService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Value("${sqlSession.batchNum:500}")
    private Integer batchNum;

    /**
     * 批量插入
     */
    /*@Override
    public void insertBatch(List<StationInfo> list) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            StationInfoMapper mapper = sqlSession.getMapper(StationInfoMapper.class);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (i != 0 && i%batchNum == 0) {
                    sqlSession.commit();
                }
                StationInfo stationInfo = list.get(i);
                mapper.insertOne(stationInfo, stationInfo.getStationTime().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            log.debug("批量插入出现异常 e = {}", e.getMessage());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }*/
    @Override
    public void insertBatch(List<StationInfo> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        // 获取插入时间
        String yyyyMMdd = list.get(0).getStationTime().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 批量插入
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            StationInfoMapper mapper = sqlSession.getMapper(StationInfoMapper.class);
            int startSize = 0;
            int endSize = list.size();
            for (int i = 0; i < endSize/batchNum; i++) {
                List<StationInfo> stationInfos = list.subList(startSize, startSize + 500);
                mapper.insertList(stationInfos, yyyyMMdd);
                sqlSession.commit();
                startSize += 500;
            }
            // 有余数时
            if (endSize%batchNum != 0) {
                List<StationInfo> stationInfos = list.subList(startSize, endSize);
                mapper.insertList(stationInfos, yyyyMMdd);
                sqlSession.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            log.debug("批量插入出现异常 e = {}", e.getMessage());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Override
    public List<StationInfo> selectByTimeAndStationId(StationInfoVO stationInfoVO) {
        return baseMapper.selectByTimeAndStationId(stationInfoVO, stationInfoVO.getStationTime().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    @Override
    public List<StationInfo> selectByTimeAndStationRange(StationInfoVO stationInfoVO) {
        return baseMapper.selectByTimeAndStationRange(stationInfoVO, stationInfoVO.getStationTime().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    @Override
    public List<StationInfo> selectByTimeAndStationArea(StationInfoVO stationInfoVO) {
        return baseMapper.selectByTimeAndStationArea(stationInfoVO, stationInfoVO.getStationTime().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }
}
