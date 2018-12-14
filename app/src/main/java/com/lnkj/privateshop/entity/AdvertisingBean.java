package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class AdvertisingBean {

    /**
     * status : 1
     * info : 活动海报
     * data : [{"ad_id":"4","title":"pc首页banner图广告","link_url":"www.liuniukeji.com","content":"/Uploads/Picture/Ad/2017-06-30/595600351e525.jpg","position_id":"5"}]
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
         * ad_id : 4
         * title : pc首页banner图广告
         * link_url : www.liuniukeji.com
         * content : /Uploads/Picture/Ad/2017-06-30/595600351e525.jpg
         * position_id : 5
         */

        private String ad_id;
        private String title;
        private String link_url;
        private String content;
        private String position_id;
        private String type;
        private String item_id;




        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getAd_id() {
            return ad_id;
        }

        public void setAd_id(String ad_id) {
            this.ad_id = ad_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink_url() {
            return link_url;
        }

        public void setLink_url(String link_url) {
            this.link_url = link_url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPosition_id() {
            return position_id;
        }

        public void setPosition_id(String position_id) {
            this.position_id = position_id;
        }
    }
}
