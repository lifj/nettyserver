package com.lfj.nettyserver.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2018/10/8.
 */
public class IcCardMsg implements Serializable{

    private static final long serialVersionUID = 205074055021747426L;

    @Id
    private String id;
    private String readMsg;         //读入的原始信息
    private String mchId;           //从原始信息中获取到的设备ID
    private String cardId;          //从原始信息中获取的IC卡号
    private Date readTime;        //读卡时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReadMsg() {
        return readMsg;
    }

    public void setReadMsg(String readMsg) {
        this.readMsg = readMsg;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }
}

