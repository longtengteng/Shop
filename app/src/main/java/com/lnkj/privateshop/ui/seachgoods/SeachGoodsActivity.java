package com.lnkj.privateshop.ui.seachgoods;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.ClassGoodsSeachAdapter;
import com.lnkj.privateshop.adapter.SeachAddriAdapter;
import com.lnkj.privateshop.adapter.SeachGoodsAdapter;
import com.lnkj.privateshop.adapter.SeachRightAdapter;
import com.lnkj.privateshop.adapter.SeachSizeAdapter;
import com.lnkj.privateshop.adapter.SortGoodsSeachAdapter;
import com.lnkj.privateshop.entity.AddriGoodsBean;
import com.lnkj.privateshop.entity.ClassGoodsBean;
import com.lnkj.privateshop.entity.SeachGoodsBean;
import com.lnkj.privateshop.entity.SizeBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.MyPopupWindow;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.tv_class;


public class SeachGoodsActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener, SeachGoodsContract.View ,View.OnClickListener{
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.img_seach)
    ImageView imgSeach;
    @Bind(R.id.et_seach)
    TextView etSeach;
    @Bind(R.id.tv_sereen)
    TextView tvSereen;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    @Bind(R.id.tv_attri)
    TextView tvAttri;
    @Bind(R.id.tv_sorting)
    TextView tvSorting;
    ClassGoodsSeachAdapter classAdapter;
    SortGoodsSeachAdapter sortAdapter;
    @Bind(R.id.tv_class)
    TextView tvClass;
    @Bind(R.id.mDrawerlayout)
    DrawerLayout mDaawerLayout;

    @Bind(R.id.id_drawer)
    LinearLayout idDrawer;
    @Bind(R.id.cb_entity)
    CheckBox cbEntity;
    @Bind(R.id.cb_factory)
    CheckBox cbFactory;
    @Bind(R.id.cb_net)
    CheckBox cbNet;
    @Bind(R.id.et_min_price)
    EditText etMinPrice;
    @Bind(R.id.et_max_price)
    EditText etMaxPrice;
    @Bind(R.id.et_count)
    EditText etCount;
    @Bind(R.id.tv_reset)
    TextView tvReset;
    @Bind(R.id.tv_ok)
    TextView tvOk;
    @Bind(R.id.mGridView)
    GridView mGridView;
    @Bind(R.id.ll_nodata)
    LinearLayout ll_nodata;
    private static String[] sort={"综合排序","发布时间","价格","收藏量","销量"};
    private String id="";
    private String attr="";
    private String sorts="";
    private String min_price="";
    private String max_price="";
    private String pack_num="";
    private String shop_type="";
    private String goods_size="";
    private String keywords="";

    private int page = 1;
    private SeachGoodsPresenter mPresenter = new SeachGoodsPresenter(this);
    private SeachGoodsAdapter adapter;
    private MyPopupWindow mPopWindow;

    private SeachAddriAdapter addridapter;
    private SeachRightAdapter rightAdapter;

    private SeachSizeAdapter sizeadapter;

    @Override
    public int initContentView() {
        return R.layout.activity_seach_goods;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        keywords = getIntent().getStringExtra("keyWords");
        etSeach.setText(keywords);

        if (TextUtils.isEmpty(keywords)) {
            keywords = "";
            etSeach.setText("搜索商品");
        }
        if (TextUtils.isEmpty(id)) {
            id = "";
        }

        if (TextUtils.isEmpty(getIntent().getStringExtra("name"))) {
            tvClass.setText("分类");
        } else {

            tvClass.setText(getIntent().getStringExtra("name"));
        }
        classAdapter = new ClassGoodsSeachAdapter(this);
        //设置是否可以下拉刷新
        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        pullLoadMoreRecyclerView.setRefreshing(true);
        pullLoadMoreRecyclerView.setPushRefreshEnable(true);
        pullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        pullLoadMoreRecyclerView.setGridLayout(2);



        adapter = new SeachGoodsAdapter(this);
        addridapter = new SeachAddriAdapter(this);
        rightAdapter = new SeachRightAdapter(this);
        sortAdapter = new SortGoodsSeachAdapter(this, sort);
        sizeadapter = new SeachSizeAdapter(this);

        pullLoadMoreRecyclerView.setAdapter(adapter);
        mGridView.setAdapter(sizeadapter);
        mPresenter.setToken(token);
        mPresenter.getDataFromServer(id, attr, sorts, min_price, max_price, pack_num, page, shop_type, goods_size, keywords);

        mPresenter.getClassGoods();
        mPresenter.getDataAttri(id);
        mPresenter.getSize();

        adapter.setmClickListener(new SeachGoodsAdapter.OrderClickListener() {
            @Override
            public void oncollectShop(int position) {
                Intent intent = new Intent(SeachGoodsActivity.this, GoodsInfoActivity.class);
                intent.putExtra("goods_id", data.get(position).getGoods_id());
                startActivity(intent);
            }
        });

    }

    //    显示分类选择
    private void showPopupWindowClass() {
        final Drawable drawable = getResources().getDrawable(R.mipmap.screen_icon_down);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_class_layout_seach_goods, null);
        mPopWindow = new MyPopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        //让pop可以点击外面消失掉
        mPopWindow.setBackgroundDrawable(new ColorDrawable(0));
        ListView lv_left = (ListView) contentView.findViewById(R.id.lv_left);
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                classAdapter.setCheckedPosition(i);
                tvClass.setText(lists.get(i).getCat_name());
                id = lists.get(i).getCat_id();
                page = 1;
                mPresenter.getDataAttri(id);
                mPresenter.getDataFromServer(id,attr,sorts,min_price,max_price,pack_num,page,shop_type,goods_size,keywords);
                if (mPopWindow != null && mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                    tvClass.setTextColor(Color.parseColor("#000000"));
                    tvClass.setCompoundDrawables(null, null, drawable, null);
                }
            }
        });
        lv_left.setAdapter(classAdapter);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tvClass.setTextColor(Color.parseColor("#000000"));
                tvClass.setCompoundDrawables(null, null, drawable, null);
            }
        });
        mPopWindow.showAsDropDown(tvClass,Gravity.BOTTOM, 0);
    }

    //    显示属性选择
    private void showPopupAddriWindowClass() {
        final Drawable drawable = getResources().getDrawable(R.mipmap.screen_icon_down);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_addri_seach_layout, null);
        mPopWindow = new MyPopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        //让pop可以点击外面消失掉
        mPopWindow.setBackgroundDrawable(new ColorDrawable(0));
        final ListView lv_left = (ListView) contentView.findViewById(R.id.lv_left);
        final ListView lv_right = (ListView) contentView.findViewById(R.id.lv_right);

        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tvAttri.setTextColor(Color.parseColor("#000000"));
                tvAttri.setCompoundDrawables(null, null, drawable, null);
            }
        });
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                addridapter.setCheckedPosition(i);
//                childBean = classlists.get(i).getChild();
                listvalue = listsAddri.get(i).get_value_list();
                rightAdapter.addData(listvalue);

            }
        });

        lv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                rightAdapter.setCheckedPosition(i);
                tvAttri.setText(listvalue.get(i).getAttr_value());
                attr = listvalue.get(i).getAttr_value();
                page = 1;
                mPresenter.getDataFromServer(id,attr,sorts,min_price,max_price,pack_num,page,shop_type,goods_size,keywords);
                if (mPopWindow != null && mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                    tvAttri.setTextColor(Color.parseColor("#000000"));
                    tvAttri.setCompoundDrawables(null, null, drawable, null);
                }
            }
        });
//        mPopWindow.setOutsideTouchable(true);
//        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        lv_left.setAdapter(addridapter);
        lv_right.setAdapter(rightAdapter);
        //显示PopupWindow
        mPopWindow.showAsDropDown(tvAttri, Gravity.BOTTOM, 0);
    }
    //    显示排序
    private void showPopupWindowSort() {
        final Drawable drawable = getResources().getDrawable(R.mipmap.screen_icon_down);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_class_layout_seach_goods, null);
        mPopWindow = new MyPopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        //让pop可以点击外面消失掉
        mPopWindow.setBackgroundDrawable(new ColorDrawable(0));
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tvSorting.setCompoundDrawables(null, null, drawable, null);
                tvSorting.setTextColor(Color.parseColor("#000000"));
            }
        });
        ListView lv_left = (ListView) contentView.findViewById(R.id.lv_left);
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sortAdapter.setCheckedPosition(i);
                tvSorting.setText(sort[i]);
                switch (i){
                    case 0:
                        sorts = "";
                        break;
                    case 1:
                        sorts = "add_time";
                        break;
                    case 2:
                        sorts = "shop_price";
                        break;
                    case 3:
                        sorts = "collect_num";
                        break;
                    case 4:
                        sorts = "sale_num";
                        break;
                }
                page = 1;
                mPresenter.getDataFromServer(id,attr,sorts,min_price,max_price,pack_num,page,shop_type,goods_size,keywords);
                if (mPopWindow != null && mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                    tvSorting.setTextColor(Color.parseColor("#000000"));
                    tvSorting.setCompoundDrawables(null, null, drawable, null);
                }

            }
        });
        lv_left.setAdapter(sortAdapter);
        //显示PopupWindow
        mPopWindow.showAsDropDown(tvSorting, Gravity.BOTTOM, 0);
    }
    @Override
    public void initUiAndListener() {

    }

    @OnClick({R.id.img_back, R.id.tv_sereen, tv_class, R.id.tv_attri, R.id.tv_sorting,R.id.tv_reset, R.id.tv_ok,R.id.ll_factory,R.id.ll_net,R.id.ll_entity, R.id.rl_seach})
    public void onViewClicked(View view) {
        Drawable drawable = getResources().getDrawable(R.mipmap.screen_icon_up_s);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_sereen:
                showDrawerLayout();
                break;
            case tv_class:
                showPopupWindowClass();
                tvClass.setTextColor(Color.parseColor("#FF7722"));
                tvClass.setCompoundDrawables(null, null, drawable, null);
                break;
            case R.id.tv_attri:
                if (TextUtils.isEmpty(id)){
                    ToastUtil.showToast("请选择分类");
                    return;
                }
                showPopupAddriWindowClass();
                tvAttri.setTextColor(Color.parseColor("#FF7722"));
                tvAttri.setCompoundDrawables(null, null, drawable, null);
                break;
            case R.id.tv_sorting:
                showPopupWindowSort();
                tvSorting.setTextColor(Color.parseColor("#FF7722"));
                tvSorting.setCompoundDrawables(null, null, drawable, null);
                break;
            case R.id.tv_reset:
                tvSorting.setText(sort[0]);
                tvAttri.setText("属性");
                tvClass.setText("分类");
                cbFactory.setChecked(false);
                cbEntity.setChecked(false);
                cbNet.setChecked(false);
                etMinPrice.setText("");
                etMaxPrice.setText("");
                etCount.setText("");
                for (int i = 0; i < listssize.size(); i++) {
                    listssize.get(i).setIscheck(false);
                }
                sizeadapter.notifyDataSetChanged();
                id = "";
                attr="";
                sorts="";
                min_price="";
                max_price="";
                pack_num="";
                shop_type="";
                goods_size="";

                mPresenter.getDataFromServer(id,attr,sorts,min_price,max_price,pack_num,page,shop_type,goods_size,keywords);
                break;
            case R.id.tv_ok:
                 min_price=etMinPrice.getText().toString();
                 max_price=etMaxPrice.getText().toString();
                 pack_num=etCount.getText().toString();
                if (cbNet.isChecked()){
                    shop_type="3";
                }else if (cbFactory.isChecked()){
                    shop_type="2";
                }else if (cbEntity.isChecked()){
                    shop_type="1";
                }
                for (int i = 0; i < listssize.size(); i++) {
                    if ( listssize.get(i).getIscheck()){
                        goods_size = listssize.get(i).getItem();
                    }

                }
                page = 1;
                mPresenter.getDataFromServer(id,attr,sorts,min_price,max_price,pack_num,page,shop_type,goods_size,keywords);
                showDrawerLayout();
                break;
            case R.id.ll_factory:
                cbFactory.setChecked(!cbFactory.isChecked());
                cbEntity.setChecked(false);
                cbNet.setChecked(false);
                break;
            case R.id.ll_net:
                cbFactory.setChecked(false);
                cbEntity.setChecked(false);
                cbNet.setChecked(!cbNet.isChecked());
                break;
            case R.id.ll_entity:
                cbFactory.setChecked(false);
                cbEntity.setChecked(!cbEntity.isChecked());
                cbNet.setChecked(false);
                break;
            case  R.id.rl_seach:
//                Intent intent =new Intent(this,SeachGOodsPublicActivity.class);
//                startActivityForResult(intent,20);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==20&&resultCode==30){
            keywords =  data.getStringExtra("keyWords");
            page = 1;
            mPresenter.getDataFromServer(id,attr,sorts,min_price,max_price,pack_num,page,shop_type,goods_size,keywords);
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.getDataFromServer(id,attr,sorts,min_price,max_price,pack_num,page,shop_type,goods_size,keywords);
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
    public void toLoging() {

    }

    @Override
    public void PullLoadMoreComplete() {
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }
    List<SeachGoodsBean.DataBean> data = new ArrayList<>();
    @Override
    public void succeed(SeachGoodsBean beass) {
        if (page==1){
            data.clear();
        }
        List<SeachGoodsBean.DataBean> databean = beass.getData();
        data .addAll(databean);
        if (data.size() == 0 && page == 1) {
            pullLoadMoreRecyclerView.setVisibility(View.GONE);
            ll_nodata.setVisibility(View.VISIBLE);
        } else {
            pullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
            ll_nodata.setVisibility(View.GONE);
        }
        adapter.addAllData(data);

    }

    List<ClassGoodsBean.DataBean> lists = new ArrayList<>();

    @Override
    public void classSucceed(ClassGoodsBean beass) {
        lists.addAll(beass.getData());
        classAdapter.addData(lists);
    }

    List<AddriGoodsBean.DataBean> listsAddri = new ArrayList<>();
    List<AddriGoodsBean.DataBean.ValueListBean> listvalue = new ArrayList<>();

    @Override
    public void AttriSucceed(AddriGoodsBean beass) {
        listsAddri.clear();
        listsAddri.addAll(beass.getData());
        addridapter.addData(listsAddri);
        if (listsAddri.get(0) != null) {
            listvalue = listsAddri.get(0).get_value_list();
            rightAdapter.addData(listvalue);

        }

    }
    //获取尺寸成功
    List<SizeBean.DataBean> listssize = new ArrayList();
    @Override
    public void sizeSucceed(SizeBean beass) {
        listssize.addAll(beass.getData());
        sizeadapter.addData(listssize);
    }



    /**
     * 控制策划菜单打开关闭
     */
    private void showDrawerLayout() {
        if (!mDaawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            mDaawerLayout.openDrawer(Gravity.RIGHT);
        } else {
            mDaawerLayout.closeDrawer(Gravity.RIGHT);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case
        }
    }
}
