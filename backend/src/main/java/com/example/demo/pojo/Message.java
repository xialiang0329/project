package com.example.demo.pojo;

import java.io.Serializable;

/**
 * (Message)实体类
 *
 * @author makejava
 * @since 2021-04-25 11:40:43
 */
public class Message implements Serializable
{
    private static final long serialVersionUID = -73547120595419435L;

    private String pkid;

    /**
     * 发送人
     */
    private String userId;

    /**
     * 接收人
     */
    private String toUserId;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 消息内容
     */
    private String content;

    public String getPkid()
    {
        return pkid;
    }

    public void setPkid(String pkid)
    {
        this.pkid = pkid;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getToUserId()
    {
        return toUserId;
    }

    public void setToUserId(String toUserId)
    {
        this.toUserId = toUserId;
    }

    public String getMessageType()
    {
        return messageType;
    }

    public void setMessageType(String messageType)
    {
        this.messageType = messageType;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

}
