package com.lfj.nettyserver.repository;

import com.lfj.nettyserver.entity.IcCardMsg;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by lenovo on 2018/10/8.
 */
public interface IcCardMsgRepository extends MongoRepository<IcCardMsg, String> {
}
