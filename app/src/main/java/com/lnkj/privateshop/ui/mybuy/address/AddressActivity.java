package com.lnkj.privateshop.ui.mybuy.address;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.AddresslistAdapter;
import com.lnkj.privateshop.entity.AddressListBean;
import com.lnkj.privateshop.ui.mybuy.address.addaddress.AddressTwoActivity;
import com.lnkj.privateshop.view.CenterActionDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收货地址
 */

public class AddressActivity extends BaseActivity implements AddressContract.View ,AddresslistAdapter.AddressClickListener{
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.btn_add)
    Button btnAdd;
    AddresslistAdapter adapter;
    String address;
    private AddressPresenter mPresenter = new AddressPresenter(this);
    private List<AddressListBean.DataBean> lists;

    @Override
    public int initContentView() {
        return R.layout.activity_address;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("我的收货地址");
        mPresenter.getToken(token);
        mPresenter.getDataFromServer();
        address= getIntent().getStringExtra("addorder");
    }

    @Override
    protected void onRestart() {
        mPresenter.getDataFromServer();
        super.onRestart();
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

    @Override
    public void showFragment(int position) {

    }

    @Override
    public void updatePraiseView(int dif, int position) {

    }

    @Override
    public void setData(AddressListBean beans) {
        lists = beans.getData();
        adapter = new AddresslistAdapter(this, lists);
        if (!TextUtils.isEmpty(address)){
           adapter.address(1);
        }
        adapter.setAddressClickListener(this);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!TextUtils.isEmpty(address)){
                    Intent intent = new Intent();
                    intent.putExtra("id",lists.get(i).getAddress_id());
                    intent.putExtra("name",lists.get(i).getConsignee());
                    intent.putExtra("Mobile",lists.get(i).getMobile());
                    intent.putExtra("Address",lists.get(i).getAddress());
                    setResult(30,intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void PullLoadMoreComplete() {

    }

    @Override
    public void deldetAddressSuccress(List<AddressListBean.DataBean> lists, int position) {
        lists.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setuAddressSuccress() {
        setResult(30);
    }

    @OnClick({R.id.img_back, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_add:
                Intent intent = new Intent(this,AddressTwoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDeleteCilck(final List<AddressListBean.DataBean> lists, final int position) {
        CenterActionDialog dialog =   new CenterActionDialog(this);
        dialog.setActionString("确定删除此地址吗？",
                "确定",
                "取消");
        dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
            @Override
            public void sureAction() {
                mPresenter.deldetAddress(lists,position);
            }

            @Override
            public void cancelAction() {

            }
        });
        dialog.show();

    }

    @Override
    public void onEditCilck(List<AddressListBean.DataBean> lists, int position) {
        Intent intent = new Intent(this,AddressTwoActivity.class);
        intent.putExtra("addressid",lists.get(position).getAddress_id());
        startActivity(intent);
    }

    @Override
    public void onSetupCilck(final List<AddressListBean.DataBean> lists, final int position, final int flag) {

//        CenterActionDialog dialog =   new CenterActionDialog(this);
//        dialog.setActionString("确定设为默认地址吗？",
//                "确定",
//                "取消");
//        dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
//            @Override
//            public void sureAction() {
                mPresenter.SetuAddress(lists,position,flag);
//            }
//
//            @Override
//            public void cancelAction() {
//
//            }
//        });
//        dialog.show();


    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
