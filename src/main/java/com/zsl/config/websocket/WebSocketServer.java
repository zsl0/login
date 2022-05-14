package com.zsl.config.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 原生 websocket 基本使用
 * @Author zsl
 * @Date 2022/1/23 17:57
 */
@Slf4j
@Service
@ServerEndpoint("/api/websocket")   // websocket接口与@RequestMapping功能相同 url: ws://ip:port/api/websocket
public class WebSocketServer {

    /**
     * 建立连接，基于http（三次握手）协议
     */
    @OnOpen
    public void onOpen(Session session) {
        log.info("onOpen...");
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose() {
        log.info("onClose...");
    }

    /**
     * 数据传输
     */
    @OnMessage
    public void onMessage(String text, Session session) {
        log.info("onMessage... text = {}", text);
        try {
            session.getBasicRemote().sendText("echo: " + text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 出现异常时执行
     */
    @OnError
    public void onError(Session session, Throwable t) {
        log.info("onError...");
        log.debug("throwable = {}", t.getMessage());
    }
}
