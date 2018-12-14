package com.lnkj.privateshop.ui.mybuy.refunds.shipping.details;


import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.easeui.domain.EaseUser;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.RetrunImageAdapter;
import com.lnkj.privateshop.chat.ChatActivity;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.entity.PhotoEntity;
import com.lnkj.privateshop.entity.RetrunDetailsBean;
import com.lnkj.privateshop.entity.ServiceEmchatBean;
import com.lnkj.privateshop.ui.LookBigImgActivity;
import com.lnkj.privateshop.ui.mybuy.myorder.orderdetailed.OrderDetailedActivity;
import com.lnkj.privateshop.ui.mybuy.refunds.shipping.ShipPingReturnActivity;
import com.lnkj.privateshop.ui.mybuy.returngoods.record.RecordActivity;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.lnkj.privateshop.view.MyGridView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 退款详情
 */
public class RetrunDetailsActivity extends BaseActivity implements ReturnDetailsContract.View {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_11)
    TextView tv11;
    @Bind(R.id.tv_order_state)
    TextView tvOrderState;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.iv_speak)
    ImageView ivSpeak;
    @Bind(R.id.tv_order_number)
    TextView tvOrderNumber;
    @Bind(R.id.tv_copy_nummber)
    TextView tvCopyNummber;
    @Bind(R.id.tv_order_time)
    TextView tvOrderTime;
    @Bind(R.id.tv_order_mode)
    TextView tvOrderMode;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_cause)
    TextView tvCause;
    @Bind(R.id.tv_message)
    TextView tvMessage;
    @Bind(R.id.tv_btn_look)
    TextView tvBtnLook;
    @Bind(R.id.btn_revocation)
    Button btnRevocation;
    @Bind(R.id.btn_former)
    Button btnFormer;
    String order_sn;
    String order_goods_id;
    String roder_status;
    @Bind(R.id.mGridview)
    MyGridView mGridVIew;
    ReturnDetailsPresenter mPresenter = new ReturnDetailsPresenter(this);
    private RetrunDetailsBean.DataBean bean;

    @Override
    public int initContentView() {
        return R.layout.activity_retrun_details;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("退款退货详情");
        order_sn = getIntent().getStringExtra("order_sn");
        order_goods_id = getIntent().getStringExtra("order_goods_id");
        roder_status = getIntent().getStringExtra("roder_status");
        mPresenter.getToken(token);

    }

    @Override
    public void initUiAndListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.ShipPingGoods(order_sn, order_goods_id);
    }

    @OnClick({R.id.img_back, R.id.iv_speak, R.id.tv_btn_look, R.id.btn_revocation, R.id.btn_former, R.id.tv_copy_nummber, R.id.iv_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.iv_speak:
                try {
                    if (bean.getEmchat() == null) {
                        ToastUtil.showToast("客服不在线");
                        return;
                    }

                    EaseUser easeUser = new EaseUser(bean.getEmchat().getEmchat_username());
                    easeUser.setAvatar(Constants.Base_IMG_URL + bean.getEmchat().getHead_pic());
                    easeUser.setNickname(bean.getEmchat().getNickname());

                    List<EaseUser> users = new ArrayList<EaseUser>();
                    users.add(easeUser);
                    DemoHelper.getInstance().updateContactList(users);

                    Intent intent1 = new Intent(this, ChatActivity.class);
                    intent1.putExtra("userId", bean.getEmchat().getEmchat_username());
                    startActivity(intent1);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast("客服不在线");
                }
                break;
            case R.id.iv_phone:
                call(bean.getShop_mobile());
                break;
            case R.id.tv_btn_look:
                Intent intent2 = new Intent(this, RecordActivity.class);
                intent2.putExtra("order_sn", order_sn);
                startActivity(intent2);
                break;
            case R.id.btn_revocation:
                if (roder_status.equals("1")) {
                    //撤销退货申请
                    CenterActionDialog dialog = new CenterActionDialog(this);
                    dialog.setActionString("撤销退货申请",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            try {
                                mPresenter.onRevocation(order_sn);
                            } catch (Exception e) {
                            }
                        }

                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();

                } else if (roder_status.equals("2")) {
                    //去发货
                    Intent intent = new Intent(this, ShipPingReturnActivity.class);
                    intent.putExtra("order_sn", order_sn);
                    startActivityForResult(intent, 20);
                } else if (roder_status.equals("3")) {
                    mPresenter.getServiceInfo();

                } else if (roder_status.equals("4")) {
                    //撤销退货申请
                    CenterActionDialog dialog = new CenterActionDialog(this);
                    dialog.setActionString("删除该申请吗？",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            try {
                                mPresenter.onDeleteOrder(order_sn);
                            } catch (Exception e) {
                            }
                        }

                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();
                    //删除

                }

                break;
            case R.id.btn_former:
                Intent intent = new Intent(this, OrderDetailedActivity.class);
                intent.putExtra("orderId", order_sn);
                intent.putExtra("type", "refund");
                startActivity(intent);
                break;
            case R.id.tv_copy_nummber:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(tvOrderNumber.getText());
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
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
    public void getShipPingData(RetrunDetailsBean beass) {
        bean = beass.getData();
        tvOrderState.setText(bean.getShop_state());
        tvShopName.setText(bean.getShop_name());
        tvOrderNumber.setText(order_sn);
        tvOrderTime.setText(bean.getAdd_time());

        tvCause.setText(bean.getReason());
        tvMessage.setText(bean.getDescription());
        tvOrderMode.setText(bean.getRefund_type());
        tvPrice.setText(bean.getAmount());
        if (roder_status.equals("1")) {
            //撤销退货申请
            btnRevocation.setText("撤销申请");
        } else if (roder_status.equals("2")) {
            //去发货
            btnRevocation.setText("去退货");

        } else if (roder_status.equals("3")) {
            //
            btnRevocation.setText("联系官方客服");


        } else if (roder_status.equals("4")) {
            //删除
            btnRevocation.setText("删除订单");
        } else if (roder_status.equals("10")) {
            //等待卖家收货
            btnRevocation.setVisibility(View.GONE);
        }
        final List<String> images = bean.getImages();
        RetrunImageAdapter adapter = new RetrunImageAdapter(images, this);
        mGridVIew.setAdapter(adapter);
        mGridVIew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RetrunDetailsActivity.this,
                        LookBigImgActivity.class);
                List<PhotoEntity> list = new ArrayList<PhotoEntity>();
                for (int j = 0; j < images.size(); j++) {
                    PhotoEntity photoEntity = new PhotoEntity();
                    if (!TextUtils.isEmpty(images.get(j))) {
                        photoEntity.setImg_url(images.get(j));
                        photoEntity.bendi = 1;
                    }
                    photoEntity.setId(i + "");
                    list.add(photoEntity);
                }
                intent.putExtra("imgBeen", (Serializable) list);
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });
    }

    @Override
    public void ShopPingGoodsSuccree() {

    }

    @Override
    public void RevocationSucreed() {
        setResult(30);
        finish();
    }

    @Override
    public void DeleteSuccreed() {
        finish();
    }

    @Override
    public void getServiceInfo(ServiceEmchatBean.DataBean bean) {
        try {

            EaseUser easeUser = new EaseUser(bean.getChat_username());
            easeUser.setAvatar(Constants.Base_IMG_URL + bean.getChat_headpic());
            easeUser.setNickname(bean.getChat_nickname());

            List<EaseUser> users = new ArrayList<EaseUser>();
            users.add(easeUser);
            DemoHelper.getInstance().updateContactList(users);
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("userId", bean.getChat_username());
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.showToast("客服不在线");
        }
    }

    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (Exception e) {
        }
    }

}
