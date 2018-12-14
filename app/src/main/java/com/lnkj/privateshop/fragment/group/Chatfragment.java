package com.lnkj.privateshop.fragment.group;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.hyphenate.easeui.domain.EaseUser;
import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.ChatAdapter;
import com.lnkj.privateshop.chat.ChatActivity;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.entity.ChatBean;
import com.lnkj.privateshop.entity.DynamicragBean;
import com.lnkj.privateshop.ui.login.LoginActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.ProgreesDialog;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class Chatfragment extends BaseFragment implements GroupContract.View, PullLoadMoreRecyclerView.PullLoadMoreListener {

    @Bind(R.id.layout_no_datas)
    LinearLayout layoutNoDatas;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    ChatAdapter adapter;
    private GroupPresenter mPresenter = new GroupPresenter(this);
    private int page = 1;

    public static Chatfragment newInstance() {
        Chatfragment fragment = new Chatfragment();
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        ButterKnife.bind(this, view);
        mPresenter.setToken(token);
        mPresenter.getChatFromService(page);
        mpullLoadMoreRecyclerView.setPullRefreshEnable(false);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(false);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setRefreshing(true);
        mpullLoadMoreRecyclerView.setLinearLayout();
        adapter = new ChatAdapter(getActivity());
        mpullLoadMoreRecyclerView.setAdapter(adapter);
    }

    Boolean is_bogin;

    @Override
    protected void loadDatas() {
        super.loadDatas();
        is_bogin = PreferencesUtils.getBoolean(getActivity(), "is_bogin");

        adapter.setmClickListener(new ChatAdapter.OrderClickListener() {
            @Override
            public void onOrderCilck(String goodsId, int position) {
                if (is_bogin) {

                    startActivity(new Intent(getActivity(), ChatActivity.class).putExtra("chatType", 3).
                            putExtra("userId", goodsId));
                } else {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

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
        ProgreesDialog.ProgeesDialogShow();
    }

    @Override
    public void hideLoading() {
        ProgreesDialog.ProgeesDialogDiss();
    }

    @Override
    public void initView() {

    }

    @Override
    public void getDynamicragSuceed(DynamicragBean beass) {

    }

    ChatBean beass;


    @Override
    public void getChatFromSuceed(ChatBean beass) {
        this.beass = beass;
        mpullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        adapter.addAllData(beass.getData());

//        List<EaseUser> users = new ArrayList<>();
        Map<String, EaseUser> aContactList = new HashMap<>();
        for (int i = 0; i < beass.getData().size(); i++) {
            EaseUser easeUser = new EaseUser(beass.getData().get(i).getRoom_id());
            easeUser.setAvatar(Constants.Base_IMG_URL + beass.getData().get(i).getLitpic());
            easeUser.setNickname(beass.getData().get(i).getTitle());
            easeUser.setNick(beass.getData().get(i).getTitle());
            aContactList.put(beass.getData().get(i).getRoom_id(),easeUser);
//            users.add(easeUser);
        }
            DemoHelper.getInstance().setContactList(aContactList);
//        UserDao dao = new UserDao(MyApplication.getApplication());
//        dao.saveContactList(users);
//        dao.saveContact(easeUser);
    }

    @Override
    public void SetColloectShopSuccreed() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
