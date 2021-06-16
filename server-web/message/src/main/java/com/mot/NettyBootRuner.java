package com.mot;

import com.mot.handler.channel.WebsocketMessageHandler;
import com.mot.config.properties.NettyConfig;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author tianfangxu
 */
@Component
public class NettyBootRuner implements CommandLineRunner {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    NettyConfig nettyConfig;

    private Channel serverChannel;

    @Override
    public void run(String... args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.localAddress(new InetSocketAddress(nettyConfig.nettyWebsocketIp, nettyConfig.nettyWebsocketPort));
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new HttpServerCodec());
                    pipeline.addLast(new ChunkedWriteHandler());
                    pipeline.addLast(new HttpObjectAggregator(65536));
                    pipeline.addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            if(msg instanceof FullHttpRequest) {
                                FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
                                String uri = fullHttpRequest.uri();
                                if (!uri.equals(nettyConfig.nettyWebsocketPath)) {
                                    // 访问的路径不是 websocket的端点地址，响应404
                                    ctx.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND))
                                            .addListener(ChannelFutureListener.CLOSE);
                                    return ;
                                }
                            }
                            super.channelRead(ctx, msg);
                        }
                    });
                    pipeline.addLast(new WebSocketServerCompressionHandler());
                    pipeline.addLast(new WebSocketServerProtocolHandler(nettyConfig.nettyWebsocketPath, null, true, nettyConfig.nettyWebsocketMaxFrameSize));
                    /**
                     * 心跳设置
                     */
                    pipeline.addLast(new IdleStateHandler(nettyConfig.nettyWebsocketHeartBeatRead,
                            nettyConfig.nettyWebsocketHeartBeatWrite,nettyConfig.nettyWebsocketHeartBeatAll, TimeUnit.SECONDS));
                    /**
                     * 从IOC中获取到Handler
                     */
                    pipeline.addLast(applicationContext.getBean(WebsocketMessageHandler.class));
                }
            });
            Channel channel = serverBootstrap.bind().sync().channel();
            this.serverChannel = channel;
            channel.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void onApplicationEvent(ContextClosedEvent event) {
        if (this.serverChannel != null) {
            this.serverChannel.close();
        }
    }
}
