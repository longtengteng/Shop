package com.lnkj.privateshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class HelpBean implements Serializable{

    /**
     * status : 1
     * info : 文章列表
     * data : [{"title":"帮助1","addtime":"1502846917","article_id":"1"},{"title":"帮助2","addtime":"1502864958","article_id":"2"}]
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

    public static class DataBean implements Serializable{
        /**
         * title : 帮助1
         * addtime : 1502846917
         * article_id : 1
         */

        private String title;
        private String addtime;
        private String article_id;
        private String introduce;

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }
    }
}
