package com.lnkj.privateshop.ui.mybuy.sell.order.refunds.details;


import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.lnkj.privateshop.ui.LookBigImgActivity;
import com.lnkj.privateshop.ui.mybuy.returngoods.record.RecordActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.orderdetailed.SellOrderDetaildeActivity;
import com.lnkj.privateshop.ui.mybuy.sttting.paypwd.ChagePayPwdActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.lnkj.privateshop.view.MyGridView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.tv_cause;

public class SellRetrunDetailsActivity extends BaseActivity implements SellReturnDetailsContract.View{
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
    @Bind(tv_cause)
    TextView tvCause;
    @Bind(R.id.tv_message)
    TextView tvMessage;
    @Bind(R.id.tv_btn_look)
    TextView tvBtnLook;
    @Bind(R.id.btn_former)
    Button btnFormer;
    @Bind(R.id.mGridview)
    MyGridView mGridVIew;
    @Bind(R.id.ll_bottom_boton)
    LinearLayout ll_bottom_boton;

    @Bind(R.id.btn_delete)
    Button btn_delete;

    @Bind(R.id.btn_ok)
    Button btn_ok;
    @Bind(R.id.btn_no)
    Button btn_no;

    String order_sn;
    String order_goods_id;
    String shop_status;
    String refund_type;
    SellReturnDetailsPresenter mPresenter = new SellReturnDetailsPresenter(this);
    private RetrunDetailsBean.DataBean bean;

    @Override
    public int initContentView() {
        return R.layout.activity_sell_retrun_details;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        btnFormer.setVisibility(View.VISIBLE);
        tvTitle.setText("退款退货详情");
        order_sn=getIntent().getStringExtra("order_sn");
        order_goods_id=getIntent().getStringExtra("order_goods_id");
        shop_status = getIntent().getStringExtra("roder_status");
        refund_type = getIntent().getStringExtra("refund_type");


        mPresenter.getToken(token);
        mPresenter.ShipPingGoods(order_sn,order_goods_id);
    }

    @Override
    public void initUiAndListener() {

    }
    private AlertDialog alertDialog;
    @OnClick({R.id.img_back, R.id.iv_speak,  R.id.tv_btn_look,R.id.tv_copy_nummber,R.id.btn_ok,R.id.btn_no,R.id.btn_delete,R.id.btn_former})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.iv_speak:
                try {
                    if (bean.getEmchat()==null){
                        ToastUtil.showToast("买家不在线");
                        return;
                    }
//                    UserInfoDaoUtils daoUtils = new UserInfoDaoUtils(this);
//                    ShopEmchatBean emchatBean = new ShopEmchatBean();
//                    emchatBean.setNickname(bean.getEmchat().getNickname());
//                    emchatBean.setEmchat_password(bean.getEmchat().getEmchat_password());
//                    emchatBean.setEmchat_username(bean.getEmchat().getEmchat_username());
//                    emchatBean.setHead_pic(Constants.Base_URL+bean.getEmchat().getHead_pic());
//                    if (daoUtils.queryUserInfoByEId(bean.getEmchat().getEmchat_username()).size() > 0) {
//                        daoUtils.updateUserInfo(emchatBean);
//                    } else {
//                        daoUtils.insertUserInfo(emchatBean);
//                    }
                    EaseUser easeUser = new EaseUser(bean.getEmchat().getEmchat_username());
                    easeUser.setAvatar(Constants.Base_IMG_URL+bean.getEmchat().getHead_pic());
                    easeUser.setNickname(bean.getEmchat().getNickname());
                    List<EaseUser> users = new ArrayList<EaseUser>();
                    users.add(easeUser);
                    DemoHelper.getInstance().updateContactList(users);

                    Intent intent1 = new Intent(this, ChatActivity.class);
                    intent1.putExtra("userId", bean.getEmchat().getEmchat_username());
                    startActivity(intent1);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast("买家不在线");
                }

                break;
                case R.id.tv_btn_look:
            Intent i = new Intent(this,RecordActivity.class);
                i.putExtra("order_sn",order_sn);
                startActivity(i);
                break;
            case R.id.btn_ok:
                if (!TextUtils.isEmpty(refund_type)&&refund_type.equals("1")){
                    int   isPay_password =  PreferencesUtils.getInt(this,"isPay_password");
                    if (isPay_password==1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        final View view2 = View.inflate(SellRetrunDetailsActivity.this, R.layout.layout_lialog_one, null);
                        builder.setView(view2);
                        alertDialog = builder.create();
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.setCancelable(false);
                        TextView text_title = (TextView) view2.findViewById(R.id.text_title);
                        text_title.setText("输入支付密码");
                        TextView tv_ok_d = (TextView) view2.findViewById(R.id.tv_ok);
                        TextView  tv_quxiao = (TextView) view2.findViewById(R.id.tv_quxiao);
                        final EditText mEditText = (EditText) view2.findViewById(R.id.mEditText);
                        tv_ok_d.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPresenter.OkRoNoGoods(order_sn,"1",order_goods_id);
                            }
                        });
                        //取消
                        tv_quxiao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }else {
                        Intent intent = new Intent(SellRetrunDetailsActivity.this,ChagePayPwdActivity.class);
                        startActivity(intent);
                    }
//                    CenterActionDialog dialog =   new CenterActionDialog(this);
//                    dialog.setActionString("同意退款吗？",
//                            "确定",
//                            "取消");
//                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
//                        @Override
//                        public void sureAction() {
//                            mPresenter.OkRoNoGoods(order_sn,"1");
//                        }
//
//                        @Override
//                        public void cancelAction() {
//
//                        }
//                    });
//                    dialog.show();

                }else {
                    CenterActionDialog dialog =   new CenterActionDialog(this);
                    dialog.setActionString("同意退款退货吗？",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            mPresenter.OkRoNoGoods(order_sn,"2",order_goods_id);
                        }

                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();


                }
                break;
            case R.id.btn_no:
                if (!TextUtils.isEmpty(refund_type)&&refund_type.equals("1")){
                    CenterActionDialog dialog =   new CenterActionDialog(this);
                    dialog.setActionString("拒绝退款吗？",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            mPresenter.OkRoNoGoods(order_sn,"3",order_goods_id);
                        }

                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();
                }else {
                    CenterActionDialog dialog =   new CenterActionDialog(this);
                    dialog.setActionString("拒绝退款退货吗？",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            mPresenter.OkRoNoGoods(order_sn,"4",order_goods_id);
                        }
                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();
                }
                break;
            case R.id.btn_delete:
                if (shop_status.equals("10")){
                    int   isPay_password =  PreferencesUtils.getInt(this,"isPay_password");
                    if (isPay_password==1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        final View view2 = View.inflate(SellRetrunDetailsActivity.this, R.layout.layout_lialog_one, null);
                        builder.setView(view2);
                        alertDialog = builder.create();
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.setCancelable(false);
                        TextView text_title = (TextView) view2.findViewById(R.id.text_title);
                        text_title.setText("输入支付密码");
                        TextView tv_ok_d = (TextView) view2.findViewById(R.id.tv_ok);
                        TextView  tv_quxiao = (TextView) view2.findViewById(R.id.tv_quxiao);
                        final EditText mEditText = (EditText) view2.findViewById(R.id.mEditText);
                        tv_ok_d.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (alertDialog!=null)
                                    alertDialog.dismiss();
                                mPresenter.ReceiveGoods(order_sn,mEditText.getText().toString());
                            }
                        });
                        //取消
                        tv_quxiao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (alertDialog!=null)
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }else {
                        Intent intent = new Intent(SellRetrunDetailsActivity.this,ChagePayPwdActivity.class);
                        startActivity(intent);
                    }
                }else if (shop_status.equals("2")){
                    mPresenter.onUrged(bean.getRefund_id());
                }else if (shop_status.equals("3")){
                    CenterActionDialog dialog =   new CenterActionDialog(this);
                    dialog.setActionString("删除该申请？",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            mPresenter.onDeleteOrder(order_sn);
                        }
                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();

                }else if (shop_status.equals("4")){
                    CenterActionDialog dialog =   new CenterActionDialog(this);
                    dialog.setActionString("删除该申请？",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            mPresenter.onDeleteOrder(order_sn);
                        }
                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();

                }
                break;
            case R.id.btn_former:
                Intent intent = new Intent(this,SellOrderDetaildeActivity.class);
                intent.putExtra("orderId",order_sn);
                intent.putExtra("type","retrun");
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
        tvPrice.setText("¥"+bean.getAmount());

        tvMessage.setText(bean.getDescription());
        tvCause.setText(bean.getReason());

        tvOrderMode.setText(bean.getRefund_type());


        final List<String> images= bean.getImages();
        RetrunImageAdapter adapter = new RetrunImageAdapter(images,this);
        mGridVIew.setAdapter(adapter);
        mGridVIew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SellRetrunDetailsActivity.this,
                        LookBigImgActivity.class);
                List<PhotoEntity> list = new ArrayList<PhotoEntity>();
                for (int j = 0; j < images.size(); j++) {
                    PhotoEntity photoEntity = new PhotoEntity();
                    if (!TextUtils.isEmpty(images.get(j))) {
                        photoEntity.setImg_url(images.get(j));
                        photoEntity.bendi = 1;
                        photoEntity.setId(i + "");
                        list.add(photoEntity);
                    }
                }
                intent.putExtra("imgBeen", (Serializable) list);
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });


        if ( !TextUtils.isEmpty(refund_type)){
            if (refund_type.equals("1")){
                btn_no.setText("拒绝退款");
              btn_ok.setText("同意退款");
            }else {
                btn_no.setText("拒绝退货退款");
                btn_ok.setText("同意退货退款");
            }
        }

        if (shop_status.equals("1")){
            ll_bottom_boton.setVisibility(View.VISIBLE);
            btn_delete.setVisibility(View.GONE);
        }else if (shop_status.equals("10")){
            ll_bottom_boton.setVisibility(View.GONE);
            btn_delete.setVisibility(View.VISIBLE);
            btn_delete.setText("确认收货并退款");
        }else if (shop_status.equals("2")){
            ll_bottom_boton.setVisibility(View.GONE);
            btn_delete.setVisibility(View.VISIBLE);
            btn_delete.setText("提醒退货");
        }else if (shop_status.equals("3")){
            ll_bottom_boton.setVisibility(View.GONE);
            btn_delete.setVisibility(View.VISIBLE);
            btn_delete.setText("删除订单");
        }else if (shop_status.equals("4")){
            ll_bottom_boton.setVisibility(View.GONE);
            btn_delete.setVisibility(View.VISIBLE);
            btn_delete.setText("删除订单");
        }


    }

    @Override
    public void ShopPingGoodsSuccree() {
        setResult(30);
        finish();
    }

    @Override
    public void OkRoNoGOodssuccree() {
//        mPresenter.ShipPingGoods(order_sn,order_goods_id);
        setResult(30);
        finish();
    }

    @Override
    public void OnDeleteOrderSucreed() {
        setResult(30);
        finish();
    }
}
