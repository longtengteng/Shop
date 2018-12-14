package com.lnkj.privateshop.entity;


/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class ShopEmchatBean {

    /**
     * data : {"emchat_password":"pwd1509332188_75711","emchat_username":"20171030_105628_988623","nickname":"xhhxbx"}
     * info : 客服账号
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
         * emchat_password : pwd1509332188_75711
         * emchat_username : 20171030_105628_988623
         * nickname : xhhxbx
         */

        private String emchat_password;
        private String emchat_username;
        private String nickname;

        public String getEmchat_password() {
            return emchat_password;
        }

        public void setEmchat_password(String emchat_password) {
            this.emchat_password = emchat_password;
        }

        public String getEmchat_username() {
            return emchat_username;
        }

        public void setEmchat_username(String emchat_username) {
            this.emchat_username = emchat_username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
