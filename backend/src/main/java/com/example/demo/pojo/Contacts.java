package com.example.demo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
public class Contacts implements Serializable {
    private static final long serialVersionUID = 577042502561526498L;
    private String id;
    @JSONField(name = "to_user_id")
    private String toUserId;
    @JSONField(name = "user_id")
    private String userId;
}
