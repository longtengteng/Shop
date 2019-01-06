package com.lnkj.privateshop.fragment.article;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ArticleBean;
import com.lnkj.privateshop.entity.ArticleCateBean;

public class ArticleSonContract {
    //界面处理逻辑
    interface View extends BaseView {

        void toLoging();

        void PullLoadMoreComplete();

        void succeed(ArticleBean beass);

        void getArticleCategory(ArticleCateBean articleCateBean);


    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);

        void getDataFromServer(int page, String article_category_id);

        void getArticleCategory();

        void setToken(String token);

        void getSize();
    }
}
