package com.example.demo.common.util;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TokenUtil
{
    //设置过期时间
    private static final long EXPIRE_DATE_SECOND=259200;//三天
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String createToken (){
       return UUID.randomUUID().toString();
    }
}
