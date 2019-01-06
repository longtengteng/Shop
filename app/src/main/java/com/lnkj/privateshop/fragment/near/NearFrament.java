package com.lnkj.privateshop.fragment.near;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.OrderViewPagerAdapter;
import com.lnkj.privateshop.fragment.group.Chatfragment;
import com.lnkj.privateshop.fragment.group.Dynamicfragment;
import com.lnkj.privateshop.ui.ease.EaseConversationListActivity;
import com.lnkj.privateshop.ui.login.LoginActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class NearFrament extends BaseFragment {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.tv_count)
    TextView tv_count;
    private List<Fragment> fragmentList;
    private List<String> titeList;
    private OrderViewPagerAdapter adapter;
    Boolean is_bogin;

    @Override
    protected int getContentResid() {
        return R.layout.fragment_near;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        ButterKnife.bind(this, view);

    }

    public void setVisibility(int state) {
        Dynamicfragment.newInstance().setVisibility(state);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (UnreadMsgCount > 0) {
            tv_count.setVisibility(View.VISIBLE);
            tv_count.setText(UnreadMsgCount + "");
        } else {
            tv_count.setVisibility(View.GONE);
        }
    }

    @Override
    protected void loadDatas() {
        super.loadDatas();
        titeList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titeList.add("最新动态");
        titeList.add("聊天室");
        fragmentList.add(Dynamicfragment.newInstance());
        fragmentList.add(Chatfragment.newInstance());

//        System.out.println(titeList.size());
//        System.out.println(fragmentList.size());

        adapter = new OrderViewPagerAdapter(getChildFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        adapter.bind(fragmentList, titeList);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is_bogin = PreferencesUtils.getBoolean(getActivity(), "is_bogin");
                if (is_bogin) {
                    startActivity(new Intent(getActivity(), EaseConversationListActivity.class));
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
