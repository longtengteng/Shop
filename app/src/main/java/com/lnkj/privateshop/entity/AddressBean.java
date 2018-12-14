package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class AddressBean {

    /**
     * status : 1
     * info : 区域列表
     * data : [{"id":"2","region_name":"北京市"},{"id":"3","region_name":"天津市"}]
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
         * id : 2
         * region_name : 北京市
         */

        private String id;
        private String region_name;
        private Boolean ischeck=false;

        public Boolean getIscheck() {
            return ischeck;
        }

        public void setIscheck(Boolean ischeck) {
            this.ischeck = ischeck;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }
    }
}
