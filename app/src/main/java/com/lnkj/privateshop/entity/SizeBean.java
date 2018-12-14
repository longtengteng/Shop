package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class SizeBean {

    /**
     * status : 1
     * info : 常用尺码
     * data : [{"id":"4","item":"均码","color_code":""},{"id":"27","item":"4XL","color_code":""},{"id":"26","item":"3XL","color_code":""},{"id":"25","item":"2XL","color_code":""},{"id":"14","item":"XL","color_code":""},{"id":"13","item":"L","color_code":""},{"id":"7","item":"M","color_code":""},{"id":"6","item":"S","color_code":""},{"id":"5","item":"XS","color_code":""},{"id":"28","item":"5XL","color_code":""}]
     */

    private int status;
    private String info;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * item : 均码
         * color_code :
         */

        private String id;
        private String item;
        private String color_code;
        private Boolean ischeck=false;

        public Boolean getIscheck() {
            return ischeck;
        }

        public void setIscheck(Boolean ischeck) {
            this.ischeck = ischeck;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getColor_code() {
            return color_code;
        }

        public void setColor_code(String color_code) {
            this.color_code = color_code;
        }
    }
}
