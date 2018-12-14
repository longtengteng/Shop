package com.lnkj.privateshop.ui.ease;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.easeui.domain.EaseUser;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.MyApplication;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.chat.ChatActivity;
import com.lnkj.privateshop.ease.domain.db.UserDao;
import com.lnkj.privateshop.entity.HelpBean;
import com.lnkj.privateshop.entity.ServiceEmchatBean;
import com.lnkj.privateshop.ui.ease.systemnews.NoticeActivity;
import com.lnkj.privateshop.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EaseConversationListActivity extends BaseActivity implements EaseConversationListContract.View {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.fl_content)
    FrameLayout flContent;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.ll_system)
    LinearLayout ll_system;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_server)
    TextView tv_server;
    @Bind(R.id.tv_title_t)
    TextView tv_title;
    EaseConversationListPresenter mPresenter = new EaseConversationListPresenter(this);
    private ConversationListFragment mFragmet;

    @Override
    public int initContentView() {
        return R.layout.activity_ease_conversation_list;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mFragmet = new ConversationListFragment();
        mPresenter.getToken(token);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content, mFragmet);
        fragmentTransaction.commit();
        tvTitle.setText("消息列表");
        mPresenter.getDataFromServer();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void initUiAndListener() {
        ll_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent intent = new Intent(EaseConversationListActivity.this, NoticeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list",beans);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        tv_server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        mPresenter.getServiceInfo();
            }
        });
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
    HelpBean beans;
    @Override
    public void getOrderData(HelpBean beans) {
        this.beans=beans;
        try {

      if (beans==null||beans.getData().size()==0){
          ll_system.setVisibility(View.GONE);
      }else {

          ll_system.setVisibility(View.VISIBLE);
          tv_name.setText(beans.getData().get(0).getTitle());
          tv_title.setText("系统公告");
          tv_time.setText(beans.getData().get(0).getAddtime());
      }
        }catch (Exception e){}

    }

    @Override
    public void getServiceInfo(ServiceEmchatBean.DataBean bean) {
        try {

            EaseUser easeUser = new EaseUser(bean.getChat_username());
            easeUser.setAvatar(Constants.Base_IMG_URL+bean.getChat_headpic());
            easeUser.setNickname(bean.getChat_nickname());

            UserDao dao = new UserDao(MyApplication.getApplication());
            List<EaseUser> users = new ArrayList<EaseUser>();
            users.add(easeUser);
            dao.saveContactList(users);

            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("userId", bean.getChat_username());
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.showToast("客服不在线");
        }
    }
}
