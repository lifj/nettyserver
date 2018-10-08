package com.lfj.nettyserver.netty;

import com.lfj.nettyserver.constants.MchConstants;
import com.lfj.nettyserver.entity.IcCardMsg;
import com.lfj.nettyserver.manage.IcCardMsgBiz;
import com.lfj.nettyserver.utils.Utils;
import com.lfj.nettyserver.utils.XorCodeUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/9/30.
 */
@Component
public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    private static final Log log = LogFactory.getLog(NettyServerBootstrap.class);

    private IcCardMsgBiz icCardMsgBiz;

    public MessageDecoder(IcCardMsgBiz icCardMsgBiz) {
        this.icCardMsgBiz = icCardMsgBiz;
    }


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        InetSocketAddress inSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = inSocket.getAddress().getHostAddress();       //客户端IP

        in.markReaderIndex();
        int ableLen = in.readableBytes();
        byte[] body = new byte[ableLen];
        in.readBytes(body);

        String msg = Utils.bytesToHexString(body);                  //获取得读卡器传输的数据
        if("00".equals(msg)){
            return;
        }

        if(!checkMsg(msg)){
            in.clear();
            in.writeBytes(MchConstants.FAIL.getBytes());
            ctx.writeAndFlush(in);
            return;
        }
        log.info("#msg-" + clientIp + "-" + (new Date().getTime()) +"-" + msg + "-msg#" );              //记录日志 #msg-ip-time-data-msg#，可以从info日志里提取这样的数据进行二次校验。

        //去掉后两位，进行补码计算
        String dataMsg = msg.substring(0, msg.length()-2);

        String machineHexCode = msg.substring(0, 32);
        String machineId = new String(Utils.hexStringToBytes(machineHexCode),"UTF-8");      //设备序列号
        String cardId = dataMsg.substring(34);                                                              // IC卡ID

        IcCardMsg readMsg = new IcCardMsg();
        readMsg.setCardId(cardId);
        readMsg.setMchId(machineId);
        readMsg.setReadMsg(msg);
        readMsg.setReadTime(new Date());
        icCardMsgBiz.save(readMsg);

        in.clear();
        in.writeBytes(MchConstants.SUCCESS.getBytes());
        ctx.writeAndFlush(in);
        log.info("write success");
    }



    //校验传递的数据是否OK
    private Boolean checkMsg(String msg) {

        if(msg.length() < 10){
            log.error("data length is error");
            return false;
        }

        //去掉后两位，进行补码计算
        String dataMsg = msg.substring(0, msg.length()-2);

        //补码
        String complement = msg.substring(msg.length()-2);

        //校验补码
        String calculateXorCode = XorCodeUtils.calculateXorCode(dataMsg);
        if(!calculateXorCode.equals(complement)){
            return false;
        }
        return true;

    }
}
