package com.lnkj.privateshop.entity;

import java.util.List;

public class ArticleCateBean {


    private int status;
    private String info;
    private List<ArticleCateBean.DataBean> data;

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

    public List<ArticleCateBean.DataBean> getData() {
        return data;
    }

    public void setData(List<ArticleCateBean.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * article_category_id : 6
         * name : 青年类
         * display : 1
         * sort : 50
         */

        private String article_category_id;
        private String name;
        private String display;
        private String sort;

        public String getArticle_category_id() {
            return article_category_id;
        }

        public void setArticle_category_id(String article_category_id) {
            this.article_category_id = article_category_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }
}
