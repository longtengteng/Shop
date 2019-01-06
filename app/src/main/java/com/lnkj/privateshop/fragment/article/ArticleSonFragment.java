package com.lnkj.privateshop.fragment.article;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.LimitSaleAdapter;
import com.lnkj.privateshop.entity.ArticleBean;
import com.lnkj.privateshop.entity.ArticleCateBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.ui.limitsalelist.LimitSaleActivity;
import com.lnkj.privateshop.ui.limitsalelist.LimitSalePresenter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArticleSonFragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener,ArticleContract.View {

    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.ll_nodata)
    LinearLayout llNodata;

    private int page = 1;
    private ArticlePresenter mPresenter = new ArticlePresenter(this);
    private ArticleAdapter adapter;
    LinearLayout layout_no_datas;
    private List<ArticleBean.DataBean> articleBeanList = new ArrayList<>();
    String article_category_id;

    @Override
    protected int getContentResid() {
        return R.layout.fragment_articleson;
    }

    public static ArticleSonFragment newInstance(String article_category_id) {
        Bundle bundle = new Bundle();
        bundle.putString("article_category_id", article_category_id);
        ArticleSonFragment articleSonFragment = new ArticleSonFragment();
        articleSonFragment.setArguments(bundle);
        return articleSonFragment;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        ButterKnife.bind(this, view);
        //设置是否可以下拉刷新
        article_category_id=getArguments().getString("article_category_id");
        pullLoadMoreRecyclerView.setPullRefreshEnable(true);
        pullLoadMoreRecyclerView.setRefreshing(true);
        pullLoadMoreRecyclerView.setPushRefreshEnable(true);
        pullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener((PullLoadMoreRecyclerView.PullLoadMoreListener) this);
        pullLoadMoreRecyclerView.setGridLayout(1);


        adapter = new ArticleAdapter(articleBeanList);
        pullLoadMoreRecyclerView.setAdapter(adapter);
        mPresenter.setToken(token);
        mPresenter.getDataFromServer(page,article_category_id );

    }

    @Override
    public void toLoging() {

    }

    @Override
    public void PullLoadMoreComplete() {

    }

    @Override
    public void succeed(ArticleBean beass) {
        if (page == 1) {
            articleBeanList.clear();
        }
        articleBeanList.addAll(beass.getData());
        adapter.setNewData(articleBeanList);
        if (articleBeanList.size() == 0 && page == 1) {
            layout_no_datas.setVisibility(View.VISIBLE);
            pullLoadMoreRecyclerView.setVisibility(View.GONE);
        } else {
            layout_no_datas.setVisibility(View.GONE);
            pullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getArticleCategory(ArticleCateBean articleCateBean) {

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
    public void onRefresh() {
        page = 1;
        articleBeanList.clear();
        mPresenter.getDataFromServer(page,article_category_id);
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.getDataFromServer(page,article_category_id);
    }
}
