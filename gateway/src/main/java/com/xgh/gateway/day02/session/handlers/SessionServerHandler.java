package com.xgh.gateway.day02.session.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xgh.gateway.day02.bind.IGenericReference;
import com.xgh.gateway.day02.session.BaseHandler;
import com.xgh.gateway.day02.session.Configuration;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 会话服务处理器
 */
public class SessionServerHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(SessionServerHandler.class);

    private final Configuration configuration;

    public SessionServerHandler(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求uri:{},method:{}",request.uri(),request.method());
        //返回信息控制
        String methodName = request.uri().substring(1);
        if(methodName.equals("favicon.ico")){return;}
        //返回信息处理
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        //服务泛化调用
        IGenericReference reference = configuration.getGenericReference("sayHi");
        String result = reference.$invoke("test)" + " " + System.currentTimeMillis());

        //设置回写数据
        response.content().writeBytes(JSON.toJSONBytes(result, SerializerFeature.PrettyFormat));
        //头部信息设置
        HttpHeaders heads = response.headers();
        //返回内容类型
        heads.add(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON+";charset=UTF-8");
        //响应体长度
        heads.add(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes());
        //配置持久化
        heads.add(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
        //配置跨域
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");

        channel.writeAndFlush(response);
    }
}
