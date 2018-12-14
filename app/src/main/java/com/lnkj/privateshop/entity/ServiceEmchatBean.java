package com.lnkj.privateshop.entity;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class ServiceEmchatBean {


    /**
     * data : {"chat_headpic":"/Static/images/kefu.png","chat_nickname":"定制小妹","chat_pwd":"123456","chat_username":"kefu1"}
     * info : 客服
     * status : 1
     */

    private DataBean data;
    private String info;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * chat_headpic : /Static/images/kefu.png
         * chat_nickname : 定制小妹
         * chat_pwd : 123456
         * chat_username : kefu1
         */

        private String chat_headpic;
        private String chat_nickname;
        private String chat_pwd;
        private String chat_username;

        public String getChat_headpic() {
            return chat_headpic;
        }

        public void setChat_headpic(String chat_headpic) {
            this.chat_headpic = chat_headpic;
        }

        public String getChat_nickname() {
            return chat_nickname;
        }

        public void setChat_nickname(String chat_nickname) {
            this.chat_nickname = chat_nickname;
        }

        public String getChat_pwd() {
            return chat_pwd;
        }

        public void setChat_pwd(String chat_pwd) {
            this.chat_pwd = chat_pwd;
        }

        public String getChat_username() {
            return chat_username;
        }

        public void setChat_username(String chat_username) {
            this.chat_username = chat_username;
        }
    }
}
