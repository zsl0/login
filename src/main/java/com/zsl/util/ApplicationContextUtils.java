package com.zsl.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author zsl
 * @Date 2021/12/29 17:18
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Objects.isNull(ApplicationContextUtils.applicationContext)) {
            ApplicationContextUtils.applicationContext = applicationContext;
        }
    }

    /**
     * 通过名称获取bean
     */
    public static Object getBeanByName(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * 通过Class获取bean
     */
    public static <T> T getBeanByClass(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过Class 和 beanName 获取bean
     */
    public static <T> T getBeanByClassAndName(Class<T> clazz, String beanName) {
        return applicationContext.getBean(beanName, clazz);
    }
}
