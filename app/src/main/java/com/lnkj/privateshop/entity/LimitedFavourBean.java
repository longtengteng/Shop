package com.lnkj.privateshop.entity;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class LimitedFavourBean {


    /**
     * data : {"act_img":"/Uploads/Picture/Activity/2017-09-20/59c1d8e3db34d.jpg","end_time":"1505908500","now_time":1505892640,"start_time":"1505843700"}
     * info : 限时特惠
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
         * act_img : /Uploads/Picture/Activity/2017-09-20/59c1d8e3db34d.jpg
         * end_time : 1505908500
         * now_time : 1505892640
         * start_time : 1505843700
         */

        private String act_img;
        private String end_time;
        private int now_time;
        private String start_time;

        public String getAct_img() {
            return act_img;
        }

        public void setAct_img(String act_img) {
            this.act_img = act_img;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getNow_time() {
            return now_time;
        }

        public void setNow_time(int now_time) {
            this.now_time = now_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }
    }
}
