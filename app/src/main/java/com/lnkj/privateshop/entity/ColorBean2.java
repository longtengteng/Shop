package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class ColorBean2 {


    /**
     * data : [{"color_code":"default","color_img":"/Static/images/color/default.png","id":"31","item":"如图"},{"color_code":"FFFFFF","color_img":"/Static/images/color/FFFFFF.png","id":"15","item":"白色"},{"color_code":"EBEBEB","color_img":"/Static/images/color/EBEBEB.png","id":"16","item":"灰色"},{"color_code":"2D2D2D","color_img":"/Static/images/color/2D2D2D.png","id":"17","item":"黑色"},{"color_code":"CC0F01","color_img":"/Static/images/color/CC0F01.png","id":"2","item":"红色"},{"color_code":"FFD247","color_img":"/Static/images/color/FFD247.png","id":"18","item":"黄色"},{"color_code":"22D415","color_img":"/Static/images/color/22D415.png","id":"19","item":"绿色"},{"color_code":"4D9DFF","color_img":"/Static/images/color/4D9DFF.png","id":"3","item":"蓝色"},{"color_code":"370341","color_img":"/Static/images/color/370341.png","id":"20","item":"紫色"},{"color_code":"FFA3D7","color_img":"/Static/images/color/FFA3D7.png","id":"1","item":"粉色"},{"color_code":"C5771A","color_img":"/Static/images/color/C5771A.png","id":"21","item":"卡其色"},{"color_code":"F08512","color_img":"/Static/images/color/F08512.png","id":"22","item":"橙色"},{"color_code":"FFF2C7","color_img":"/Static/images/color/FFF2C7.png","id":"23","item":"米色"}]
     * info : 常用颜色
     * status : 1
     */

    private String info;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * color_code : default
         * color_img : /Static/images/color/default.png
         * id : 31
         * item : 如图
         */

        private String color_code;
        private String color_img;
        private String id;
        private String item;
        private Boolean ischeck=false;

        public Boolean getIscheck() {
            return ischeck;
        }

        public void setIscheck(Boolean ischeck) {
            this.ischeck = ischeck;
        }

        public String getColor_code() {
            return color_code;
        }

        public void setColor_code(String color_code) {
            this.color_code = color_code;
        }

        public String getColor_img() {
            return color_img;
        }

        public void setColor_img(String color_img) {
            this.color_img = color_img;
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
    }
}
