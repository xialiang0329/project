package com.example.demo.common.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TokenUtil
{
    //设置过期时间
    private static final long EXPIRE_DATE_SECOND=10800;//三小时

    public static void main(String[] args) {

        List<String> strList = new ArrayList<>();
        strList.forEach(l ->{
            String[] split = l.split("");
            try
            {
                for (int i = 0; i < split.length; i++) {
                    System.out.print(split[i]);
                    Thread.sleep(500);
                }
                System.out.println();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
