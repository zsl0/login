package com.zsl;

import com.zsl.util.TokenUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author zsl
 * @Date 2021/12/29 9:44
 */
@SpringBootApplication
@EnableTransactionManagement    // 事务
@EnableAspectJAutoProxy // AOP
@ServletComponentScan   // 扫描 Servlet 注解(@WebFilter、@WebListener、@WebServlet)
//@EnableScheduling // 定时器
@EnableAsync
//@EnableSwagger2
public class LoginApp {
    public static void main(String[] args) {
        SpringApplication.run(LoginApp.class, args);
    }

    /**
     * 替换@EnableScheduling注解，避免与@EnableWebSocket冲突
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduling = new ThreadPoolTaskScheduler();
        scheduling.setPoolSize(10);
        scheduling.initialize();
        return scheduling;
    }

}
