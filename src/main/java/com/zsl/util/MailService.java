package com.zsl.util;

import com.zsl.core.cache.CodeServer;
import com.zsl.entity.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zsl
 * @Date 2021/12/29 16:32
 */
@Service
@Slf4j
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String from;

    @Value("${spring.mail.codeExpire:3}")
    String codeExpire;

    @Autowired
    CodeServer codeServer;

    @Value("${admin.mail}")
    String adminMail;

    /**
     * 发送邮件
     */
    private void sendEmail(String to, String subject, String text) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom("XXX名称<" + from + ">");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, true);
            javaMailSender.send(mimeMessage);
            log.info("发送邮件成功 to={}", to);
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("发送邮件失败 to={}", to);
        }
    }

    /**
     * 发送邮箱验证码
     */
    public void sendEmailCode(User user, int codeLength) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取 freemarker 模板
                String text = null;
                try {
                    // 获取模板
                    Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
                    configuration.setDirectoryForTemplateLoading(new File("E:\\JavaCode\\login\\src\\main\\resources\\ftl"));
                    configuration.setDefaultEncoding("UTF-8");
                    Template template = configuration.getTemplate("emailCode.ftl");
                    // 设置模板变量
                    String code = CodeUtils.getCode(codeLength);
                    Map<String, String> data = new HashMap<>();
                    data.put("name", user.getUserAccount());
                    data.put("code", code);
                    data.put("codeExpire", codeExpire);
                    // 保存验证码
                    codeServer.set(user.getUserEmail(), code);
                    // 获取模板字符串
                    StringWriter out = new StringWriter();
                    template.process(data, out);
                    text = out.toString();
                    out.close();
                    // 发送邮件
                    String subject = "请注意查收验证码";
                    sendEmail(user.getUserEmail(), subject, text);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("获取邮箱验证码 freemarker 失败");
                }
            }
        }).start();
    }

    /**
     * 预警邮件
     */
    public void sendWarnMail(String msg) {
        log.info("发送预警邮件 date = {}", LocalDateTime.now().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendEmail(adminMail, "Warning 预警", msg);
            }
        }).start();
    }
}
