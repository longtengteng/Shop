package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class ShipPingBean {

    /**
     * status : 1
     * info : 物流列表
     * data : [{"express_id":"1","name":"顺丰"},{"express_id":"10","name":"中通快递"},{"express_id":"5","name":"百世汇通"},{"express_id":"2","name":"圆通"},{"express_id":"9","name":"宅急送"}]
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
         * express_id : 1
         * name : 顺丰
         */

        private String express_id;
        private String name;
        private boolean incheck =false;

        public boolean isIncheck() {
            return incheck;
        }

        public void setIncheck(boolean incheck) {
            this.incheck = incheck;
        }

        public String getExpress_id() {
            return express_id;
        }

        public void setExpress_id(String express_id) {
            this.express_id = express_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
