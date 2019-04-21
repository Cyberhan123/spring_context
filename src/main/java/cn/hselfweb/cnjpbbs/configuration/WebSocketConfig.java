package cn.hselfweb.cnjpbbs.configuration;


import cn.hselfweb.cnjpbbs.handler.MarcoHandler;
import cn.hselfweb.cnjpbbs.interceptor.MarcoHandShakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private MarcoHandler marcoHandler;
    private MarcoHandShakeInterceptor handshakeInterceptor;

    @Autowired
    public WebSocketConfig(MarcoHandler marcoHandler, MarcoHandShakeInterceptor handshakeInterceptor) {
        this.marcoHandler = marcoHandler;
        this.handshakeInterceptor = handshakeInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //部分 支持websocket 的访问链接,允许跨域
        registry.addHandler(marcoHandler, "/echo").addInterceptors(handshakeInterceptor).setAllowedOrigins("*");
        //部分 不支持websocket的访问链接,允许跨域
        registry.addHandler(marcoHandler, "/sockjs/echo").addInterceptors(handshakeInterceptor).setAllowedOrigins("*").withSockJS();
    }
}


