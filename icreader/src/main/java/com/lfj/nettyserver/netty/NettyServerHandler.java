package com.lfj.nettyserver.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by lenovo on 2018/9/30.
 */
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Log log = LogFactory.getLog(NettyServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

    }

}
