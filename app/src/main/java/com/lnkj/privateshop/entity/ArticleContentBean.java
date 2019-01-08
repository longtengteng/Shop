package com.lnkj.privateshop.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class ArticleContentBean implements Serializable {


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
         * article_id : 6
         * title : 客户信息管理
         * thumb_img : /Uploads/Picture/Article/2018-12-08/5c0b7411bc562.jpg
         * content : <p>为三星</p>
         * like_count : 1
         * comment_count : 0
         */

        private String article_id;
        private String title;
        private String thumb_img;
        private String content;
        private String like_count;
        private String comment_count;

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

        public String getThumb_img() {
            return thumb_img;
        }

        public void setThumb_img(String thumb_img) {
            this.thumb_img = thumb_img;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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
