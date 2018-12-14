package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class TalkRecordBean {

    /**
     * status : 1
     * info : 协商记录
     * data : [{"admin_type":"0","title":"申请退货退款","add_time":"2017-09-27 10:23:33","content":"申请退货退款","reason":"拍错了，不想买","desc":"Dffdf","refundAmont":"215.00"},{"title":"撤销退货退款操作","add_time":"2017-09-27 13:52:35","content":"撤销退货退款操作"}]
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
         * admin_type : 0
         * title : 申请退货退款
         * add_time : 2017-09-27 10:23:33
         * content : 申请退货退款
         * reason : 拍错了，不想买
         * desc : Dffdf
         * refundAmont : 215.00
         */

        private String admin_type;
        private String title;
        private String add_time;
        private String content;
        private String reason;
        private String desc;
        private String refundAmont;

        public String getAdmin_type() {
            return admin_type;
        }

        public void setAdmin_type(String admin_type) {
            this.admin_type = admin_type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getRefundAmont() {
            return refundAmont;
        }

        public void setRefundAmont(String refundAmont) {
            this.refundAmont = refundAmont;
        }
    }
}

