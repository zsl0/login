package com.zsl.service.impl;

import com.zsl.entity.Logging;
import com.zsl.mapper.LoggingMapper;
import com.zsl.service.LoggingService;
import com.zsl.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author zsl
 * @Date 2022/1/4 9:35
 */
@Service
public class LoggingServiceImpl extends BaseServiceImpl<Logging, LoggingMapper> implements LoggingService {
}
