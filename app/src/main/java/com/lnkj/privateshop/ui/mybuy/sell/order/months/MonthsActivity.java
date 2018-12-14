package com.lnkj.privateshop.ui.mybuy.sell.order.months;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**+
 * 本月
 */

public class MonthsActivity extends BaseActivity implements MonthsContract.View {


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.tv_mones)
    TextView tvMones;
    @Bind(R.id.tv_look)
    TextView tvLook;
    @Bind(R.id.tv_main_mones)
    TextView tvMainMones;
    @Bind(R.id.tv_income)
    TextView tvIncome;
    @Bind(R.id.tv_consum)
    TextView tvConsum;
    private MonthsPresenter mPresenter = new MonthsPresenter(this);

    @Override
    public int initContentView() {
        return R.layout.activity_months;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("本月实况");
        mPresenter.getToken(token);
     //没有接口
        mPresenter.getDataFromServer();
    }

    @Override
    public void initUiAndListener() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void initView() {

    }

    @Override
    public void showFragment(int position) {

    }

    @Override
    public void showLoginUi() {

    }

    @Override
    public void updatePraiseView(int dif, int position) {

    }

    @Override
    public void setData(String mones, String mainmones, String income,String date) {
        tvLook.setText(date);
        tvMainMones.setText(mones);
        tvConsum.setText(mainmones);
        tvIncome.setText(income);
    }


    @Override
    public void PullLoadMoreComplete() {

    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
