package com.example.demo.common.socketio.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
public class WebSocketioMessage
{
    //发送者name
    @NotBlank
    public String from;
    //发送者ID
    @NotBlank
    public String fromPkid;
    //接收者类型
    @NotBlank
    public String to;
    //接受者Id
    @NotBlank
    private String toUser;
    //发送的信息
    @NotBlank
    public String message;
    //发送的信息类型
    @NotBlank
    public String messageType;
    //发送时间
    @NotBlank
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public LocalDate date;
}
