package com.lnkj.privateshop.fragment.user;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.domain.EaseUser;
import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.chat.ChatActivity;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.entity.GoodsCategoryBean;
import com.lnkj.privateshop.entity.ServiceEmchatBean;
import com.lnkj.privateshop.entity.UseInfoBean;
import com.lnkj.privateshop.ui.mybuy.address.AddressActivity;
import com.lnkj.privateshop.ui.mybuy.favoritegoods.GoodsCollectionActivity;
import com.lnkj.privateshop.ui.mybuy.followshops.FollowActivity;
import com.lnkj.privateshop.ui.mybuy.footprint.FootPrintActivity;
import com.lnkj.privateshop.ui.mybuy.help.HelpActivity;
import com.lnkj.privateshop.ui.mybuy.mredund.MyRedundActivity;
import com.lnkj.privateshop.ui.mybuy.myorder.MyOrderActivity;
import com.lnkj.privateshop.ui.mybuy.refunds.RefundsActivity;
import com.lnkj.privateshop.ui.mybuy.userinfo.EditUserActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MyFragment extends BaseFragment implements MyContract.View {

    //    @Bind(R.id.tv_switch)
//    TextView tvSwitch;
//    @Bind(R.id.img_set_up)
//    ImageView imgSetUp;
//    @Bind(R.id.img_massage)
//    ImageView imgMassage;
    @Bind(R.id.img_head)
    CircleImageView imgHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_vip)
    TextView tvVip;
    @Bind(R.id.rl_order)
    RelativeLayout rlOrder;
    @Bind(R.id.tv_payment_count)
    TextView tvPaymentCount;
    @Bind(R.id.ll_payment)
    LinearLayout llPayment;
    @Bind(R.id.tv_delivery_count)
    TextView tvDeliveryCount;
    @Bind(R.id.ll_delivery)
    LinearLayout llDelivery;
    @Bind(R.id.tv_togoods_count)
    TextView tvTogoodsCount;
    @Bind(R.id.ll_togoods)
    LinearLayout llTogoods;
    @Bind(R.id.tv_comments_count)
    TextView tvCommentsCount;
    @Bind(R.id.ll_comments)
    LinearLayout llComments;
    @Bind(R.id.tv_refund_count)
    TextView tvRefundCount;
    @Bind(R.id.ll_refund)
    LinearLayout llRefund;
    @Bind(R.id.rl_collection)
    RelativeLayout rlCollection;
    @Bind(R.id.rl_focus)
    RelativeLayout rlFocus;
    @Bind(R.id.rl_foot)
    RelativeLayout rlFoot;
    @Bind(R.id.rl_remain)
    RelativeLayout rlRemain;
    @Bind(R.id.rl_address)
    RelativeLayout rlAddress;
    @Bind(R.id.rl_help)
    RelativeLayout rlHelp;
    @Bind(R.id.rl_service)
    RelativeLayout rlService;
    private MyPresenter myPresenter = new MyPresenter(this);
    @Bind(R.id.rl_edit)
    RelativeLayout rl_edit;
    @Override
    protected int getContentResid() {
        return R.layout.fragment_my;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        ButterKnife.bind(this, view);
        myPresenter.setToken(token);
    }
    Boolean is_bogin;
    @Override
    public void onResume() {
        super.onResume();
      is_bogin =PreferencesUtils.getBoolean(getContext(), "is_bogin");
        if (is_bogin){
        myPresenter.getDataFromService();
        myPresenter.getOrderData();
        }else {
            tvName.setText("注册/登录");
            tvVip.setVisibility(View.GONE);
//            rl_edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                }
//            });
        }
    }

    @Override
    public void initView() {

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
    public void updateView(int id) {

    }

    @Override
    public void upateFragmentData(int type) {

    }

    @Override
    public void liftData(GoodsCategoryBean beans) {

    }

    UseInfoBean.DataBean databean;

    @Override
    public void getUserInfoSuccreed(UseInfoBean beans) {
        databean = beans.getData();
        tvName.setText(beans.getData().getNickname());
        tvVip.setText(beans.getData().getUser_level());
        Glide
                .with(getActivity())
                .load(Constants.Base_URL + beans.getData().getHead_pic())
                .error(R.mipmap.de_photo)
                .into(imgHead);

        PreferencesUtils.putString(getActivity(),"head_pic",beans.getData().getHead_pic());
        PreferencesUtils.putString(getActivity(),"nickname",beans.getData().getNickname());

        DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(beans.getData().getNickname());
        DemoHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(Constants.Base_IMG_URL+beans.getData().getHead_pic());
    }

    @Override
    public void getOrderDataSuccreed(int status_1, int status_2, int status_3, String status_4, String status_5) {
        tvPaymentCount.setText(status_1 + "");
        tvDeliveryCount.setText(status_2 + "");
        tvTogoodsCount.setText(status_3 + "");
        tvCommentsCount.setText(status_4);
        tvRefundCount.setText(status_5);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rl_order, R.id.ll_payment, R.id.ll_delivery, R.id.ll_togoods, R.id.ll_comments,
            R.id.ll_refund, R.id.rl_collection, R.id.rl_focus, R.id.rl_foot, R.id.rl_remain,
            R.id.rl_address, R.id.rl_help, R.id.rl_service, R.id.img_head,R.id.rl_edit})
    public void onViewClicked(View view) {
        if (!is_bogin){
            ToastUtil.showToast("您还没有登录，请去登录");
            return;
        }

        Intent intent;
        switch (view.getId()) {
//            case R.id.tv_switch:
//                break;
//            case R.id.img_set_up:
//                break;
//            case R.id.img_massage:
//                break;
            case R.id.ll_payment:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                intent.putExtra("type", "payment");
                startActivity(intent);
                break;
            case R.id.ll_delivery:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                intent.putExtra("type", "delivery");
                startActivity(intent);
                break;
            case R.id.ll_togoods:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                intent.putExtra("type", "togoods");
                startActivity(intent);
                break;
            case R.id.ll_comments:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                intent.putExtra("type", "comments");
                startActivity(intent);
                break;
            case R.id.ll_refund: //退货退款
                intent = new Intent(getActivity(), RefundsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_order:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_collection:
                GoodsCollectionActivity.startActivity(getActivity());
                break;
            case R.id.rl_focus:
                intent = new Intent(getActivity(), FollowActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_foot:
                intent = new Intent(getActivity(), FootPrintActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_remain:
                intent = new Intent(getActivity(), MyRedundActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_address:
                intent = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_help:
                intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_service:
                myPresenter.getServiceInfo();
                break;
            case R.id.img_head:
                try {
                    intent = new Intent(getActivity(), EditUserActivity.class);
                    intent.putExtra("Head_pic", databean.getHead_pic());
                    intent.putExtra("Nickname", databean.getNickname());
                    intent.putExtra("Register_time", databean.getRegister_time());
                    intent.putExtra("User_level", databean.getUser_level());
                    startActivity(intent);
                }catch (Exception e){}
                break;
            case R.id.rl_edit:
                try {
                    intent = new Intent(getActivity(), EditUserActivity.class);
                    intent.putExtra("Head_pic", databean.getHead_pic());
                    intent.putExtra("Nickname", databean.getNickname());
                    intent.putExtra("Register_time", databean.getRegister_time());
                    intent.putExtra("User_level", databean.getUser_level());
                    startActivity(intent);
                }catch (Exception e){
                }
                break;

        }
    }

    @Override
    public void getServiceInfo(final ServiceEmchatBean.DataBean bean) {
        try {
//            List<EaseUser> users = new ArrayList<EaseUser>();
            EaseUser easeUser = new EaseUser(bean.getChat_username());
            easeUser.setAvatar(Constants.Base_IMG_URL+bean.getChat_headpic());
            easeUser.setNickname(bean.getChat_nickname());
//            users.add(easeUser);
            DemoHelper.getInstance().saveContact(easeUser);
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            intent.putExtra("userId", bean.getChat_username());
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.showToast("客服不在线");
        }
    }
}
