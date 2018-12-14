package com.lnkj.privateshop.ui.mybuy.sell.order.seach;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.SeachOrderAdapter;
import com.lnkj.privateshop.entity.GoodsCategoryBean;
import com.lnkj.privateshop.entity.SeachOrderBean;
import com.lnkj.privateshop.ui.mybuy.myorder.looktransit.LookTransitActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.orderdetailed.SellOrderDetaildeActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.orderdetailed.modify.ModifyPriceActivity;
import com.lnkj.privateshop.ui.mybuy.sell.order.shipping.ShipPingActivity;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SeachOrderActivity extends BaseActivity implements SeachOrderContract.View{

    @Bind(R.id.img_seach)
    ImageView imgSeach;
    @Bind(R.id.et_seach)
    EditText etSeach;
    @Bind(R.id.tv_seach)
    TextView tvSeach;
    @Bind(R.id.rl_seach)
    RelativeLayout rlSeach;
    @Bind(R.id.layout_no_datas)
    LinearLayout layoutNoDatas;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.img_back)
            ImageView img_back;
    SeachOrderPresenter presenter = new SeachOrderPresenter(this);
    SeachOrderAdapter adapter ;
    @Override
    public int initContentView() {
        return R.layout.activity_seach_order;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvSeach.setText("取消");
        presenter.getToken(token);
        adapter = new SeachOrderAdapter(this);
        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        pullLoadMoreRecyclerView.setPushRefreshEnable(false);
        pullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
//        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setAdapter(adapter);
    }

    @Override
    public void initUiAndListener() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        tvSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etSeach.getText().toString())){
                    presenter.getDataFromService(etSeach.getText().toString());
                }else {
                    finish();
                }
            }
        });
        etSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etSeach.getText().toString().trim().length()==0){
                    tvSeach.setText("取消");
                }else {
                    tvSeach.setText("搜索");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        adapter.setmIDeleteBtnClickListener(new SeachOrderAdapter.OrderClickListener() {

            @Override
            public void onDeleteOrderCilck(final int position) {
//                LLog.d(TAG,"删除订单");
                CenterActionDialog dialog =   new CenterActionDialog(SeachOrderActivity.this);
                dialog.setActionString("您要删除订单吗？",
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {
                        presenter.onDeleteOrder(list.get(position).getOrder_sn(),100);
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();
            }

            @Override
            public void onCancelCilck(final int position) {
                //取消订单
                CenterActionDialog dialog =   new CenterActionDialog(SeachOrderActivity.this);
                dialog.setActionString("您要取消订单吗？",
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {
                        presenter.onDeleteOrder(list.get(position).getOrder_sn(),0);
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();
            }

            @Override
            public void onLookLogisticsCilck(int position) {
                //查看物流
                Intent intent = new Intent(SeachOrderActivity.this,LookTransitActivity.class);
                intent.putExtra("orderId",list.get(position).getOrder_sn());
                try {

                    intent.putExtra("goods_pic", Constants.Base_URL+list.get(position).getGoods_info().get(0).getGoods_img());
                }catch (Exception e){}
                startActivity(intent);
            }

            @Override
            public void onRemindDeliveryCilck(int position) {
                Intent intent = new Intent(SeachOrderActivity.this,ShipPingActivity.class);
                intent.putExtra("orderId",list.get(position).getOrder_sn());
               startActivityForResult(intent,20);
            }

            @Override
            public void onModifyThePrice(int position) {
                //修改价格
                Intent intent = new Intent(SeachOrderActivity.this,ModifyPriceActivity.class);
                intent.putExtra("orderId",list.get(position).getOrder_sn());
                startActivityForResult(intent,20);
            }

            @Override
            public void onRemindPay(int position) {
                presenter.onRemindPay(list.get(position).getOrder_sn());
            }

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SeachOrderActivity.this,SellOrderDetaildeActivity.class);
                intent.putExtra("orderId",list.get(position).getOrder_sn());
                startActivityForResult(intent,20);
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
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
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

    @Override
    public void remoOrder() {
        presenter.getDataFromService(etSeach.getText().toString());
    }

    @Override
    public void okGoods() {
        presenter.getDataFromService(etSeach.getText().toString());
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==20){
            presenter.getDataFromService(etSeach.getText().toString());
        }
    }

    @Override
    public void remindDelivery(String s) {

    }
    List<SeachOrderBean.DataBean.OrderListBean> list = new ArrayList<>();
    @Override
    public void getOrderData(SeachOrderBean beass) {
        List<SeachOrderBean.DataBean.OrderListBean> orderlist =   beass.getData().getOrder_list();
            list.clear();
        list.addAll(orderlist);
        adapter.addAllData(list);
        if (list.size()==0){
            layoutNoDatas.setVisibility(View.VISIBLE);
            pullLoadMoreRecyclerView.setVisibility(View.GONE);
        }else {
            layoutNoDatas.setVisibility(View.GONE);
            pullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRemindPaySucreed() {

    }
}
