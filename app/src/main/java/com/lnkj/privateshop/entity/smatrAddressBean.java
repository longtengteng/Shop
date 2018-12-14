package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * @Description
 * @Author (ZHL/383608571@qq.com)
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date on 2018/4/4 0004 11:25
 * @CreateBy android_studio
 */
public class smatrAddressBean {

    /**
     * status : 0
     * info : 识别成功
     * data : {"name":"tianzhongjian","mobile":"18953935328","code":"276034","address":"山东省 临沂市 河东区 芝麻墩街道 冠亚星城","thr_address":[{"id":"16","region_code":"370000","region_name":"山东省","parent_id":"1"},{"id":"182","region_code":"371300","region_name":"临沂市","parent_id":"16"},{"id":"1833","region_code":"371301","region_name":"河东区","parent_id":"182"}]}
     */

    private int status;
    private String info;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : tianzhongjian
         * mobile : 18953935328
         * code : 276034
         * address : 山东省 临沂市 河东区 芝麻墩街道 冠亚星城
         * thr_address : [{"id":"16","region_code":"370000","region_name":"山东省","parent_id":"1"},{"id":"182","region_code":"371300","region_name":"临沂市","parent_id":"16"},{"id":"1833","region_code":"371301","region_name":"河东区","parent_id":"182"}]
         */

        private String name;
        private String mobile;
        private String code;
        private String address;
        private List<ThrAddressBean> thr_address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<ThrAddressBean> getThr_address() {
            return thr_address;
        }

        public void setThr_address(List<ThrAddressBean> thr_address) {
            this.thr_address = thr_address;
        }

        public static class ThrAddressBean {
            /**
             * id : 16
             * region_code : 370000
             * region_name : 山东省
             * parent_id : 1
             */

            private String id;
            private String region_code;
            private String region_name;
            private String parent_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRegion_code() {
                return region_code;
            }

            public void setRegion_code(String region_code) {
                this.region_code = region_code;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }
        }
    }
}
