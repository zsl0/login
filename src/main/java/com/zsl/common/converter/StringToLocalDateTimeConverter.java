package com.zsl.common.converter;

import com.zsl.common.exception.MessageException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author zsl
 * @Date 2022/1/7 10:30
 */
@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String s) {
        // 可支持多种格式进行判断格式化
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        /*try {
            String[] ts = s.split("T");
            String[] date = ts[0].split("-");
            String[] time = ts[1].split(":");
            if (time.length == 2) {
                return LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0);
            }
            return LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new MessageException("String 转换 LocalDateTime 错误 e = " + e.getMessage());
        }*/
    }
}
