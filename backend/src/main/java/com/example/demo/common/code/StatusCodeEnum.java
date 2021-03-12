package com.example.demo.common.code;

public class StatusCodeEnum
{

    public static final Integer SUCCESS_CODE = 0;

    public static final Integer EXPIRE_DATE = -1;

    public static final Integer EXPIRES_IN = 7200;//s

    public enum officialAccount
    {
        access_token("official_account_access_token"),

        ticket("official_account_ticket"),

        signature("official_account_signature"),

        timestamp("official_account_timestamp"),

        noncestr("official_account_noncestr"),
        ;

        private String key;

        public String getKey()
        {
            return key;
        }

        officialAccount(String key)
        {
            this.key = key;
        }
    }
}
