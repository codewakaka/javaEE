package com.xgh.gateway.day02.session;

import com.xgh.gateway.day02.session.handlers.SessionServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 会话管道初始化类
 */
public class SessionChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final Configuration configuration;

    public SessionChannelInitializer(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new HttpRequestDecoder())
                .addLast(new HttpResponseEncoder())
                .addLast(new HttpObjectAggregator(1024*1024))
                .addLast(new SessionServerHandler(configuration));
    }
}
