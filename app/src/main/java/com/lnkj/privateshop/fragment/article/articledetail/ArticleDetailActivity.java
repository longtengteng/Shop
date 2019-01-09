package com.lnkj.privateshop.fragment.article.articledetail;

import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.ArticleContentBean;
import com.lnkj.privateshop.ui.limitsalelist.LimitSalePresenter;

import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;

/*文章详情*/
public class ArticleDetailActivity extends BaseActivity implements ArticleDetailContract.View {
    String article_id;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_content)
    ImageView iv_content;
    @Bind(R.id.tv_content)
    TextView tv_content;
    @Bind(R.id.tv_zan_count)
    TextView tv_zan_count;
    @Bind(R.id.tv_comment_count)
    TextView tv_comment_count;
    @Bind(R.id.iv_zan)
    ImageView iv_zan;

    private ArticleDetailPresenter mPresenter = new ArticleDetailPresenter(this);

    @Override
    public int initContentView() {

        return R.layout.activity_articledetail;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
    }

    @Override
    public void initUiAndListener() {
        article_id = getIntent().getStringExtra("article_id");
        struct();
        mPresenter.getDataFromServer(article_id);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void toLoging() {

    }

    @Override
    public void PullLoadMoreComplete() {

    }

    @Override
    public void succeed(ArticleContentBean beass) {

        Glide.with(this).load(Constants.Base_URL + beass.getData().getThumb_img())
                .error(R.mipmap.default_image)
                .placeholder(R.mipmap.default_image)
                .into(iv_content);
        tv_comment_count.setText(beass.getData().getComment_count());
        tv_zan_count.setText(beass.getData().getLike_count());
        tvTitle.setText(beass.getData().getTitle());
        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置可滚动
        tv_content.setMovementMethod(LinkMovementMethod.getInstance());//设置超链接可以打开网页
        tv_content.setText(Html.fromHtml(beass.getData().getContent(), imgGetter, null));

    }


    //这里面的resource就是fromhtml函数的第一个参数里面的含有的url
    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Log.i("RG", "source---?>>>" + source);
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);
                Log.i("RG", "url---?>>>" + url);
                drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            Log.i("RG", "url---?>>>" + url);
            return drawable;
        }
    };

    public static void struct() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork() // or
                // .detectAll()
                // for
                // all
                // detectable
                // problems
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
                .penaltyLog() // 打印logcat
                .penaltyDeath().build());
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
