package com.lnkj.privateshop.entity;

import java.util.List;

public class HomeGoodsCateBean {
    private int status;
    private String info;
    private List<HomeGoodsCateBean.DataBean> data;

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

    public List<HomeGoodsCateBean.DataBean> getData() {
        return data;
    }

    public void setData(List<HomeGoodsCateBean.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cat_id : 169
         * cat_name : 外套
         * image :
         * parent_id : 0
         * keywords :
         * sort : 2
         * description :
         * display : 1
         */

        private String cat_id;
        private String cat_name;
        private String image;
        private String parent_id;
        private String keywords;
        private String sort;
        private String description;
        private String display;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
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

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }
    }

}
