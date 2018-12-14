package com.lnkj.privateshop;

/**
 * Created by sll on 2016/3/9.
 */
public interface BaseView {
    void onEmpty();
    void onNetError();
    void showLoading();
    void hideLoading();
}
