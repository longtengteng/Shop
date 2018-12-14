package com.lnkj.privateshop.ui.ease.systemnews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.NoticeAdapter;
import com.lnkj.privateshop.entity.HelpBean;
import com.lnkj.privateshop.ui.ease.systemnews.noticedetails.NoticeDetailsActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeActivity extends BaseActivity {

    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.mListView)
    ListView mListView;
    private HelpBean beans;

    @Override
    public int initContentView() {
        return R.layout.activity_notice;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("系统公告");
        Bundle bundle = getIntent().getExtras();
        beans = (HelpBean) bundle.getSerializable("list");
        NoticeAdapter adapter = new NoticeAdapter(this, beans.getData());
        mListView.setAdapter(adapter);
    }

    @Override
    public void initUiAndListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NoticeActivity.this, NoticeDetailsActivity.class);
                intent.putExtra("Article_id",beans.getData().get(i).getArticle_id());
                startActivity(intent);
            }
        });
    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
