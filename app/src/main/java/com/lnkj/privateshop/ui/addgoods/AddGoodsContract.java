package com.lnkj.privateshop.ui.addgoods;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.AddGoodsBean;
import com.lnkj.privateshop.entity.ColorBean2;
import com.lnkj.privateshop.entity.GoodsAttriBean;
import com.lnkj.privateshop.entity.GoodsEditBean;
import com.lnkj.privateshop.entity.SizeBean;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;

/**
 * Created by WRJ on 2016/8/30.
 */
public class AddGoodsContract {

    interface View extends BaseView {

        void initView();
        void showLoading();//显示加载框
        void hideLoading();//隐藏加载框
        void getColorSucceed(ColorBean2 beass);
        void getSizeSucceed(SizeBean beass);
        void getClassSucceed(AddGoodsBean beass);

        void putNewColorSucceed(String etcolor,String id,String img);
        void putNewSizeSucceed(String etcolor,String id);
        void getAttriSucceed(GoodsAttriBean beass);
        void addGoodsSucceed();
        void getGoodsSuccreed(GoodsEditBean beass);
        String getListColor();
        void deleteImgSuccreed(int i);
    }

    interface Presenter extends BasePresenter<View> {
        void initView();
        void login(String phone, String pwd);
        void getToken(String token);
        void getColorFromServer();
        void putNewColor( String etcolor);
        void putNewSize( String size);
        void getSizeFromServer();
        void getClassFromServer();
        void getAttriFromServer(String id);
        void AddGoods(String title,String content,String goodsprice,String goodspickprice,String  color,String size,String
                cat_id,String jsonText,String weight,String p, ArrayList<ImageItem> images,boolean mswitch);

        void editGoods(String title, String content, String goodsprice, String goodspickprice, String  color, String size,
                       String cat_id, String jsonText, String weight, String p, ArrayList<ImageItem> images, String goods_id,boolean mswitch,String del_img_id);
        void getGoods(String goodsid);
        void deleteImg(String imgid,int i);
    }
}
