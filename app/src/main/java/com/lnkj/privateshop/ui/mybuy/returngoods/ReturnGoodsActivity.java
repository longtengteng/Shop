package com.lnkj.privateshop.ui.mybuy.returngoods;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.AlbumReturnAdapter;
import com.lnkj.privateshop.adapter.ReturnListAdapter;
import com.lnkj.privateshop.entity.ReturnGoodsBean;
import com.lnkj.privateshop.view.MyGridView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReturnGoodsActivity extends BaseActivity implements View.OnClickListener,ReturnGoodsContract.View{
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_cause)
    TextView tvCause;
    @Bind(R.id.et_price)
    TextView etPrice;
//    @Bind(R.id.et_phone)
//    EditText etPhone;
    @Bind(R.id.mGridView)
    MyGridView mGridView;
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.btn_ok)
    Button btnOk;
    @Bind(R.id.rb_quan)
    RadioButton rbQuan;
    @Bind(R.id.rb_bufen)
    RadioButton rbBufen;
    @Bind(R.id.rg_1)
    RadioGroup rg1;
    private String orderId;
    private String price;
    private String goods_id;
    private String order_goods_id;
    String type = "2";

    AlbumReturnAdapter mAdapter;
    private PopupWindow mPopWindow;
    ReturnGoodsPresenter mPresenter = new ReturnGoodsPresenter(this);
    View rootview;
    String status;
    @Override
    public int initContentView() {
        rootview=View.inflate(this,R.layout.activity_return_goods,null);
        return R.layout.activity_return_goods;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        mPresenter.getToken(token);
        orderId = getIntent().getStringExtra("OrderNumber");
        price = getIntent().getStringExtra("price");
        goods_id = getIntent().getStringExtra("goods_id");
        order_goods_id = getIntent().getStringExtra("order_goods_id");
        status = getIntent().getStringExtra("status");
        tvTitle.setText("申请退款");
        mPresenter.getReturn();
        if (status.equals("2")){
            rbQuan.setVisibility(View.VISIBLE);
            rbBufen.setVisibility(View.GONE);
            type = "1";
            rbQuan.setChecked(true);
        }
        mPresenter.getReturnPrice(orderId,goods_id);





    }

    private int IMAGE_PICKER = 0x00001;
    ArrayList<ImageItem> images = new ArrayList<>();

    private int getDataSize() {
        return images == null ? 0 : images.size();
    }

    @Override
    public void initUiAndListener() {

        mAdapter = new AlbumReturnAdapter(this, images);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == getDataSize()) {
                    ImagePicker imagePicker = ImagePicker.getInstance();
                    imagePicker.setMultiMode(true);
                    imagePicker.setCrop(false);
                    imagePicker.setSelectLimit(9 - images.size());
                    Intent intent = new Intent(ReturnGoodsActivity.this, ImageGridActivity.class);
                    startActivityForResult(intent, IMAGE_PICKER);
                }
            }
        });
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_quan:
                        type = "1";
                        break;
                    case R.id.rb_bufen:
                        type = "2";
                        break;
                }
            }
        });
    }
    //    选择退款原因
    String [] str = {"缺货、发货慢","瑕疵品，质量差","发错货，漏发货","拍错了，不想买","其他"};
    private void showPopupWindowSize() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_return_layout, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        LinearLayout llBecak = (LinearLayout) contentView.findViewById(R.id.ll_blank);
        ListView mListView = (ListView) contentView.findViewById(R.id.mListView);
        Button btn_coles = (Button) contentView.findViewById(R.id.btn_coles);
        mListView.setAdapter(new ReturnListAdapter(this,lists));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvCause.setText(lists.get(i));
                if (mPopWindow != null && mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }
            }
        });
        btn_coles.setOnClickListener(this);
        llBecak.setOnClickListener(this);
        //显示PopupWindow
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @OnClick({R.id.img_back, R.id.btn_ok,R.id.tv_cause})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_ok:
                mPresenter.getPutFromServer(type,goods_id,order_goods_id,etContent.getText().toString(),orderId,tvCause.getText().toString(),images);
                break;
            case R.id.tv_cause:
                showPopupWindowSize();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                images.addAll((ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));
                mAdapter.bindList(images);

            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_blank:
                if (mPopWindow != null && mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }
                break;
            case R.id.btn_coles:
                if (mPopWindow != null && mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }
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
    public void finisht() {
        finish();
    }

    @Override
    public void succree() {
//        ToastUtil.showToast("申请成功，请等待卖家处理");
        finish();
    }
List<String> lists;
    @Override
    public void getRetrunSuccreed(ReturnGoodsBean beass) {
        lists = beass.getData();
    }

    @Override
    public void getReturnPrice(String pice) {

        etPrice.setText("¥"+pice);
    }
}
