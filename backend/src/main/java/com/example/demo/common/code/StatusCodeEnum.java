package com.example.demo.common.code;

public class StatusCodeEnum {

    public static final Integer SUCCESS_CODE = 0;

    public static final Integer EXPIRE_DATE = -1;

    public static final Integer EXPIRES_IN = 7200;//s

    public enum officialAccount {
        access_token("official_account_access_token"),

        ticket("official_account_ticket"),

        signature("official_account_signature"),

        timestamp("official_account_timestamp"),

        noncestr("official_account_noncestr"),
        ;

        private String key;

        public String getKey() {
            return key;
        }

        officialAccount(String key) {
            this.key = key;
        }
    }

    /**
     * 登录状态
     */
    public enum loginStatus {
        not_on_line("0"),//离线

        on_line("1"),//在线

        cancellation("2"),//注销
        ;
        private String key;

        public String getKey() {
            return key;
        }

        loginStatus(String key) {
            this.key = key;
        }
    }

    /**
     * socket.io
     */
    public enum socketBodyType{
        //推送类型
        to_broadcast("-1"),//广播
        from_system("system"),//系统信息

        //信息类型
        message_text("text"),
        ;
        private String key;

        public String getKey() {
            return key;
        }

        socketBodyType(String key) {
            this.key = key;
        }
    }
}
