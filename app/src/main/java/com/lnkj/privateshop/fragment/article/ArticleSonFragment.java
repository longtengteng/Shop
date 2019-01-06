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

import butterknife.Bind;

public class ArticleSonFragment extends BaseFragment implements ArticleContract.View {

    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.ll_nodata)
    LinearLayout llNodata;

    private int page = 1;
    private ArticlePresenter mPresenter = new ArticlePresenter(this);
    private ArticleAdapter adapter;
    LinearLayout layout_no_datas;
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
