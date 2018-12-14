package com.lnkj.privateshop.ui.mybuy.sell.order.orderdetailed;


import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import com.lnkj.privateshop.adapter.SellOrderDetaildeAdapter;
import com.lnkj.privateshop.chat.ChatActivity;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.entity.SellOrderDetaildeBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.ui.mybuy.myorder.looktransit.LookTransitActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.orderdetailed.modify.ModifyPriceActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.shipping.ShipPingActivity;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.lnkj.privateshop.view.MyListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SellOrderDetaildeActivity extends BaseActivity implements SellOrderDetaildeContract.View{
    @Bind(R.id.tv_title)
    TextView tvTitle;
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
    private String type;

    SellOrderDetaildeAdapter adapter;
    private SellOrderDetaildePresenter mPresenter = new SellOrderDetaildePresenter(this);
    private String orderId;
    private SellOrderDetaildeBean.DataBean data;


    @Override
    public int initContentView() {
        return R.layout.activity_sell_order_detailde;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        adapter=new SellOrderDetaildeAdapter(this);
        tvTitle.setText("订单详情");
//        ivPhone.setVisibility(View.GONE);
//        ivSpeak.setVisibility(View.GONE);
        mMyListView.setAdapter(adapter);
        mPresenter.getToken(token);
        orderId = getIntent().getStringExtra("orderId");
        type =getIntent().getStringExtra("type");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getOrderFromServer(orderId);
    }

    @Override
    public void initUiAndListener() {
        mMyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(data.getOrder_goods().get(i).getGoods_id());
                try {
                Intent intent = new Intent(SellOrderDetaildeActivity.this,GoodsInfoActivity.class);
                intent.putExtra("goods_id",data.getOrder_goods().get(i).getGoods_id());
                startActivity(intent);
                }catch (Exception e){

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
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void initView() {

    }
    String status ;
    String Order_id;
    String goods_pic;
    @Override
    public void getOrderSucceed(SellOrderDetaildeBean beass) {
        data = beass.getData();
        if (data ==null){
            return;
        }
        mobile = data.getMobile();
        status= data.getOrder_status();
        Order_id = data.getOrder_sn();
        if (status.equals("0")){
            tvOrderState.setText("交易取消");
            tvBtnLeftOne.setVisibility(View.GONE);
            tvBtnTwo.setVisibility(View.GONE);
            tvBtnThree.setVisibility(View.GONE);
            tvBtnThree.setText("删除订单");
        }else if (status.equals("1")){
            tvOrderState.setText("等待付款");
            tvBtnLeftOne.setVisibility(View.VISIBLE);
            tvBtnTwo.setVisibility(View.VISIBLE);
            tvBtnThree.setVisibility(View.VISIBLE);
            tvBtnThree.setText("修改价格");
            tvBtnTwo.setText("取消订单");
            tvBtnLeftOne.setText("提醒付款");
            tvBtnTwo.setTextColor(Color.parseColor("#FF7722"));
            tvBtnTwo.setBackgroundResource(R.drawable.button_bj_orange);
        }else if (status.equals("2")){
            tvOrderState.setText("等待发货");
            tvBtnLeftOne.setVisibility(View.GONE);
            tvBtnTwo.setVisibility(View.GONE);
            tvBtnThree.setVisibility(View.VISIBLE);
            tvBtnThree.setText("前往发货");
            tvBtnThree.setTextColor(Color.parseColor("#FF7722"));
            tvBtnThree.setBackgroundResource(R.drawable.button_bj_orange);

        }else if (status.equals("3")){
            tvOrderState.setText("等待收货");
            tvBtnLeftOne.setVisibility(View.GONE);
            tvBtnTwo.setVisibility(View.GONE);
            tvBtnThree.setVisibility(View.VISIBLE);
            tvBtnThree.setText("查看物流");
            tvBtnThree.setTextColor(Color.parseColor("#FF7722"));
            tvBtnThree.setBackgroundResource(R.drawable.button_bj_orange);
        }else if (status.equals("4")){
            tvOrderState.setText("交易完成");
            tvBtnLeftOne.setVisibility(View.GONE);
            tvBtnTwo.setVisibility(View.GONE);
            tvBtnThree.setVisibility(View.VISIBLE);
            tvBtnThree.setText("查看物流");
            tvBtnLeftOne.setTextColor(Color.parseColor("#FF7722"));
            tvBtnLeftOne.setBackgroundResource(R.drawable.button_bj_orange);
        }

        if (!TextUtils.isEmpty(type)){
            tvBtnLeftOne.setVisibility(View.GONE);
            tvBtnTwo.setVisibility(View.GONE);
            tvBtnThree.setVisibility(View.GONE);
        }
        tvOrderNumber.setText(data.getOrder_sn());
        tvOrderTime.setText(data.getAdd_time());
        tvPeople.setText(data.getConsignee());
        tvAddress.setText(data.getAddress());
        tvShopName.setText(data.getShop_name());
        tv_phone.setText(data.getMobile());
        tvGoodsCountTwo.setText(data.getTotal_buy_number());
        tvOrderPrice.setText("￥"+ data.getReal_pay_amount()+"元");
        if (TextUtils.isEmpty(data.getExpress_amount())|| data.getExpress_amount().equals("0.00")){
            tvFreight.setText("免运费");
        }else {
            tvFreight.setText("含运费"+ data.getExpress_amount()+"元");
        }
        if (TextUtils.isEmpty(data.getRemark())){
            tvOrderMessage.setText("没有留言");
        }else {
        tvOrderMessage.setText(data.getRemark());

        }
        adapter.addData(data.getOrder_goods());
        tvBtnLeftOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status.equals("1")) {
                mPresenter.onRemindPay(Order_id);
                }
            }
        });
        try {

        goods_pic= Constants.Base_URL + data.getOrder_goods().get(0).getGoods_img();
        }catch (Exception e){}
//        Glide
//                .with(this)
//                .load(Constants.Base_URL + data.gets)
//                .error(R.mipmap.de_photo)
//                .into(ivShopHead);
    }

    @Override
    public void okGoods() {
//        mPresenter.getOrderFromServer(orderId);
        finish();
    }
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    String mobile;
    @Override
    public void remoOrder() {
       finish();
    }
    @OnClick({R.id.img_back, R.id.iv_speak, R.id.iv_phone, R.id.tv_btn_left_one,R.id.tv_copy_address, R.id.tv_btn_two, R.id.tv_btn_three,R.id.tv_copy_nummber, R.id.tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.iv_speak:
               SellOrderDetaildeBean.DataBean.Echat echat = data.getEchat();
                if (TextUtils.isEmpty(echat.getEmchat_username())) {
                    ToastUtil.showToast("客服不在线");
                    return;
                }

                EaseUser easeUser = new EaseUser(echat.getEmchat_username());
                easeUser.setAvatar(Constants.Base_IMG_URL+echat.getHead_pic());
                easeUser.setNickname(echat.getNickname());
//                List<EaseUser> users = new ArrayList<EaseUser>();
//                users.add(easeUser);
                try {
                DemoHelper.getInstance().saveContact(easeUser);
                }catch (Exception e){
                    ToastUtil.showToast("正在加载数据，请稍候");
                }
                Intent intt = new Intent(SellOrderDetaildeActivity.this, ChatActivity.class);
                intt.putExtra("userId", echat.getEmchat_username());
                startActivity(intt);
                break;

            case R.id.iv_phone:

                call(mobile);
                break;

            case R.id.tv_btn_two:
                if (TextUtils.isEmpty(status)){
                    return;
                }
                if (status.equals("1")) {
                    //取消订单
                    CenterActionDialog dialog =   new CenterActionDialog(this);
                    dialog.setActionString("您要取消订单吗？",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            mPresenter.onDeleteOrder(Order_id,0);
                        }

                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();

                }
                break;
            case R.id.tv_btn_three:
                if (TextUtils.isEmpty(status)){
                    return;
                }
                if (status.equals("0")) {
                    //删除订单
                    CenterActionDialog dialog =   new CenterActionDialog(this);
                    dialog.setActionString("您要删除订单吗？",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            mPresenter.onDeleteOrder(Order_id,100);
                        }

                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();

                }else if (status.equals("1")){
                    //修改价格
                    Intent intent = new Intent(this,ModifyPriceActivity.class);
                    intent.putExtra("orderId",Order_id);
                    startActivityForResult(intent,20);
                }else if(status.equals("2")){
                    //前往发货
//                    Intent intent = new Intent(this,LookTransitActivity.class);
                    Intent intent = new Intent(this,ShipPingActivity.class);
                    intent.putExtra("orderId",Order_id);
                    startActivityForResult(intent,20);
                }else if (status.equals("3")){
                    //查看物流
                    Intent intent = new Intent(this,LookTransitActivity.class);
                    intent.putExtra("orderId",Order_id);
                    intent.putExtra("goods_pic",goods_pic);
                    startActivity(intent);
                }else if (status.equals("4")){
                    //查看物流
                    Intent intent = new Intent(this,LookTransitActivity.class);
                    intent.putExtra("orderId",Order_id);
                    intent.putExtra("goods_pic",goods_pic);
                    startActivity(intent);
                    //删除订单
//                    CenterActionDialog dialog =   new CenterActionDialog(this);
//                    dialog.setActionString("您要删除订单吗？",
//                            "确定",
//                            "取消");
//                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
//                        @Override
//                        public void sureAction() {
//                            mPresenter.onDeleteOrder(Order_id,100);
//                        }
//
//                        @Override
//                        public void cancelAction() {
//
//                        }
//                    });
//                    dialog.show();

                }

                break;

            case R.id.tv_copy_nummber:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(tvOrderNumber.getText());
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_address:
                ClipboardManager cm2 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm2.setText(tvAddress.getText());
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_copy_address:
                ClipboardManager cm3 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm3.setText(tvPeople.getText()+","+tv_phone.getText()+","+tvAddress.getText()+",000000");
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
