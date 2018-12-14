package com.lnkj.privateshop.entity;

/**
 * 作者：赵林 on 2017/10/25 0025.
 */

public class TeturnPriceBean {

    /**
     * data : {"all_amount":"0.00"}
     * info : 退款总额
     * status : 1
     */

    private DataBean data;
    private String info;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * all_amount : 0.00
         */

        private String all_amount;

        public String getAll_amount() {
            return all_amount;
        }

        public void setAll_amount(String all_amount) {
            this.all_amount = all_amount;
        }
    }
}
