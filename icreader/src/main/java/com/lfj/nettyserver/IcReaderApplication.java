package com.lfj.nettyserver;

import com.lfj.nettyserver.netty.NettyServerBootstrap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2018/10/8.
 */
@SpringBootApplication
public class IcReaderApplication  implements CommandLineRunner {

    @Resource
    private NettyServerBootstrap nettyServerBootstrap;

    public static void main(String[] args) {
        SpringApplication.run(IcReaderApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        nettyServerBootstrap.bind(9003);
    }

}
