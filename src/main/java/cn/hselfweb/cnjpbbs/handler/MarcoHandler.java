package cn.hselfweb.cnjpbbs.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class MarcoHandler implements WebSocketHandler  {
    private final static List<WebSocketSession> SESSIONS = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Connection established!");
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        System.out.println("Received message: " + webSocketMessage.getPayload());
        Thread.sleep(2000);
        webSocketSession.sendMessage(new TextMessage("Polo!"));
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        log.info("链接出错，关闭链接......");
        SESSIONS.remove(webSocketSession);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("Connection closed. Status: " + status);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
