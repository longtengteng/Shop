package com.lnkj.privateshop.fragment.user.sell;

import android.content.Intent;
import android.text.TextUtils;
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
import com.lnkj.privateshop.entity.SellUserBean;
import com.lnkj.privateshop.entity.ServiceEmchatBean;
import com.lnkj.privateshop.ui.mybuy.help.HelpActivity;
import com.lnkj.privateshop.ui.mybuy.openshop.OpenShopNetActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.SellOrderActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.appraiseadmin.CommentAdminActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.carriage.CarriageActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.goods.SellGoodsActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.money.MoneyActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.months.MonthsActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.refunds.SellReturnActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.shopdata.EntityShopActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.shopdata.ShopFactoryActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.shopdata.ShopNetActivity;
import com.lnkj.privateshop.ui.shop.shopInfo.ShopInfoActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.lnkj.privateshop.R.id.rl_yunfei;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class selluserFragment extends BaseFragment implements selluserContract.View {
    @Bind(R.id.img_head)
    CircleImageView imgHead;
    @Bind(R.id.tv_name)
    TextView tvName;
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
    @Bind(R.id.tv_refund_count)
    TextView tvRefundCount;
    @Bind(R.id.ll_refund)
    LinearLayout llRefund;
    @Bind(R.id.rl_goods)
    RelativeLayout rlGoods;
    @Bind(R.id.rl_months)
    RelativeLayout rlMonths;
    @Bind(R.id.rl_funds)
    RelativeLayout rlFunds;
    @Bind(R.id.rl_appraise)
    RelativeLayout rlAppraise;
    @Bind(R.id.rl_address)
    RelativeLayout rlAddress;
    @Bind(R.id.rl_help)
    RelativeLayout rlHelp;
    @Bind(R.id.rl_service)
    RelativeLayout rlService;
    @Bind(rl_yunfei)
    RelativeLayout rlYunfei;
    @Bind(R.id.tv_pay_bond)
    TextView tv_pay_bond;

    selluserPresenter mPresenter = new selluserPresenter(this);

    @Override
    protected int getContentResid() {
        return R.layout.fragment_sell_user;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        ButterKnife.bind(this, view);
        mPresenter.setToken(token);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getDataFromService();
        mPresenter.getOrderData();

    }

    @OnClick({R.id.rl_order, R.id.rl_goods, R.id.rl_months, R.id.rl_funds, R.id.rl_appraise, R.id.rl_address, R.id.rl_help,
            R.id.rl_service, rl_yunfei, R.id.ll_refund, R.id.go_shop, R.id.ll_payment, R.id.ll_delivery, R.id.ll_togoods})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_togoods:
                intent = new Intent(getActivity(), SellOrderActivity.class);
                intent.putExtra("type", "togoods");
                startActivity(intent);
                break;
            case R.id.ll_delivery:
                intent = new Intent(getActivity(), SellOrderActivity.class);
                intent.putExtra("type", "delivery");
                startActivity(intent);
                break;
            case R.id.ll_payment:
                intent = new Intent(getActivity(), SellOrderActivity.class);
                intent.putExtra("type", "payment");
                startActivity(intent);
                break;
            case R.id.rl_order:
                intent = new Intent(getActivity(), SellOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_goods:
                intent = new Intent(getActivity(), SellGoodsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_months:
                intent = new Intent(getActivity(), MonthsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_funds:
                intent = new Intent(getActivity(), MoneyActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_appraise:
                intent = new Intent(getActivity(), CommentAdminActivity.class);
                startActivity(intent);
                break;
            case R.id.go_shop:
                String shopid = PreferencesUtils.getString(getActivity(), "shop_id");
                intent = new Intent(getActivity(), ShopInfoActivity.class);
                intent.putExtra("shop_id", shopid);
                intent.putExtra("type", "selluser");
                startActivity(intent);
                break;
            case R.id.rl_address:
                if (databean.getUrl_type().equals("2")) {
                    //修改店铺信息
                    intent = new Intent(getActivity(), OpenShopNetActivity.class);
                    intent.putExtra("editShop", "selluserFragment");
                    startActivity(intent);
                  /*  if (shop_type.equals("3")) { //网批店

                    } else if (shop_type.equals("2")) {//工厂店
                        intent = new Intent(getActivity(), ShopFactoryActivity.class);
                        intent.putExtra("shop_id", PreferencesUtils.getString(getActivity(), "shop_id"));
                        startActivity(intent);
                    } else if (shop_type.equals("1")) {//实体店

                        intent = new Intent(getActivity(), EntityShopActivity.class);
                        intent.putExtra("shop_id", PreferencesUtils.getString(getActivity(), "shop_id"));
                        startActivity(intent);
                    }*/
                } else if (databean.getUrl_type().equals("0")) {
                    intent = new Intent(getActivity(), com.lnkj.privateshop.ui.mybuy.openshop.money.MoneyActivity.class);
                    intent.putExtra("shop_id", PreferencesUtils.getString(getActivity(), "shop_id"));
                    startActivity(intent);
                } else if (databean.getUrl_type().equals("3")) { //审核失败
                    intent = new Intent(getActivity(), com.lnkj.privateshop.ui.mybuy.openshop.money.MoneyActivity.class);
                    intent.putExtra("shop_id", PreferencesUtils.getString(getActivity(), "shop_id"));
                    startActivity(intent);
                }
                break;
            case R.id.rl_help://帮助
                intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_service:
                mPresenter.getServiceInfo();
                break;
            case rl_yunfei: //运费模版
                intent = new Intent(getActivity(), CarriageActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_refund://退货
                intent = new Intent(getActivity(), SellReturnActivity.class);
                startActivity(intent);
                break;
        }
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
    public void updateView(int id) {

    }

    @Override
    public void upateFragmentData(int type) {

    }

    @Override
    public void liftData(GoodsCategoryBean beans) {

    }

    private int index = 0;
    SellUserBean.DataBean databean;
    String shop_type = "";

    @Override
    public void getUserInfoSuccreed(SellUserBean beans) {
        databean = beans.getData();
        tvName.setText(beans.getData().getShop_name());
        Glide
                .with(getActivity())
                .load(Constants.Base_URL + beans.getData().getShop_logo())
                .error(R.mipmap.bg_img)
                .placeholder(R.mipmap.bg_img)
                .into(imgHead);
        index = databean.getIs_pay_bond();
        shop_type = databean.getShop_type();

        if (databean.getUrl_type().equals("0")) {
            tv_pay_bond.setText("缴纳保证金");
        } else if (databean.getUrl_type().equals("1")) {
            tv_pay_bond.setText("审核中");
        } else if (databean.getUrl_type().equals("2")) {
            tv_pay_bond.setText("");
        } else if (databean.getUrl_type().equals("3")) {
            tv_pay_bond.setText("审核拒绝");
        }


        if (databean.getIs_set_template() == 1) {
            rlYunfei.setVisibility(View.GONE);

        }
    }

    @Override
    public void getOrderDataSuccreed(int status_1, int status_2, int status_3, String status_5) {
        tvPaymentCount.setText(status_1 + "");
        tvDeliveryCount.setText(status_2 + "");
        tvTogoodsCount.setText(status_3 + "");

        tvRefundCount.setText(status_5);
    }

    @Override
    public void getServiceInfo(final ServiceEmchatBean.DataBean bean) {
        try {
            if (TextUtils.isEmpty(bean.getChat_username())) {
                ToastUtil.showToast("客服不在线");
                return;
            }
            EaseUser easeUser = new EaseUser(bean.getChat_username());
            easeUser.setAvatar(Constants.Base_IMG_URL + bean.getChat_headpic());
            easeUser.setNickname(bean.getChat_nickname());

//            List<EaseUser> users = new ArrayList<EaseUser>();
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
