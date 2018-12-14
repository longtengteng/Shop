package com.lnkj.privateshop.ui.seachgoods;


import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.AddriGoodsBean;
import com.lnkj.privateshop.entity.ClassGoodsBean;
import com.lnkj.privateshop.entity.SeachGoodsBean;
import com.lnkj.privateshop.entity.SizeBean;

/**
 * Created by zjh on 2016/5/11.
 */
public interface SeachGoodsContract {

    //界面处理逻辑
    interface View extends BaseView {

        void toLoging();
        void PullLoadMoreComplete();
        void succeed( SeachGoodsBean beass);
        void classSucceed(ClassGoodsBean beass);
        void AttriSucceed(AddriGoodsBean beass );
        void sizeSucceed(SizeBean beass);

    }
//  map.put("cat_id", id);
//        map.put("attr", attr);
//        map.put("sort", sorts);
//        map.put("min_price", min_price);
//        map.put("max_price", max_price);
//        map.put("pack_num", pack_num);
//        map.put("p", page);
    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);
        void getDataFromServer( String cat_id,String attr,String sorts,String min_price,String max_price,
                                String pack_num,int page,String shop_type,String goods_size,String keywords);
        void getClassGoods();
        void setToken(String token);
        void getDataAttri(String id);
        void getSize();

    }
}
