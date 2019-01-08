package com.lnkj.privateshop.fragment.article.articledetail;

import com.lnkj.privateshop.BasePresenter;
import com.lnkj.privateshop.BaseView;
import com.lnkj.privateshop.entity.ArticleBean;
import com.lnkj.privateshop.entity.ArticleCateBean;
import com.lnkj.privateshop.entity.ArticleContentBean;

public class ArticleDetailContract {
    //界面处理逻辑
    interface View extends BaseView {

        void toLoging();

        void PullLoadMoreComplete();

        void succeed(ArticleContentBean beass);

    }

    //业务处理逻辑
    interface Presenter extends BasePresenter<View> {
        void getToken(String token);

        void getDataFromServer(String article_id);

        void setToken(String token);

        void getSize();
    }
}
