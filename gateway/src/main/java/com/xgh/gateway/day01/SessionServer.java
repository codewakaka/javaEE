package com.xgh.gateway.day01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.concurrent.Callable;


/**
 * 网关会话服务
 * @author xgh
 */
public class SessionServer implements Callable<Channel> {

    private final Logger logger = LoggerFactory.getLogger(SessionServer.class);

    private final EventLoopGroup boss = new NioEventLoopGroup(1);

    private final EventLoopGroup work = new NioEventLoopGroup();

    private Channel channel;


    @Override
    public Channel call() throws Exception {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childHandler(new SessionChannelInitializer());
            channelFuture =  b.bind(7387).syncUninterruptibly();
            this.channel = channelFuture.channel();
        }catch (Exception e){
            logger.error("socket server start error.",e);
        }finally {
            if(null != channelFuture || channelFuture.isSuccess()){
                logger.error("socket server start done.");
            }else {
                logger.error("socket server start error.");
            }
        }
        return channel;
    }
}
