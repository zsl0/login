package com.zsl.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsl.common.converter.StringToLocalDateTimeConverter;
import com.zsl.common.exception.MessageException;
import com.zsl.common.interceptor.LoggingInterceptor;
import com.zsl.common.interceptor.TokenInterceptor;
import com.zsl.entity.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zsl
 * @Date 2021/12/29 11:56
 */
@Configuration
public class MyWebMVCConfig {
    @Autowired
    LoggingInterceptor loggingInterceptor;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        // 跨域请求 拒绝携带cookie
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(false)
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(loggingInterceptor);
                registry.addInterceptor(new TokenInterceptor());
            }

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                // todo 如何使用 HttpMessageConverter MediaType （自定义参数处理）
                // 支持判断 Class 和 MediaType 类型
                /*converters.add(new HttpMessageConverter<LocalDateTime>() {
                    @Override
                    public boolean canRead(Class<?> clazz, MediaType mediaType) {
                        return false;
                    }

                    @Override
                    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
                        return false;
                    }

                    @Override
                    public List<MediaType> getSupportedMediaTypes() {
                        List<MediaType> mediaTypes = new ArrayList<>();
                        mediaTypes.add(MediaType.ALL);
                        return mediaTypes;
                    }

                    @Override
                    public LocalDateTime read(Class<? extends LocalDateTime> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
                        int read = inputMessage.getBody().read();
                        System.out.println("exec read");
                        return LocalDateTime.now();
                    }

                    @Override
                    public void write(LocalDateTime time, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
                        System.out.println("exec write");
                    }
                });*/
            }

            @Override
            public void addFormatters(FormatterRegistry registry) {
                // todo Converter 不生效的原因 （自定义转换规则）
                // 已解决 需要使用 get 请求 key:value 格式（具体原因未解决）
                /*registry.addConverter(new Converter<String, TestBean>() {
                    @Override
                    public TestBean convert(String s) {
                        TestBean testBean = new TestBean();
                        String[] s1 = s.split(" ");
                        testBean.setPrename(s1[0]);
                        testBean.setSufname(s1[1]);
                        return testBean;
                    }
                });*/
                /*registry.addConverter(new Converter<String, LocalDateTime>() {
                    @Override
                    public LocalDateTime convert(String s) {
                        try {
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
                        }
                    }
                });*/
//                registry.addConverter(stringToLocalDateTimeConverter);
            }


        };
    }

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        ObjectMapper objectMapper = new ObjectMapper();
        jackson2ObjectMapperBuilder.configure(objectMapper);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
}
