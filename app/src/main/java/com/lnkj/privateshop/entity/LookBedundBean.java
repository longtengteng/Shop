package com.lnkj.privateshop.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public  class  LookBedundBean  {


    /**
     * status : 1
     * info : 余额详情
     * data : [{"date":"2017-06-26","log_list":[{"user_money":"9.90","change_type":"充值","change_desc":"将退货金额退还到余额","change_sign":"+"},{"user_money":"9.90","change_type":"充值","change_desc":"将退货金额退还到余额","change_sign":"+"}]},{"date":"2016-08-02","log_list":[{"user_money":"0.10","change_type":"提现","change_desc":"0000","change_sign":"-"}]},{"date":"2016-08-01","log_list":[{"user_money":"100.00","change_type":"提现","change_desc":"接着花","change_sign":"-"}]}]
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
         * date : 2017-06-26
         * log_list : [{"user_money":"9.90","change_type":"充值","change_desc":"将退货金额退还到余额","change_sign":"+"},{"user_money":"9.90","change_type":"充值","change_desc":"将退货金额退还到余额","change_sign":"+"}]
         */

        private String date;
        private List<LogListBean> log_list;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<LogListBean> getLog_list() {
            return log_list;
        }

        public void setLog_list(List<LogListBean> log_list) {
            this.log_list = log_list;
        }

        public static class LogListBean {
            /**
             * user_money : 9.90
             * change_type : 充值
             * change_desc : 将退货金额退还到余额
             * change_sign : +
             */

            private String user_money;
            private String change_type;
            private String change_desc;
            private String change_sign;
            private String date;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getUser_money() {
                return user_money;
            }

            public void setUser_money(String user_money) {
                this.user_money = user_money;
            }

            public String getChange_type() {
                return change_type;
            }

            public void setChange_type(String change_type) {
                this.change_type = change_type;
            }

            public String getChange_desc() {
                return change_desc;
            }

            public void setChange_desc(String change_desc) {
                this.change_desc = change_desc;
            }

            public String getChange_sign() {
                return change_sign;
            }

            public void setChange_sign(String change_sign) {
                this.change_sign = change_sign;
            }
        }
    }
}
