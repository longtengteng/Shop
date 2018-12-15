package com.lnkj.privateshop.entity;

import java.util.List;

public class HotBannerBean {

    /**
     * ad_id : 34
     * title : 爆款测试
     * link_url : http://baidu.com
     * content : /Uploads/Picture/Ad/2018-12-06/5c08bbec383b8.jpg
     * type : 0
     * item_id : 0
     */
    private int status;
    private String info;
    private List<HotBannerBean.DataBean> data;

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

    public List<HotBannerBean.DataBean> getData() {
        return data;
    }

    public void setData(List<HotBannerBean.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String ad_id;
        private String title;
        private String link_url;
        private String content;
        private String type;
        private String item_id;

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
    }
}
