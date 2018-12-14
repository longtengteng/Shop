package com.lnkj.privateshop.ui.mybuy.returngoods.record;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.RecordAdapter;
import com.lnkj.privateshop.entity.TalkRecordBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity extends BaseActivity implements RecordContract.View {

    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_11)
    TextView tv11;
    @Bind(R.id.mListView)
    ListView mListView;
    @Bind(R.id.layout_no_datas)
    LinearLayout layout_no_datas;
    RecordPresenter mPresenter = new RecordPresenter(this);
    @Override
    public int initContentView() {
        return R.layout.activity_record;
    }

    @Override
    public void initInjector() {
        String order_sn = getIntent().getStringExtra("order_sn");
        ButterKnife.bind(this);
        tvTitle.setText("查看协商记录");
        mPresenter.getToken(token);
        mPresenter.getPutFromServer(order_sn);
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void succree(TalkRecordBean beass) {
        List<TalkRecordBean.DataBean>  lists = beass.getData();
        RecordAdapter adapter = new RecordAdapter(this,lists);
        mListView.setAdapter(adapter);
        if (lists!=null&&lists.size()!=0){
            layout_no_datas.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
        }else {
            layout_no_datas.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
