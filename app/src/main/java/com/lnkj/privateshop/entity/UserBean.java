package com.lnkj.privateshop.entity;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class UserBean {

    /**
     * data : {"birthday":"0","disabled":"1","email":"","frozen_money":"0","head_pic":"","mobile":"15562909157","nickname":"","password":"$2a$08$bBKktXqIL3V6z.8upnm3pOmguvFe0.e5jKpMYTs3tiXVrQnRtcKXS","qq":"","rank_id":"1","sex":"2","shop_id":"0","user_id":"100","user_money":"0","user_name":"","weixin":""}
     * info : 登录成功
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
         * birthday : 0
         * disabled : 1
         * email :
         * frozen_money : 0
         * head_pic :
         * mobile : 15562909157
         * nickname :
         * password : $2a$08$bBKktXqIL3V6z.8upnm3pOmguvFe0.e5jKpMYTs3tiXVrQnRtcKXS
         * qq :
         * rank_id : 1
         * sex : 2
         * shop_id : 0
         * user_id : 100
         * user_money : 0
         * user_name :
         * weixin :
         */

        private String birthday;
        private String disabled;
        private String email;
        private String frozen_money;
        private String head_pic;
        private String mobile;
        private String nickname;
        private String password;
        private String qq;
        private String rank_id;
        private String sex;
        private String shop_id;
        private String user_id;
        private String user_money;
        private String user_name;
        private String weixin;
        private String token;
        private int is_shop;
        private int pay_password;
        private String emchat_password;
        private String emchat_username;

        public int getPay_password() {
            return pay_password;
        }

        public void setPay_password(int pay_password) {
            this.pay_password = pay_password;
        }

        public String getEmchat_username() {
            return emchat_username;
        }

        public void setEmchat_username(String emchat_username) {
            this.emchat_username = emchat_username;
        }

        public String getEmchat_password() {
            return emchat_password;
        }

        public void setEmchat_password(String emchat_password) {
            this.emchat_password = emchat_password;
        }
        public int getIs_shop() {
            return is_shop;
        }

        public void setIs_shop(int is_shop) {
            this.is_shop = is_shop;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getDisabled() {
            return disabled;
        }

        public void setDisabled(String disabled) {
            this.disabled = disabled;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getRank_id() {
            return rank_id;
        }

        public void setRank_id(String rank_id) {
            this.rank_id = rank_id;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }
    }
}
