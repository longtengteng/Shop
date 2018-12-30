package com.lnkj.privateshop.ui.mybuy.myorder.orderdetailed;


import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.easeui.domain.EaseUser;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.OrderDetaildeAdapter;
import com.lnkj.privateshop.chat.ChatActivity;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.entity.OrderDetailderBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.ui.goodscar.gopay.GoPayOrderActivity;
import com.lnkj.privateshop.ui.mybuy.myorder.commentaries.OredrCommetActivity;
import com.lnkj.privateshop.ui.mybuy.myorder.looktransit.LookTransitActivity;
import com.lnkj.privateshop.ui.mybuy.returngoods.ReturnGoodsActivity;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.lnkj.privateshop.view.CenterTiteActionDialog;
import com.lnkj.privateshop.view.MyListView;

import java.math.BigDecimal;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrderDetailedActivity extends BaseActivity implements OrderDetaildeContract.View {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    //    @Bind(R.id.tv_right_blue)
//    TextView tvRightBlue;
    @Bind(R.id.tv_order_state)
    TextView tvOrderState;
    @Bind(R.id.tv_order_number)
    TextView tvOrderNumber;
    @Bind(R.id.tv_phone)
    TextView tv_phone;

    @Bind(R.id.tv_order_time)
    TextView tvOrderTime;
    @Bind(R.id.tv_order_message)
    TextView tvOrderMessage;
    @Bind(R.id.tv_people)
    TextView tvPeople;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    //    @Bind(R.id.iv_shop_head)
//    CircleImageView ivShopHead;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.iv_speak)
    ImageView ivSpeak;
    @Bind(R.id.iv_phone)
    ImageView ivPhone;
    @Bind(R.id.mMyListView)
    MyListView mMyListView;
    @Bind(R.id.tv_goods_count_two)
    TextView tvGoodsCountTwo;
    @Bind(R.id.tv_freight)
    TextView tvFreight;
    @Bind(R.id.tv_order_price)
    TextView tvOrderPrice;
    @Bind(R.id.tv_btn_left_one)
    TextView tvBtnLeftOne;
    @Bind(R.id.tv_btn_two)
    TextView tvBtnTwo;
    @Bind(R.id.tv_btn_three)
    TextView tvBtnThree;
    OrderDetaildeAdapter adapter;
    private String type;
    private OrderDetaildePresenter mPresenter = new OrderDetaildePresenter(this);
    private String orderId;
    private List<OrderDetailderBean.DataBean.OrderGoodsBean> beans;
    private String goods_id;
    private OrderDetailderBean.DataBean data;

    @Override
    public int initContentView() {
        return R.layout.activity_order_detailed;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getOrderFromServer(orderId);
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        adapter = new OrderDetaildeAdapter(this);
        tvTitle.setText("订单详情");
//        tvRightBlue.setText("申请退款");

//        tvRightBlue.setTextColor(Color.parseColor("#ff7722"));
        mMyListView.setAdapter(adapter);
        mPresenter.getToken(token);
        orderId = getIntent().getStringExtra("orderId");
        goods_id = getIntent().getStringExtra("goods_id");
        type = getIntent().getStringExtra("type");
        adapter.setAddressClickListener(new OrderDetaildeAdapter.AddClickListener() {
            @Override
            public void onDeleteCilck(final int position) {
                CenterTiteActionDialog dialog = new CenterTiteActionDialog(OrderDetailedActivity.this);
                dialog.setActionString("确定要申请退货退款吗？", "确定", "取消", "提醒");
                dialog.setActionListener(new CenterTiteActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {
                        //确定
                        Intent intent = new Intent(OrderDetailedActivity.this, ReturnGoodsActivity.class);
                        if (beans != null) {
                            intent.putExtra("goods_id", beans.get(position).getGoods_id());
                            intent.putExtra("price", beans.get(position).getTotal_money());
                            intent.putExtra("order_goods_id", beans.get(position).getOrder_goods_id());
                            intent.putExtra("OrderNumber", orderId);
                            intent.putExtra("status", status);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();
            }
        });
        mMyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(OrderDetailedActivity.this, GoodsInfoActivity.class);
                intent.putExtra("goods_id", beans.get(i).getGoods_id());
                startActivity(intent);
            }
        });
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

    String status;
    String Order_id;
    String shop_id;
    String mobile;
    String goods_img;

    @Override
    public void getOrderSucceed(OrderDetailderBean beass) {
        data = beass.getData();
        if (data == null) {
            return;
        }
        status = data.getOrder_status();
        mobile = data.getShop_mobile();
        Order_id = data.getOrder_sn();
        shop_id = data.getShop_id();
        beans = data.getOrder_goods();
        adapter.addData(beans);
//        shop_head=data.getShop_name()
        if (status.equals("0")) {
            tvOrderState.setText("交易取消");
            tvBtnLeftOne.setVisibility(View.GONE);
            tvBtnTwo.setVisibility(View.GONE);
            tvBtnThree.setVisibility(View.GONE);
            tvBtnThree.setText("删除订单");
            adapter.setGone(false);
        } else if (status.equals("1")) {
            tvOrderState.setText("待付款");
            tvBtnLeftOne.setVisibility(View.GONE);
            tvBtnTwo.setVisibility(View.VISIBLE);
            tvBtnThree.setVisibility(View.VISIBLE);
            tvBtnThree.setText("取消订单");
            tvBtnTwo.setText("前往付款");
            tvBtnTwo.setTextColor(Color.parseColor("#FF7722"));
            tvBtnTwo.setBackgroundResource(R.drawable.button_bj_orange);
            adapter.setGone(false);
        } else if (status.equals("2")) {
            tvOrderState.setText("待发货");
//            tvRightBlue.setVisibility(View.VISIBLE);
            tvBtnLeftOne.setVisibility(View.GONE);
            tvBtnTwo.setVisibility(View.GONE);
            tvBtnThree.setVisibility(View.VISIBLE);
            tvBtnThree.setText("提醒发货");
            tvBtnThree.setTextColor(Color.parseColor("#FF7722"));
            tvBtnThree.setBackgroundResource(R.drawable.button_bj_orange);
            adapter.setGone(true);
        } else if (status.equals("3")) {
            tvOrderState.setText("待收货");
            tvBtnLeftOne.setVisibility(View.GONE);
            tvBtnTwo.setVisibility(View.VISIBLE);
            tvBtnThree.setVisibility(View.VISIBLE);
//            tvRightBlue.setVisibility(View.VISIBLE);
            tvBtnThree.setText("确认收货");
            tvBtnTwo.setText("查看物流");
            tvBtnThree.setTextColor(Color.parseColor("#FF7722"));
            tvBtnThree.setBackgroundResource(R.drawable.button_bj_orange);
            adapter.setGone(true);
        } else if (status.equals("4")) {
            tvOrderState.setText("已完成");
//            tvRightBlue.setVisibility(View.VISIBLE);
            tvBtnLeftOne.setVisibility(View.VISIBLE);
            tvBtnTwo.setVisibility(View.GONE);
            tvBtnThree.setVisibility(View.VISIBLE);
            tvBtnThree.setText("查看物流");
            tvBtnTwo.setText("再次购买");
            tvBtnLeftOne.setText("前往评价");
            tvBtnLeftOne.setTextColor(Color.parseColor("#FF7722"));
            tvBtnLeftOne.setBackgroundResource(R.drawable.button_bj_orange);
            if (data.getIs_comment().equals("0")) {
                tvBtnLeftOne.setVisibility(View.VISIBLE);
            } else {
                tvBtnLeftOne.setVisibility(View.GONE);
            }
            adapter.setGone(true);
        } else {
            adapter.setGone(false);
        }
        if (TextUtils.isEmpty(data.getRemark())) {

            tvOrderMessage.setText("没有留言");
        } else {

            tvOrderMessage.setText(data.getRemark());
        }
        tv_phone.setText(data.getMobile());
        tvOrderNumber.setText(data.getOrder_sn());
        tvOrderTime.setText(data.getAdd_time());
        tvPeople.setText(data.getConsignee());
        tvAddress.setText(data.getAddress());
        tvShopName.setText(data.getShop_name());
        tvGoodsCountTwo.setText(data.getTotal_buy_number());
        tvOrderPrice.setText("￥" + data.getReal_pay_amount() + "元");
        if (TextUtils.isEmpty(data.getExpress_amount()) || data.getExpress_amount().equals("0.00")) {
            tvFreight.setText("免运费");
        } else {
            tvFreight.setText("含运费" + data.getExpress_amount() + "元");
        }
        try {
            goods_img = Constants.Base_IMG_URL + data.getOrder_goods().get(0).getGoods_img();
        } catch (Exception e) {
        }
        prive = data.getReal_pay_amount();

        if (!TextUtils.isEmpty(type)) {
            tvBtnLeftOne.setVisibility(View.GONE);
            tvBtnTwo.setVisibility(View.GONE);
            tvBtnThree.setVisibility(View.GONE);
        }
    }

    String prive;

    @Override
    public void okGoods() {
//        mPresenter.getOrderFromServer(Order_id);
        setResult(30);
        finish();
    }

    @Override
    public void remoOrder() {
        setResult(30);
        finish();
    }

    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @OnClick({R.id.img_back, R.id.iv_speak, R.id.tv_copy_address, R.id.iv_phone, R.id.tv_btn_left_one, R.id.tv_btn_two, R.id.tv_btn_three, R.id.tv_copy_nummber, R.id.tv_right_blue, R.id.tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.iv_speak:
                OrderDetailderBean.DataBean.Echat echat = data.getEchat();
                if (TextUtils.isEmpty(echat.getEmchat_username())) {
                    ToastUtil.showToast("客服不在线");
                    return;
                }
//                List<EaseUser> users = new ArrayList<EaseUser>();
//                users.add(easeUser);

                EaseUser easeUser = new EaseUser(echat.getEmchat_username());
                easeUser.setAvatar(Constants.Base_IMG_URL + echat.getHead_pic());
                easeUser.setNickname(echat.getNickname());
                try {
                    DemoHelper.getInstance().saveContact(easeUser);
                } catch (Exception e) {
//                    ToastUtil.showToast("正在加载数据，请稍候");
                }

                Intent intt = new Intent(OrderDetailedActivity.this, ChatActivity.class);
                intt.putExtra("userId", echat.getEmchat_username());
                startActivity(intt);
                break;
            case R.id.iv_phone:
                call(mobile);
                break;
            case R.id.tv_btn_left_one:
                if (TextUtils.isEmpty(status)) {
                    return;
                }

                if (status.equals("4")) {
                    //前往评价
                    Intent intent = new Intent(this, OredrCommetActivity.class);
                    intent.putExtra("orderId", Order_id);
                    intent.putExtra("shop_id", shop_id);
                    startActivityForResult(intent, 20);
                }

                break;
            case R.id.tv_btn_two:
                if (TextUtils.isEmpty(status)) {
                    return;
                }
                if (status.equals("1")) {

                    //前往付款
                    Intent intent = new Intent(this, GoPayOrderActivity.class);
                    intent.putExtra("order_sn", Order_id);
                    intent.putExtra("total_goods_num", data.getTotal_buy_number());
                    intent.putExtra("total_amount", data.getReal_pay_amount());
                    intent.putExtra("express", data.getExpress_amount() == null ? "0.00" : data.getExpress_amount());
                    //商品总价的错误情况
                    try {
                        double f = Double.parseDouble(data.getReal_pay_amount()) - Double.parseDouble(data.getExpress_amount() == null ? "0.00" : data.getExpress_amount());
                        BigDecimal bg = new BigDecimal(f);
                        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        intent.putExtra("total_goods_amount", f1 + "");
                    } catch (Error e) {
                    }


                    startActivity(intent);
                } else if (status.equals("3")) {
                    //查看物流
                    Intent intent = new Intent(this, LookTransitActivity.class);
                    intent.putExtra("orderId", Order_id);
                    try {
                        intent.putExtra("goods_pic", goods_img);
                    } catch (Exception e) {
                    }
                    startActivity(intent);
                } else if (status.equals("4")) {
                    //再次购买
                    Intent intent = new Intent(this, GoodsInfoActivity.class);
                    intent.putExtra("goods_id", goods_id);
                    startActivity(intent);
                }
                break;
            case R.id.tv_btn_three:
                if (TextUtils.isEmpty(status)) {
                    return;
                }
                if (status.equals("0") || status.equals("4")) {
                    //查看物流
                    Intent intent = new Intent(this, LookTransitActivity.class);
                    intent.putExtra("orderId", Order_id);
                    try {
                        intent.putExtra("goods_pic", goods_img);
                    } catch (Exception e) {
                    }
                    startActivity(intent);

//                    //删除订单
//                    CenterActionDialog dialog =   new CenterActionDialog(this);
//                    dialog.setActionString("您要删除订单吗？",
//                            "确定",
//                            "取消");
//                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
//                        @Override
//                        public void sureAction() {
//                            mPresenter.onDeleteOrder(Order_id);
//                        }
//
//                        @Override
//                        public void cancelAction() {
//
//                        }
//                    });
//                    dialog.show();

                } else if (status.equals("2")) {
                    //提醒发货
                    mPresenter.onRemindDelivery(Order_id);
                } else if (status.equals("3")) {
                    //确认收货
                    CenterActionDialog dialog = new CenterActionDialog(this);
                    dialog.setActionString("确定收货吗？",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            mPresenter.onOkGoods(Order_id);
                        }

                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();

                } else if (status.equals("1")) {
                    //取消订单
                    CenterActionDialog dialog = new CenterActionDialog(this);
                    dialog.setActionString("确定取消订单吗？",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            mPresenter.onCancelorder(Order_id);
                        }

                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();
                }

                break;
            case R.id.tv_copy_nummber:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(tvOrderNumber.getText());
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_right_blue:

                break;
            case R.id.tv_address:
                ClipboardManager cm2 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm2.setText(tvAddress.getText());
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && resultCode == 30) {
            mPresenter.getOrderFromServer(orderId);
        }
    }
}
