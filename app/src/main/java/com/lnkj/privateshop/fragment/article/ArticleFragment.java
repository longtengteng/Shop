package com.lnkj.privateshop.fragment.article;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.OrderViewPagerAdapter;
import com.lnkj.privateshop.entity.ArticleBean;
import com.lnkj.privateshop.entity.ArticleCateBean;
import com.lnkj.privateshop.fragment.group.Dynamicfragment;
import com.lnkj.privateshop.ui.limitsalelist.LimitSalePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * 文章模块，新增功能
 * */
public class ArticleFragment extends BaseFragment implements ArticleContract.View {

    @Bind(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.img_back)
    ImageView img_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    private List<Fragment> fragmentList;
    private String[] titeList;

    private MyPagerAdapter mAdapter;
    private ArticlePresenter mPresenter = new ArticlePresenter(this);

    @Override
    protected int getContentResid() {
        return R.layout.fragment_article;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titeList[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }

    @Override
    protected void init(View view) {
        super.init(view);
        ButterKnife.bind(this, view);
        img_back.setVisibility(View.INVISIBLE);
        tv_title.setText("圈子");
        mPresenter.setToken(token);
        mPresenter.getArticleCategory();
    }

    public void setVisibility(int state) {
        Dynamicfragment.newInstance().setVisibility(state);
    }

    @Override
    public void toLoging() {

    }

    @Override
    public void PullLoadMoreComplete() {

    }

    @Override
    public void succeed(ArticleBean beass) {

    }

    @Override
    public void getArticleCategory(ArticleCateBean articleCateBean) {
        titeList = new String[]{};
        fragmentList = new ArrayList<>();
        List<ArticleCateBean.DataBean> cateList = new ArrayList<>();
        cateList = articleCateBean.getData();
        for (int i = 0; i < cateList.size(); i++) {
            titeList = Arrays.copyOf(titeList, titeList.length + 1);
            titeList[titeList.length - 1] = cateList.get(i).getName();
            fragmentList.add(ArticleSonFragment.newInstance(cateList.get(i).getArticle_category_id()));
        }
        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        viewpager.setAdapter(mAdapter);
        tabLayout.setViewPager(viewpager);


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
}
