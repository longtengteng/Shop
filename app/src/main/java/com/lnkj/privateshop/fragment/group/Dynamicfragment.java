package com.lnkj.privateshop.fragment.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.DynamicragAdapter;
import com.lnkj.privateshop.entity.ChatBean;
import com.lnkj.privateshop.entity.DynamicragBean;
import com.lnkj.privateshop.ui.login.LoginActivity;
import com.lnkj.privateshop.ui.near.AddGroupActivity;
import com.lnkj.privateshop.ui.shop.shopInfo.ShopInfoActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class Dynamicfragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener, GroupContract.View {
    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;
    DynamicragAdapter adapter;
    TextView tv_published;
    private int page = 1;
    public static Dynamicfragment fragment;
    private GroupPresenter mPresenter = new GroupPresenter(this);

    public static Dynamicfragment newInstance() {
        if (fragment == null)
            fragment = new Dynamicfragment();
        return fragment;
    }


    @Override
    protected int getContentResid() {
        return R.layout.fragmetn_dynamicr;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        mpullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas = (LinearLayout) view.findViewById(R.id.layout_no_datas);
        tv_published = (TextView) view.findViewById(R.id.tv_published);
        int is_shop = PreferencesUtils.getInt(getActivity(), "is_shop");
        is_bogin = PreferencesUtils.getBoolean(getActivity(), "is_bogin");
        if (is_shop == 0) {
            tv_published.setVisibility(View.GONE);
        } else {
            int state = PreferencesUtils.getInt(getActivity(), "state", Constants.STATE_BUY);
            try {
                if (state == Constants.STATE_BUY) {
                    tv_published.setVisibility(View.GONE);
                } else {
                    tv_published.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
            }
        }
    }

    private Boolean is_bogin;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(true);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(true);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setRefreshing(true);
        mpullLoadMoreRecyclerView.setLinearLayout();
        adapter = new DynamicragAdapter(getActivity());
        mpullLoadMoreRecyclerView.setAdapter(adapter);
        mPresenter.setToken(token);
        mPresenter.getDynamicragFromService(page);
        tv_published.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int is_shop = PreferencesUtils.getInt(getActivity(), "is_shop");
                if (is_shop == 1) {
                    Intent intent = new Intent(getActivity(), AddGroupActivity.class);
                    startActivityForResult(intent, 20);
                } else {
                    ToastUtil.showToast("您还没有开店");
                }
            }
        });
        adapter.setmClickListener(new DynamicragAdapter.OrderClickListener() {
            @Override
            public void onOrderCilck(String goodsId) {
                Intent intent = new Intent(getActivity(), ShopInfoActivity.class);
                intent.putExtra("shop_id", goodsId);
                startActivity(intent);
            }

            @Override
            public void oncollectShop(int position) {
                if (is_bogin) {
                    //关注
                    pos = position;
                    mPresenter.setCollectShop(lists.get(position).getShop_id());
                } else {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }


            }
        });
    }

    private int pos;

    @Override
    public void onRefresh() {

        page = 1;
        lists.clear();
        mPresenter.getDynamicragFromService(page);

    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.getDynamicragFromService(page);
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && resultCode == 30) {
            page = 1;
            mPresenter.getDynamicragFromService(page);
        }
    }

    public void setVisibility(int state) {
        try {
            int is_shop = PreferencesUtils.getInt(getActivity(), "is_shop");
            if (is_shop == 0) {
                tv_published.setVisibility(View.GONE);
            } else {


                if (state == Constants.STATE_BUY) {
                    tv_published.setVisibility(View.GONE);
                } else {
                    tv_published.setVisibility(View.VISIBLE);
                }

            }
        } catch (Exception e) {
//        tv_published.setVisibility(View.GONE);
        }

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        mpullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void initView() {

    }

    List<DynamicragBean.DataBean> lists = new ArrayList<>();

    @Override
    public void getDynamicragSuceed(DynamicragBean beass) {
        if (page == 1) {
            lists.clear();
        }
        lists.addAll(beass.getData());
        adapter.addAllData(lists);
        if (lists.size() == 0 && page == 1) {
            layout_no_datas.setVisibility(View.VISIBLE);
            mpullLoadMoreRecyclerView.setVisibility(View.GONE);
        } else {
            layout_no_datas.setVisibility(View.GONE);
            mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void getChatFromSuceed(ChatBean beass) {

    }

    @Override
    public void SetColloectShopSuccreed() {

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getShop_id().equals(lists.get(pos).getShop_id())) {
                if (lists.get(i).getFavorite_shop() == 0) {
                    lists.get(i).setFavorite_shop(1);
                } else {
                    lists.get(i).setFavorite_shop(0);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}
