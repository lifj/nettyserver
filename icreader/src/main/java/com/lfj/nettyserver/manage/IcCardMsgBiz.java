package com.lfj.nettyserver.manage;

import com.lfj.nettyserver.entity.IcCardMsg;
import com.lfj.nettyserver.repository.IcCardMsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2018/10/8.
 */
@Component
public class IcCardMsgBiz {

    @Autowired
    private IcCardMsgRepository icCardMsgRepository;

    public IcCardMsg save(IcCardMsg icCardMsg){
        return icCardMsgRepository.save(icCardMsg);
    }

}
