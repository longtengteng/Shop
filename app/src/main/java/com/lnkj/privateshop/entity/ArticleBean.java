package com.lnkj.privateshop.entity;

import java.util.List;

public class ArticleBean {

    private int status;
    private String info;
    private List<ArticleBean.DataBean> data;

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

    public List<ArticleBean.DataBean> getData() {
        return data;
    }

    public void setData(List<ArticleBean.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * article_category_id : 6
         * article_id : 3
         * title : 123123213
         * introduce : 3213213213213212
         * thumb_img : /Uploads/Picture/Article/2018-12-08/5c0b6b764d2b4.jpg
         * display : 1
         * sort : 50
         * like_count : 1
         * comment_count : 4
         */

        private String article_category_id;
        private String article_id;
        private String title;
        private String introduce;
        private String thumb_img;
        private String display;
        private String sort;
        private String like_count;
        private String comment_count;

        public String getArticle_category_id() {
            return article_category_id;
        }

        public void setArticle_category_id(String article_category_id) {
            this.article_category_id = article_category_id;
        }

        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getThumb_img() {
            return thumb_img;
        }

        public void setThumb_img(String thumb_img) {
            this.thumb_img = thumb_img;
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

        public String getLike_count() {
            return like_count;
        }

        public void setLike_count(String like_count) {
            this.like_count = like_count;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }
    }
}
