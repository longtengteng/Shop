package com.lnkj.privateshop.ui.mybuy.sell.order.shipping;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.SellOrderDetaildeAdapter;
import com.lnkj.privateshop.adapter.ShipPingAdapter;
import com.lnkj.privateshop.entity.SellOrderDetaildeBean;
import com.lnkj.privateshop.entity.ShipPingBean;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.lnkj.privateshop.view.MyListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.tv_company;


public class ShipPingActivity extends BaseActivity implements ShipPingContract.View {
    ShipPingPresenter mPresenter = new ShipPingPresenter(this);
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_11)
    TextView tv11;
    @Bind(tv_company)
    TextView tvCompany;
    @Bind(R.id.tv_number)
    EditText tvNumber;
    @Bind(R.id.tv_copy_nummber)
    ImageView tvCopyNummber;
    @Bind(R.id.tv_order_number)
    TextView tvOrderNumber;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.mMyListView)
    MyListView mMyListView;
    @Bind(R.id.tv_goods_count_two)
    TextView tvGoodsCountTwo;
    @Bind(R.id.tv_freight)
    TextView tvFreight;
    @Bind(R.id.tv_order_price)
    TextView tvOrderPrice;
    @Bind(R.id.tv_btn_ok)
    TextView tvBtnOk;
    private ShipPingAdapter adapter;
    private String order_sn;
    SellOrderDetaildeAdapter Goodsadapter;
    @Override
    public int initContentView() {
        return R.layout.activity_ship_ping;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        Goodsadapter=new SellOrderDetaildeAdapter(this);
        mMyListView.setAdapter(Goodsadapter);
        tvTitle.setText("确认发货");
        order_sn = getIntent().getStringExtra("orderId");
        mPresenter.getShipPingDataFromServer();
        mPresenter.getOrder(order_sn);
    }

    @Override
    public void initUiAndListener() {

    }



    @OnClick({R.id.img_back, R.id.tv_btn_ok,R.id.tv_copy_nummber,R.id.ll_shipping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_btn_ok:
                if (adapter!=null){
                    CenterActionDialog dialog =   new CenterActionDialog(this);
                    dialog.setActionString("确认发货吗？",
                            "确定",
                            "取消");
                    dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            String  expressid =  adapter.getChickId();
                            String express_code = tvNumber.getText().toString();
                            mPresenter.ShipPingGoods(order_sn,expressid,express_code);
                        }

                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();


                }
                break;
            case R.id.tv_copy_nummber:
                Intent intent = new Intent(this,MipcaActivityCapture.class);
                startActivityForResult(intent,20);
                break;
            case R.id.ll_shipping:
                if (mAlertDialog!=null){
                    mAlertDialog.show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==20&&resultCode==30){
            if (data!=null){
            data.getStringExtra("data");
            tvNumber.setText(data.getStringExtra("data"));

            }
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
    public void getShipPingData(ShipPingBean beass) {
        List<ShipPingBean.DataBean> lists= beass.getData();
        dialogView = View.inflate(this, R.layout.diglog_shipping, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ListView mListView= (ListView) dialogView.findViewById(R.id.mListView);
        adapter = new ShipPingAdapter(this,lists);
        mListView.setAdapter(adapter);
        Button btnOk = (Button) dialogView.findViewById(R.id.btn_ok);
        builder.setView(dialogView);
        mAlertDialog = builder.create();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter !=null){
                    tvCompany.setText(adapter.getChickName());

                }
                if (mAlertDialog!=null){
                    mAlertDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void ShopPingGoodsSuccree() {
//        CenterTiteActionDialog dialog = new CenterTiteActionDialog(this);
//        dialog.setActionString("发货成功","继续发货","返回","提醒");
//        dialog.setActionListener(new CenterTiteActionDialog.ActionLisenter() {
//            @Override
//            public void sureAction(){
//                //确定
//                setResult(30);
//                finish();
//            }
//
//            @Override
//            public void cancelAction() {
//
//            }
//        });

        setResult(30);
        finish();
    }

    @Override
    public void getOrderSccreed(SellOrderDetaildeBean beass) {
        SellOrderDetaildeBean.DataBean data =    beass.getData();
        tvOrderNumber.setText(data.getOrder_sn());
        tvTime.setText(data.getAdd_time());
        tvTime.setText(data.getAdd_time().substring(0,11));
        tvAddress.setText(data.getAddress());
        tvName.setText(data.getConsignee());
        tvGoodsCountTwo.setText(data.getTotal_buy_number());
        tvFreight.setText("含运费"+data.getExpress_amount()+"元");
        tvOrderPrice.setText("￥"+data.getReal_pay_amount()+"元");
        try {
        Goodsadapter.addData(beass.getData().getOrder_goods());
        }catch (Exception e){}
    }

    AlertDialog mAlertDialog;
    View dialogView;

}
