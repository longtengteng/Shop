package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class ShopClassBean {


    /**
     * status : 1
     * info : 分类列表
     * data : [{"count":"13","cat_name":"男装T恤","image":"","cat_id":"154"},{"count":"4","cat_name":"T恤","image":"","cat_id":"157"},{"count":"1","cat_name":"男士风衣","image":"","cat_id":"187"},{"count":"2","cat_name":"衬衫连衣裙","image":"","cat_id":"202"},{"count":"4","cat_name":"半身裙","image":"","cat_id":"204"}]
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
         * count : 13
         * cat_name : 男装T恤
         * image :
         * cat_id : 154
         */

        private String count;
        private String cat_name;
        private String image;
        private String cat_id;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }
    }
}
