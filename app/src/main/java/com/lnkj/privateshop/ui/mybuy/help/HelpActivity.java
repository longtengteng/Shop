package com.lnkj.privateshop.ui.mybuy.help;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.HelpAdapter;
import com.lnkj.privateshop.entity.HelpBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HelpActivity extends BaseActivity implements HelpContract.View {

    HelpAdapter adapter;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.mListView)
    ListView mListView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    HelpPresenter mPresenter = new HelpPresenter(this,this);
    @Override
    public int initContentView() {
        return R.layout.activity_help;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("帮助中心");
        mPresenter.getToken(token);
        mPresenter.getPutFromServer();

    }

    @Override
    public void initUiAndListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String id =  lists.get(i).getArticle_id();
            Intent intent = new Intent(HelpActivity.this,HelpDetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
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
    public void finisht() {

    }
    List<HelpBean.DataBean> lists;
    @Override
    public void succree( HelpBean beass) {
        lists= beass.getData();
        adapter=new HelpAdapter(this,lists);
        mListView.setAdapter(adapter);
    }
}
