package com.lfj.nettyserver.netty;

import com.lfj.nettyserver.manage.IcCardMsgBiz;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2018/9/30.
 */
@Component
public class NettyServerBootstrap {
    private static final Log log = LogFactory.getLog(NettyServerBootstrap.class);

    @Autowired
    private IcCardMsgBiz icCardMsgBiz;


    public void bind(int serverPort) throws Exception {
        // 连接处理group
        EventLoopGroup boss = new NioEventLoopGroup();
        // 事件处理group
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 绑定处理group
        bootstrap.group(boss, worker);
        bootstrap.channel(NioServerSocketChannel.class);
        // 保持连接数
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024 * 1024);
        // 有数据立即发送
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        // 保持连接
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        // 处理新连接
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                // 增加任务处理
                ChannelPipeline p = sc.pipeline();
                p.addLast(new IdleStateHandler(5, 0, 0))
                 .addLast(new MessageDecoder(icCardMsgBiz), new MessageEncoder(), new NettyServerHandler());
            //    p.addLast(new NettyServerHandler());
            }
        });

        ChannelFuture f = bootstrap.bind(serverPort).sync();
        if (f.isSuccess()) {
            log.info("long connection started success");
        } else {
            log.error("long connection started fail");
        }
    }
}
