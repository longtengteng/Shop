package com.lnkj.privateshop.fragment.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.StartShopBean;
import com.lnkj.privateshop.entity.UseInfoBean;
import com.lnkj.privateshop.fragment.shop.ShopCommentPresenter;
import com.lnkj.privateshop.fragment.user.sell.selluserFragment;
import com.lnkj.privateshop.ui.ease.EaseConversationListActivity;
import com.lnkj.privateshop.ui.login.LoginActivity;
import com.lnkj.privateshop.ui.mybuy.openshop.OpenShopFactory2Activity;
import com.lnkj.privateshop.ui.mybuy.openshop.OpenShopNetActivity;
import com.lnkj.privateshop.ui.mybuy.openshop.OpenShopRealityActivity;
import com.lnkj.privateshop.ui.mybuy.sttting.SettingActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.tv_switch;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class UserFragment extends BaseFragment implements UserContract.View {
    MyFragment myFragment;// 我的
    selluserFragment sellFragment;
    @Bind(R.id.rl_bj)
    RelativeLayout rlBj;
    @Bind(tv_switch)
    TextView tvSwitch;
    @Bind(R.id.img_set_up)
    ImageView imgSetUp;
    @Bind(R.id.img_massage)
    ImageView imgMassage;
    @Bind(R.id.fl_content)
    FrameLayout flContent;
    private PopupWindows popunWin;
    public int index = 0;
    private View prentview;
    private int is_shop;
    @Bind(R.id.tv_count)
    TextView tv_count;

    private UserPresenter mPresenter = new UserPresenter(this);

    @Override
    protected int getContentResid() {
        return R.layout.fragment_user;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        prentview = view;
        ButterKnife.bind(this, view);
        mPresenter.setToken(token);

    }

    Boolean is_bogin;

    @Override
    public void onResume() {
        super.onResume();
        if (UnreadMsgCount > 0) {
            tv_count.setVisibility(View.VISIBLE);
            tv_count.setText(UnreadMsgCount + "");
        } else {
            tv_count.setVisibility(View.GONE);
        }

        is_bogin = PreferencesUtils.getBoolean(getContext(), "is_bogin");
        if (is_bogin) {
            mPresenter.getDataFromService();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    protected void loadDatas() {
        super.loadDatas();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        myFragment = new MyFragment();
        sellFragment = new selluserFragment();
        fragmentTransaction.add(R.id.fl_content, myFragment);
        fragmentTransaction.add(R.id.fl_content, sellFragment);
        is_shop = PreferencesUtils.getInt(getActivity(), "has_shop");
        if (is_shop == 0) {
            tvSwitch.setText("免费开店");
            index = -1;
            fragmentTransaction.hide(sellFragment);
            fragmentTransaction.show(myFragment);
            rlBj.setBackgroundColor(Color.parseColor("#FF7722"));
//            tvSwitch.setText("切换为卖家");
            fragmentTransaction.commit();
        } else if (is_shop == -1) {
            tvSwitch.setText("审核中");
            index = -1;
            fragmentTransaction.hide(sellFragment);
            fragmentTransaction.show(myFragment);
            rlBj.setBackgroundColor(Color.parseColor("#FF7722"));
//            tvSwitch.setText("切换为卖家");
            fragmentTransaction.commit();
        } else {
            int state = PreferencesUtils.getInt(getActivity(), "state", Constants.STATE_BUY);
            if (state == Constants.STATE_SELLER) {
                fragmentTransaction.hide(myFragment);
                fragmentTransaction.show(sellFragment);
                rlBj.setBackgroundColor(Color.parseColor("#27a2f8"));
                tvSwitch.setText("切换为买家");

            } else {
                fragmentTransaction.hide(sellFragment);
                fragmentTransaction.show(myFragment);
                rlBj.setBackgroundColor(Color.parseColor("#FF7722"));
                tvSwitch.setText("切换为卖家");
            }
            fragmentTransaction.commit();
        }

        Boolean is_bogin = PreferencesUtils.getBoolean(getContext(), "is_bogin");
        if (!is_bogin) {
            tvSwitch.setText("免费开店");
            fragmentTransaction.hide(sellFragment);
            fragmentTransaction.show(myFragment);
            rlBj.setBackgroundColor(Color.parseColor("#FF7722"));
        }
    }


    @OnClick({tv_switch, R.id.img_set_up, R.id.img_massage})
    public void onViewClicked(View view) {

        Boolean is_bogin = PreferencesUtils.getBoolean(getContext(), "is_bogin");
        if (!is_bogin) {
            ToastUtil.showToast("您还没有登录，请去登录");
            startActivity(new Intent(getActivity(), LoginActivity.class));
            return;
        }

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case tv_switch:
                mOnSwitckClickListener.onSwitckClick(index);
                if (index == 0) {
                    index = 1;
                    fragmentTransaction.hide(myFragment);
                    fragmentTransaction.show(sellFragment);
                    rlBj.setBackgroundColor(Color.parseColor("#27a2f8"));
                    tvSwitch.setText("切换为买家");
                    PreferencesUtils.putInt(getActivity(), "state", Constants.STATE_SELLER);
                    fragmentTransaction.commit();
                } else if (index == 1) {
                    index = 0;
                    fragmentTransaction.hide(sellFragment);
                    fragmentTransaction.show(myFragment);
                    rlBj.setBackgroundColor(Color.parseColor("#FF7722"));
                    tvSwitch.setText("切换为卖家");
                    PreferencesUtils.putInt(getActivity(), "state", Constants.STATE_BUY);
                    fragmentTransaction.commit();
                } else {
                    mPresenter.apply_start_shop();
                }
                break;
            case R.id.img_set_up:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.img_massage:
                startActivity(new Intent(getActivity(), EaseConversationListActivity.class));
                break;
        }
    }

    @Override
    public void initView() {

    }

    List<StartShopBean.DataBean> startBeanList = new ArrayList<>();

    @Override
    public void apply_start_shop(StartShopBean startShopBean) {
        startBeanList.clear();
        startBeanList.addAll(startShopBean.getData());
        popunWin = new PopupWindows(getActivity(), prentview);
    }

    @Override
    public void getUserInfoSuccreed(UseInfoBean beans) {
        PreferencesUtils.putInt(getActivity(), "has_shop", beans.getData().getHas_shop());
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        is_shop = PreferencesUtils.getInt(getActivity(), "has_shop", 0);
        if (is_shop == 0) {
            tvSwitch.setText("免费开店");
            index = -1;
            fragmentTransaction.hide(sellFragment);
            fragmentTransaction.show(myFragment);
            rlBj.setBackgroundColor(Color.parseColor("#FF7722"));
        } else {
            int state = PreferencesUtils.getInt(getActivity(), "state", Constants.STATE_BUY);
            if (state == Constants.STATE_BUY) {
                fragmentTransaction.hide(sellFragment);
                fragmentTransaction.show(myFragment);
                rlBj.setBackgroundColor(Color.parseColor("#FF7722"));
                tvSwitch.setText("切换为卖家");
                index = 1;
            } else {
                index = 0;
                fragmentTransaction.hide(myFragment);
                fragmentTransaction.show(sellFragment);
                rlBj.setBackgroundColor(Color.parseColor("#27a2f8"));
                tvSwitch.setText("切换为买家");
            }
        }
        fragmentTransaction.commit();
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

    public class PopupWindows extends PopupWindow {
        public PopupWindows(Context mContext, View parent) {
            View view = View.inflate(mContext, R.layout.item_popupwindow, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.fade_ins));
            LinearLayout ll_popup = (LinearLayout) view
                    .findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.push_bottom_in_2));

            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);


            setBackgroundDrawable(new BitmapDrawable());
//            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();
            StartShopAdapter adapter;
            RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_start);
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new StartShopAdapter(startBeanList);
            adapter.bindToRecyclerView(rv);
            adapter.setAutoLoadMoreSize(1);
            adapter.disableLoadMoreIfNotFullPage(rv);
         /*   Button bt1 = (Button) view
                    .findViewById(R.id.item_popupwindows_camera);*/
   /*         Button bt2 = (Button) view
                    .findViewById(R.id.item_popupwindows_Photo);*/
            Button bt3 = (Button) view
                    .findViewById(R.id.item_popupwindows_cancel);
            //    Button bt4 = (Button) view.findViewById(R.id.item_popupwindows_video);
            TextView tv_dis = (TextView) view.findViewById(R.id.tv_dis);
            tv_dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(getActivity(), OpenShopNetActivity.class);
                    intent.putExtra("shop_type", (position + 1) + "");
                    startActivity(intent);
                    dismiss();
                }
            });
            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });

        }
    }

    private OnSwitckClickListener mOnSwitckClickListener = null;

    public interface OnSwitckClickListener {
        void onSwitckClick(int index);
    }

    public void setOnSwitckClickListener(OnSwitckClickListener mOnSwitckClickListener) {
        this.mOnSwitckClickListener = mOnSwitckClickListener;
    }
}
