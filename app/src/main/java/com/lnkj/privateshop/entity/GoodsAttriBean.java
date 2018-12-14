package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class GoodsAttriBean {


    /**
     * data : [{"_values":["日韩","欧美","大众","运动风","中国风","复古","淑女","性感"],"attr_id":"28","attr_name":"风格"},{"_values":["春夏","秋冬"],"attr_id":"44","attr_name":"季节"},{"_values":["涤纶","呢料","针织","雪纺蕾丝","棉麻","皮草","牛仔","卫衣料","羽绒"],"attr_id":"36","attr_name":"面料"},{"_values":["透","偏薄","适中","偏厚","加厚","加绒"],"attr_id":"29","attr_name":"厚度"}]
     * info : 属性列表
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
         * _values : ["日韩","欧美","大众","运动风","中国风","复古","淑女","性感"]
         * attr_id : 28
         * attr_name : 风格
         */

        private String attr_id;
        private String attr_name;
        private List<String> values;

        public String getAttr_id() {
            return attr_id;
        }

        public void setAttr_id(String attr_id) {
            this.attr_id = attr_id;
        }

        public String getAttr_name() {
            return attr_name;
        }

        public void setAttr_name(String attr_name) {
            this.attr_name = attr_name;
        }

        public List<String> get_values() {
            return values;
        }

        public void set_values(List<String> _values) {
            this.values = _values;
        }
    }
}
