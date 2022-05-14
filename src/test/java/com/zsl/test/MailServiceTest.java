package com.zsl.test;

import com.zsl.core.cache.TokenServer;
import com.zsl.core.cache.impl.TokenServerImpl;
import com.zsl.util.MailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Author zsl
 * @Date 2021/12/29 16:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MailServiceTest {
    @Autowired
    MailService mailService;

    Logger logger = LoggerFactory.getLogger(MailServiceTest.class);

    @Test
    public void test() throws InterruptedException {
        /*System.out.println(mailService);
        mailService.sendEmail("249269610@qq.com", "验证码", "44444");
        System.out.println("sendEmail 调用");
        Thread.sleep(10000);*/
    }

    @Test
    public void test02() {
        System.out.println(logger);
        logger.debug("logger debug: test");
        logger.info("logger info: test");
        logger.warn("logger warn: test");
        logger.error("logger error: test");

        log.debug("Slf4j debug: log...");
        log.info("Slf4j info: log...");
        log.warn("Slf4j warn: log...");
        log.error("Slf4j error: log...");
    }

    @Autowired
    TokenServerImpl tokenServer;

    @Test
    public void test03() {
//        System.out.println(tokenServer.timeout);
    }
}
