package com.lnkj.privateshop.ui.mybuy.refunds.shipping;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.ShipPingAdapter;
import com.lnkj.privateshop.entity.ShipPingBean;
import com.lnkj.privateshop.ui.mybuy.sell.order.shipping.MipcaActivityCapture;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 退款发货
 */
public class ShipPingReturnActivity extends BaseActivity implements ShipPingReturnContract.View{


    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_11)
    TextView tv11;
    @Bind(R.id.tv_company)
    TextView tvCompany;
    @Bind(R.id.ll_shipping)
    LinearLayout llShipping;
    @Bind(R.id.tv_number)
    EditText tvNumber;
    @Bind(R.id.tv_copy_nummber)
    ImageView tvCopyNummber;
    @Bind(R.id.tv_btn_ok)
    TextView tvBtnOk;
    @Bind(R.id.ll_bottom)
    LinearLayout llBottom;
    private ShipPingAdapter adapter;
    String   order_sn;
    ShipPingReturnPresenter mPresenter = new ShipPingReturnPresenter(this);
    @Override
    public int initContentView() {
        return R.layout.activity_ship_ping_return;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("确认发货");
        order_sn = getIntent().getStringExtra("order_sn");
        mPresenter.getToken(token);
        mPresenter.getShipPingDataFromServer();

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
                String  expressid =  adapter.getChickId();
                String express_code = tvNumber.getText().toString();
                String express_name = adapter.getChickName();
                mPresenter.ShipPingGoods(order_sn,expressid,express_code,express_name);
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
    AlertDialog mAlertDialog;
    View dialogView;

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
//        dialog.setActionString("发货成功","确定","返回","提醒");
//        dialog.setActionListener(new CenterTiteActionDialog.ActionLisenter() {
//            @Override
//            public void sureAction(){
                //确定
                setResult(30);
                finish();
//            }
//
//            @Override
//            public void cancelAction() {
//
//            }
//        });
//        dialog.show();
    }
}
