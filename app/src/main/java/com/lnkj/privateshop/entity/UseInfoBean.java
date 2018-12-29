package com.lnkj.privateshop.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class UseInfoBean implements Serializable {


    /**
     * data : {"credit":"0","head_pic":"/Uploads/Picture/User/115/20171008_153000_15074478007342_7790.jpg","is_openShop":0,"nickname":"111","points":"0","register_time":"2017-09-23","user_level":"VIP1"}
     * info : 用户信息
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

    public static class DataBean implements Serializable {
        /**
         * credit : 0
         * head_pic : /Uploads/Picture/User/115/20171008_153000_15074478007342_7790.jpg
         * is_openShop : 0
         * nickname : 111
         * points : 0
         * register_time : 2017-09-23
         * user_level : VIP1
         */

        private String credit;
        private String head_pic;
        private int is_shop;
        private String nickname;
        private String points;
        private String register_time;
        private String user_level;
        private int has_shop;

        public int getHas_shop() {
            return has_shop;
        }

        public void setHas_shop(int has_shop) {
            this.has_shop = has_shop;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public int getIs_shop() {
            return is_shop;
        }

        public void setIs_shop(int is_shop) {
            this.is_shop = is_shop;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getRegister_time() {
            return register_time;
        }

        public void setRegister_time(String register_time) {
            this.register_time = register_time;
        }

        public String getUser_level() {
            return user_level;
        }

        public void setUser_level(String user_level) {
            this.user_level = user_level;
        }
    }
}
