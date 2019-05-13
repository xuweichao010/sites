package com.xwc.config.web;

import com.alibaba.fastjson.JSONObject;
import com.xwc.commons.model.EventMessage;
import com.xwc.commons.model.JsonMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/5  15:26
 * 业务：
 * 功能：
 */
@ServerEndpoint(value = "/auth/websocket/{sid}")
@Component
public class WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    public static ConcurrentHashMap<String, WebSocketServer> websocketMap = new ConcurrentHashMap<>();
    private Session session;

    //接收sid
    private String sid = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) throws IOException {
        this.session = session;
        websocketMap.put(sid, this);
        this.sid = sid;
        session.getBasicRemote().sendText(EventMessage.create(EventType.connect, null).toString());
        logger.info("当前在线人数：{}", WebSocketServer.websocketMap.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        logger.info("连接被关闭:{}", sid);
        websocketMap.remove(sid);
        logger.info("当前在线人数：{}", WebSocketServer.websocketMap.size());
    }

    /**
     * 收到消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到：{}  消息:{}", sid, message);
    }

    /**
     * 消息发送错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送消息
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
