package com.mot.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/spring-netty-config.properties")
public class NettyConfig {

    @Value("${netty-websocket-port}")
    public int nettyWebsocketPort;
    @Value("${netty-websocket-ip}")
    public String nettyWebsocketIp;
    @Value("${netty-websocket-max-frame-size}")
    public int nettyWebsocketMaxFrameSize;
    @Value("${netty-websocket-path}")
    public String nettyWebsocketPath;
    @Value("${netty-websocket-heart-beat-read}")
    public Long nettyWebsocketHeartBeatRead;
    @Value("${netty-websocket-heart-beat-write}")
    public Long nettyWebsocketHeartBeatWrite;
    @Value("${netty-websocket-heart-beat-all}")
    public Long nettyWebsocketHeartBeatAll;
}
