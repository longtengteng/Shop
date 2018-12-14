package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class OrderWholeSaleBean {


    /**
     * data : [{"ad_id":"25","content":"/Uploads/Picture/Ad/2017-09-28/59cc969e7510c.png","link_url":"","position_id":"7","title":"定制批发区域"}]
     * info : 订制批发
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
         * ad_id : 25
         * content : /Uploads/Picture/Ad/2017-09-28/59cc969e7510c.png
         * link_url :
         * position_id : 7
         * title : 定制批发区域
         */

        private String ad_id;
        private String content;
        private String link_url;
        private String position_id;
        private String title;

        public String getAd_id() {
            return ad_id;
        }

        public void setAd_id(String ad_id) {
            this.ad_id = ad_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLink_url() {
            return link_url;
        }

        public void setLink_url(String link_url) {
            this.link_url = link_url;
        }

        public String getPosition_id() {
            return position_id;
        }

        public void setPosition_id(String position_id) {
            this.position_id = position_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
