package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class AddriGoodsBean {

    /**
     * status : 1
     * info : 属性参数
     * data : [{"attr_id":"28","attr_name":"风格","_value_list":[{"attr_value":"日韩"},{"attr_value":"欧美"},{"attr_value":"大众"},{"attr_value":"运动风"},{"attr_value":"中国风"},{"attr_value":"复古"},{"attr_value":"淑女"},{"attr_value":"性感"}]},{"attr_id":"44","attr_name":"季节","_value_list":[{"attr_value":"春夏"},{"attr_value":"秋冬"}]},{"attr_id":"36","attr_name":"面料","_value_list":[{"attr_value":"涤纶"},{"attr_value":"呢料"},{"attr_value":"针织"},{"attr_value":"雪纺蕾丝"},{"attr_value":"棉麻"},{"attr_value":"皮草"},{"attr_value":"牛仔"},{"attr_value":"卫衣料"},{"attr_value":"羽绒"}]},{"attr_id":"29","attr_name":"厚度","_value_list":[{"attr_value":"透"},{"attr_value":"偏薄"},{"attr_value":"适中"},{"attr_value":"偏厚"},{"attr_value":"加厚"},{"attr_value":"加绒"}]}]
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
         * attr_id : 28
         * attr_name : 风格
         * _value_list : [{"attr_value":"日韩"},{"attr_value":"欧美"},{"attr_value":"大众"},{"attr_value":"运动风"},{"attr_value":"中国风"},{"attr_value":"复古"},{"attr_value":"淑女"},{"attr_value":"性感"}]
         */

        private String attr_id;
        private String attr_name;
        private List<ValueListBean> _value_list;

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

        public List<ValueListBean> get_value_list() {
            return _value_list;
        }

        public void set_value_list(List<ValueListBean> _value_list) {
            this._value_list = _value_list;
        }

        public static class ValueListBean {
            /**
             * attr_value : 日韩
             */

            private String attr_value;

            public String getAttr_value() {
                return attr_value;
            }

            public void setAttr_value(String attr_value) {
                this.attr_value = attr_value;
            }
        }
    }
}
