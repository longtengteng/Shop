package com.lnkj.privateshop.fragment.home.goodslist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.HomeGoodsListAdapter;
import com.lnkj.privateshop.entity.AdvertisingBean;
import com.lnkj.privateshop.entity.GoodsToListBean;
import com.lnkj.privateshop.entity.HomeGoodsListbean;
import com.lnkj.privateshop.listener.EndLessOnScrollListener;
import com.lnkj.privateshop.ui.WebActivity;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.ui.shop.shopInfo.ShopInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class GoodsLilstFragment extends BaseFragment implements GoodsListContract.View {
    private GoodsListPresenter mPresenter = new GoodsListPresenter(this);
    private int index;
    int p = 1;
    RecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;
    HomeGoodsListAdapter adapter;
    //    private ScrollView mscrolloview;
    String cat_id;

    public static GoodsLilstFragment newInstance() {
        GoodsLilstFragment fragment = new GoodsLilstFragment();
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.fragmetn_godslist;
    }

    @Override
    protected void init(View view) {

//        mscrolloview = (ScrollView) view.findViewById(R.id.mscrolloview);
        mpullLoadMoreRecyclerView = (RecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas = (LinearLayout) view.findViewById(R.id.layout_no_datas);
        super.init(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        index = getArguments().getInt("index");
        cat_id = getArguments().getString("cat_id");
        mPresenter.getToken(token);
        GridLayoutManager gManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.getItemViewType(position) == HomeGoodsListAdapter.ITEM_TYPE_AFVERTIS) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        mpullLoadMoreRecyclerView.setLayoutManager(gManager);
        adapter = new HomeGoodsListAdapter(getActivity());
        mpullLoadMoreRecyclerView.setAdapter(adapter);
        //  mPresenter.getAdvertising();
        mPresenter.getDataFromServer(p, cat_id);
        adapter.setOnItemClickListener(new HomeGoodsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (beans.get(position).getType() == 1) {
                    Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                    intent.putExtra("goods_id", beans.get(position).getGoods_id());
                    intent.putExtra("from_shop_id", beans.get(position).getFrom_shop_id());
                    startActivity(intent);
                } else {
                    try {
                        if (beans.get(position).getTypet().equals("5")) {
                            Intent intent = new Intent(getActivity(), ShopInfoActivity.class);
                            intent.putExtra("shop_id", beans.get(position).getItem_id());
                            startActivity(intent);
                        } else if (beans.get(position).getTypet().equals("6")) {
                            Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                            intent.putExtra("from_shop_id", beans.get(position).getFrom_shop_id());
                            intent.putExtra("goods_id", beans.get(position).getItem_id());
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getActivity(), WebActivity.class);
                            intent.putExtra("title", beans.get(position).getTitle());
                            intent.putExtra("url", beans.get(position).getLink_url());
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                    }

                }
            }
        });
        mpullLoadMoreRecyclerView.addOnScrollListener(new EndLessOnScrollListener(gManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        });
    }

    private void loadMoreData() {
        p++;
        mPresenter.getDataFromServer(p, cat_id);
    }


    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void showLoading() {
//        ProgreesDialog.ProgeesDialogShow();
    }

    @Override
    public void hideLoading() {
//        ProgreesDialog.ProgeesDialogDiss();
//        mpullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void initView() {

    }

    //    List<HomeGoodsListbean.DataBean> lists = new ArrayList<>();
    List<AdvertisingBean.DataBean> list = new ArrayList<>();
    private List<GoodsToListBean> beans = new ArrayList<>();
    int n = 0;

    @Override
    public void getGoodsListSucceed(HomeGoodsListbean beass) {
        if (p == 1) {
            beans.clear();
            n = 0;
        }
        for (int i = 1; i < beass.getData().size() + 1; i++) {
            GoodsToListBean bean = new GoodsToListBean();
            bean.setShop_price(beass.getData().get(i - 1).getShop_price());
            bean.setGoods_id(beass.getData().get(i - 1).getGoods_id());
            bean.setGoods_img(beass.getData().get(i - 1).getGoods_img());
            bean.setGoods_name(beass.getData().get(i - 1).getGoods_name());
            bean.setPack_price(beass.getData().get(i - 1).getPack_price());
            bean.setFrom_shop_id(beass.getData().get(i - 1).getFrom_shop_id());
            bean.setType(1);
            beans.add(bean);
            if (i != 0 && i % 8 == 0) {
                GoodsToListBean bean2 = new GoodsToListBean();
                bean2.setAd_id(list.get(n).getAd_id());
                bean2.setPosition_id(list.get(n).getPosition_id());
                bean2.setLink_url(list.get(n).getLink_url());
                bean2.setContent(list.get(n).getContent());
                bean2.setTitle(list.get(n).getTitle());
                bean2.setType(0);
                bean2.setTypet(list.get(n).getType());
                bean2.setItem_id(list.get(n).getItem_id());
                beans.add(bean2);
                n++;
            }
        }
        adapter.addAllData(beans);
        if (beans.size() == 0 && p == 1) {
            mpullLoadMoreRecyclerView.setBackgroundColor(Color.parseColor("#00000000"));
            layout_no_datas.setVisibility(View.VISIBLE);
        } else {
            mpullLoadMoreRecyclerView.setBackgroundColor(Color.parseColor("#ffffff"));
            layout_no_datas.setVisibility(View.GONE);
        }
    }

    @Override
    public void okGoods() {

    }


    @Override
    public void getAdvertisingSuccreed(AdvertisingBean beass) {
//        adapter.addAllDataList();
        list.addAll(beass.getData());
        //  mPresenter.getDataFromServer(p, cat_id);
    }

    //    @Override
    public void onRefresh() {
        beans.clear();
        mPresenter.getDataFromServer(1, cat_id);
    }
//
//    @Override
//    public void onLoadMore() {
//        mPresenter.getDataFromServer(p++,index+1);
//    }
}
