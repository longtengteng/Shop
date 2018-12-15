package com.lnkj.privateshop.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.HomeAdverAdapter;
import com.lnkj.privateshop.adapter.OrderViewPagerAdapter;
import com.lnkj.privateshop.entity.AdvertisingBean;
import com.lnkj.privateshop.entity.BannerBean;
import com.lnkj.privateshop.entity.HotBannerBean;
import com.lnkj.privateshop.entity.LimitedFavourBean;
import com.lnkj.privateshop.entity.OrderWholeSaleBean;
import com.lnkj.privateshop.fragment.home.goodslist.FollowFragment;
import com.lnkj.privateshop.fragment.home.goodslist.GoodsLilstFragment;
import com.lnkj.privateshop.ui.WebActivity;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.ui.shop.shopInfo.ShopInfoActivity;
import com.lnkj.privateshop.ui.timelinit.TimeGoodsActivity;
import com.lnkj.privateshop.ui.wholesale.WholesaleActivity;
import com.lnkj.privateshop.utils.CountDownUtil;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    HomePresenter mPresenter = new HomePresenter(this);
    @Bind(R.id.mCb)
    ConvenientBanner mCb;
    @Bind(R.id.mCb_hot)
    ConvenientBanner mCb_hot;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    GoodsLilstFragment mFragment;

    @Bind(R.id.myListView)
    MyListView myListView;
    @Bind(R.id.iv_time)
    ImageView ivTime;
    @Bind(R.id.tv_time_h)
    TextView tvTimeH;
    @Bind(R.id.tv_time_m)
    TextView tvTimeM;
    @Bind(R.id.tv_time_s)
    TextView tvTimeS;
    @Bind(R.id.iv_wholesale)
    ImageView ivWholesale;
    @Bind(R.id.id_appbarlayout)
    AppBarLayout appBarLayout;
    //    @Bind(R.id.layout_swipe_refresh)
//    SwipeRefreshLayout swipLayout;
    private List<Fragment> fragmentList;
    private List<String> titeList;
    private OrderViewPagerAdapter adapter;
    private List<AdvertisingBean.DataBean> list;
    private long time;

    @Override
    protected int getContentResid() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        ButterKnife.bind(this, view);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset >= 0) {
//                    swipLayout.setEnabled(true);
                } else {
//                    swipLayout.setEnabled(false);
                }
            }
        });
//        swipLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                GoodsLilstFragment  fragment = (GoodsLilstFragment) fragmentList.get(viewpager.getCurrentItem());
//
//                fragment.onRefresh();
//                new Handler().postDelayed(new Runnable(){
//                    public void run() {
//                        swipLayout.setRefreshing(false);
//                    }
//                }, 10000);
//            }
//        });

        mPresenter.getToken(token);
        mPresenter.getBannerFromServer();
        mPresenter.getTime();
        mPresenter.getWholesale();
        mPresenter.getAdvertising();
        titeList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titeList.add("推荐产品");
        titeList.add("上新产品");
        titeList.add("一件代发");
        titeList.add("定制产品");

        for (int i = 0; i < titeList.size(); i++) {
            mFragment = GoodsLilstFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            mFragment.setArguments(bundle);
            fragmentList.add(mFragment);
        }
        titeList.add("关注");
        fragmentList.add(FollowFragment.newInstance());

        adapter = new OrderViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        adapter.bind(fragmentList, titeList);
    }

    @OnClick({R.id.iv_time, R.id.iv_wholesale})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_time:
                if (time < 1000) {
                    ToastUtil.showToast("暂无特惠商品");
                } else {
                    Intent intent = new Intent(getActivity(), TimeGoodsActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.iv_wholesale:
                Intent intent2 = new Intent(getActivity(), WholesaleActivity.class);
                startActivity(intent2);
                break;


        }
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void initView() {

    }

    private List<String> imgurllist;
    List<BannerBean.DataBean> databeans;

    @Override
    public void getBannerSucceed(BannerBean beass) {
        databeans = beass.getData();
        imgurllist = new ArrayList<>();
        for (int i = 0; i < beass.getData().size(); i++) {
            imgurllist.add(Constants.Base_IMG_URL + databeans.get(i).getContent());
        }
        mCb.setPages(new CBViewHolderCreator<ImageViewHolder>() {
            @Override
            public ImageViewHolder createHolder() {
                return new ImageViewHolder();
            }
        }, imgurllist)
                .setPageIndicator(new int[]{R.drawable.ponit_normal, R.drawable.point_select}) //设置两个点作为指示器
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//设置指示器的方向水平  居中
                .startTurning(3000)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        try {
                            if (databeans.get(position).getType().equals("5")) {
                                if (!TextUtils.isEmpty(databeans.get(position).getItem_id())) {
                                    Intent intent = new Intent(getActivity(), ShopInfoActivity.class);
                                    intent.putExtra("shop_id", databeans.get(position).getItem_id());
                                    startActivity(intent);

                                }
                            } else if (databeans.get(position).getType().equals("6")) {
                                if (!TextUtils.isEmpty(databeans.get(position).getItem_id())) {

                                    Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                                    intent.putExtra("goods_id", databeans.get(position).getItem_id());
                                    startActivity(intent);
                                }
                            } else if (databeans.get(position).getType().equals("0")) {
                                Intent intent = new Intent(getActivity(), WebActivity.class);
                                intent.putExtra("title", databeans.get(position).getTitle());
                                intent.putExtra("url", databeans.get(position).getLink_url());
                                startActivity(intent);
                            }
                        } catch (Exception e) {

                        }
                    }
                })
                .setCanLoop(true);
    }

    private List<String> imgurllist_hot;
    List<HotBannerBean.DataBean> databeans_hot;

    @Override
    public void gethotBannerSucceed(HotBannerBean hotBannerBean) {
        databeans_hot = hotBannerBean.getData();
        imgurllist_hot = new ArrayList<>();
        for (int i = 0; i < hotBannerBean.getData().size(); i++) {
            imgurllist_hot.add(Constants.Base_IMG_URL + databeans_hot.get(i).getContent());
        }
        mCb_hot.setPages(new CBViewHolderCreator<ImageViewHolder>() {
            @Override
            public ImageViewHolder createHolder() {
                return new ImageViewHolder();
            }
        }, imgurllist_hot)
                //   .setPageIndicator(new int[]{R.drawable.ponit_normal, R.drawable.point_select}) //设置两个点作为指示器
                //   .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//设置指示器的方向水平  居中
                .startTurning(3000)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        try {
                            if (databeans_hot.get(position).getType().equals("5")) {
                                if (!TextUtils.isEmpty(databeans_hot.get(position).getItem_id())) {
                                    Intent intent = new Intent(getActivity(), ShopInfoActivity.class);
                                    intent.putExtra("shop_id", databeans_hot.get(position).getItem_id());
                                    startActivity(intent);

                                }
                            } else if (databeans_hot.get(position).getType().equals("6")) {
                                if (!TextUtils.isEmpty(databeans_hot.get(position).getItem_id())) {

                                    Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                                    intent.putExtra("goods_id", databeans_hot.get(position).getItem_id());
                                    startActivity(intent);
                                }
                            } else if (databeans_hot.get(position).getType().equals("0")) {
                                Intent intent = new Intent(getActivity(), WebActivity.class);
                                intent.putExtra("title", databeans_hot.get(position).getTitle());
                                intent.putExtra("url", databeans_hot.get(position).getLink_url());
                                startActivity(intent);
                            }
                        } catch (Exception e) {

                        }
                    }
                })
                .setCanLoop(true);
    }


    @Override
    public void okGoods() {

    }

    @Override
    public void getTimeSuccreed(LimitedFavourBean beass) {
        LimitedFavourBean.DataBean beas = beass.getData();
        Glide.with(getActivity()).load(Constants.Base_URL + beas.getAct_img())
                .error(R.mipmap.bg_img)
                .placeholder(R.mipmap.bg_img)
                .into(ivTime);

        time = Long.parseLong(beas.getEnd_time()) * 1000 - System.currentTimeMillis();
        CountDownUtil cdu = new CountDownUtil(time, 1000 * 60 * 60,
                tvTimeH, 1);
        cdu.start();
        CountDownUtil cdu2 = new CountDownUtil(time, 1000 * 60,
                tvTimeM, 2);
        cdu2.start();
        CountDownUtil cdu3 = new CountDownUtil(time, 1000,
                tvTimeS, 3);
        cdu3.start();

    }

    @Override
    public void getWholesaleSuccreed(OrderWholeSaleBean beass) {
        try {

            Glide.with(getActivity()).load(Constants.Base_URL + beass.getData().get(0).getContent())
                    .error(R.mipmap.de_photo)
                    .thumbnail(0.1f)
                    .placeholder(R.mipmap.de_photo)
                    .into(ivWholesale);
        } catch (Exception e) {
        }
    }

    @Override
    public void getAdvertisingSuccreed(AdvertisingBean beass) {
        list = beass.getData();
        HomeAdverAdapter adapter = new HomeAdverAdapter(getActivity(), list);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                try {

                    if (list.get(position).getType().equals("5")) {
                        Intent intent = new Intent(getActivity(), ShopInfoActivity.class);
                        intent.putExtra("shop_id", list.get(position).getItem_id());
                        startActivity(intent);
                    } else if (list.get(position).getType().equals("6")) {
                        Intent intent = new Intent(getActivity(), GoodsInfoActivity.class);
                        intent.putExtra("goods_id", list.get(position).getItem_id());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        intent.putExtra("title", list.get(position).getTitle());
                        intent.putExtra("url", list.get(position).getLink_url());
                        startActivity(intent);
                    }
                } catch (Exception e) {
                }
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    public class ImageViewHolder implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(getActivity())
                    .load(data)
                    .placeholder(new ColorDrawable(Color.parseColor("#cccccc")))
                    .into(imageView);
        }

    }
}
