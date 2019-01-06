package com.lnkj.privateshop.fragment.article;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.ArticleBean;

import java.util.List;

public class ArticleAdapter extends BaseQuickAdapter<ArticleBean.DataBean, BaseViewHolder> {

    public ArticleAdapter(@Nullable List<ArticleBean.DataBean> data) {
        super(R.layout.item_article, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DataBean item) {

        LinearLayout ll_article = helper.getView(R.id.ll_article);
        if (item.getDisplay().equals("0")) {
            ll_article.setVisibility(View.GONE);
        } else {
            ll_article.setVisibility(View.VISIBLE);

            Glide.with(mContext).load(Constants.Base_URL + item.getThumb_img())
                    .error(R.mipmap.bg_img)
                    .placeholder(R.mipmap.bg_img)
                    .into((ImageView) helper.getView(R.id.iv_artcle));
            helper.setText(R.id.tv_describe, item.getIntroduce());
            helper.setText(R.id.tv_title,item.getTitle());

        }


    }
}
