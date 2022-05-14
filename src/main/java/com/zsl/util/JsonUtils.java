package com.zsl.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * @Author zsl
 * @Date 2021/12/29 14:17
 */
@Slf4j
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // init objectMapper
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 对象转json字符串
     */
    public static String obj2Str(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }

        if (obj instanceof String) {
            return (String)obj;
        }

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json字符串转对象
     */
    public static <T> T str2Obj(String jsonStr, Class<T> clazz) {
        if (Objects.isNull(jsonStr) || Objects.isNull(clazz)) {
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T) jsonStr : objectMapper.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return null;
        }
    }

    /**
     * json字符串转对象
     */
    public static <T> T str2Obj(String jsonStr, TypeReference<T> valueTypeRef) {
        if (Objects.isNull(jsonStr) || Objects.isNull(valueTypeRef)) {
            return null;
        }

        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return null;
        }
    }
}
