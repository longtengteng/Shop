package com.lnkj.privateshop.ui.addgoods;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.AlbumAdapter;
import com.lnkj.privateshop.adapter.AttriAdapter;
import com.lnkj.privateshop.adapter.ClassGoodsAdapter;
import com.lnkj.privateshop.adapter.ClassRightAdapter;
import com.lnkj.privateshop.adapter.CoodsAdapter;
import com.lnkj.privateshop.adapter.SizeAdapter;
import com.lnkj.privateshop.entity.AddGoodsBean;
import com.lnkj.privateshop.entity.ColorBean2;
import com.lnkj.privateshop.entity.GoodsAttriBean;
import com.lnkj.privateshop.entity.GoodsEditBean;
import com.lnkj.privateshop.entity.SizeBean;
import com.lnkj.privateshop.utils.CashierInputFilter;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.utils.UiUtils;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.lnkj.privateshop.view.MyGridView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.ll_blank;


public class AddGoodsActivity extends BaseActivity implements View.OnClickListener, AddGoodsContract.View {
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
    @Bind(R.id.mGridView)
    MyGridView mGridview;
    @Bind(R.id.et_title)
    EditText etTitle;
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.et_goods_price)
    EditText etGoodsPrice;
    @Bind(R.id.et_packing_price)
    EditText etPackingPrice;
    @Bind(R.id.tv_color)
    TextView tvColor;
    @Bind(R.id.tv_size)
    TextView tvSize;
    @Bind(R.id.tv_class)
    TextView tvClass;
    @Bind(R.id.tv_bute)
    TextView tvBute;
    @Bind(R.id.et_weight)
    EditText etWeight;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.btn_reset)
    Button btnReset;
    @Bind(R.id.mswitch)
    Switch mswitch;
    @Bind(R.id.tv_coun)
    TextView tv_coun;
    private PopupWindow mPopWindow;
    private EditText etColor;
    private TextView tvAddColor;
    private EditText etSize;
    private TextView tvAddSize;
    private Button btnAdd;
    private ListView mListView;
    private MyGridView mGridView;
    private CoodsAdapter adapter;
    private SizeAdapter sizeadapter;
    private ClassGoodsAdapter classadapter;
    private ClassRightAdapter rightAdapter;
    private AttriAdapter attriAdapter;
//    private PopupWindows popunWin;
    private AddGoodsPresenter mPresenter = new AddGoodsPresenter(this);
    View rootview;
    AlbumAdapter mAdapter;
    private List<AddGoodsBean.DataBean.ChildBean> childBean;
    private String cat_id;
    int place = 0;
    StringBuffer delsb = new StringBuffer();
    public List<ImageItem> mDataList = new ArrayList<ImageItem>();
    ImageItem currentDeleteItem;
    private String color;
    private String size;
    private String jsonText;

    @Override
    public int initContentView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        rootview = LayoutInflater.from(this).inflate(R.layout.activity_add_goods, null);
        return R.layout.activity_add_goods;
    }
    String goods_id;
    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("上传新产品");
        mPresenter.getToken(token);
        goods_id= getIntent().getStringExtra("goods_id");
        if (!TextUtils.isEmpty(goods_id)){
            tvTitle.setText("编辑商品");
            mPresenter.getGoods(goods_id);
        }
        mPresenter.getSizeFromServer();
        mPresenter.getClassFromServer();
        mPresenter.getColorFromServer();

        adapter = new CoodsAdapter(this);
        sizeadapter = new SizeAdapter(this);
        classadapter = new ClassGoodsAdapter(this);
        rightAdapter = new ClassRightAdapter(this);
        attriAdapter = new AttriAdapter(this);
        InputFilter[] filters = {new CashierInputFilter()};
        etPackingPrice.setFilters(filters);
        etGoodsPrice.setFilters(filters);

    }
private int IMAGE_PICKER =0x00001;

    private String pos;
    private int posint =0;
    @Override
    public void initUiAndListener() {
        mAdapter = new AlbumAdapter(this, images);
        mGridview.setAdapter(mAdapter);
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == getDataSize()) {
                    ImagePicker imagePicker = ImagePicker.getInstance();
                    imagePicker.setCrop(false);
                    imagePicker.setMultiMode(true);
                    imagePicker.setSelectLimit(20-images.size());
                    Intent intent = new Intent(AddGoodsActivity.this, ImageGridActivity.class);
                    startActivityForResult(intent, IMAGE_PICKER);
//                    popunWin = new PopupWindows(AddGoodsActivity.this, customGridview);
                } else {
                    posint=position;

                 mAdapter.clickPosition(position);
                }
            }
        });


        mAdapter.setOnItemClickListener(new AlbumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                //删除网络图片
//                mPresenter.deleteImg(images.get(i).addTime+"",i);
                if (delsb.length()!=0){
                    delsb.append(",");
                    }
                delsb.append(images.get(i).addTime+"");
                images.remove(i);
//        System.out.println(i);
//        adapter.addData(images);
                mAdapter.notifyDataSetChanged();
            }
        });
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_coun.setText(etContent.getText().toString().length()+"/1000");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private int getDataSize() {
        return images == null ? 0 : images.size();
    }

    //显示颜色选择
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupcolorlayout, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
//        mPopWindow.setOutsideTouchable(true);
//        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        etColor = (EditText) contentView.findViewById(R.id.et_color);
        tvAddColor = (TextView) contentView.findViewById(R.id.tv_add_color);
        btnAdd = (Button) contentView.findViewById(R.id.btn_add);
        mGridView = (MyGridView) contentView.findViewById(R.id.myGridView);
        LinearLayout llBecak = (LinearLayout) contentView.findViewById(ll_blank);
        tvAddColor.setOnClickListener(this);
        llBecak.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        mGridView.setAdapter(adapter);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    //    显示尺码选择
    private void showPopupWindowSize() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupsizelayout, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
//        mPopWindow.setOutsideTouchable(true);
//        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        LinearLayout llBecak = (LinearLayout) contentView.findViewById(ll_blank);
        etSize = (EditText) contentView.findViewById(R.id.et_size);
        tvAddSize = (TextView) contentView.findViewById(R.id.tv_add_size);
        btnAdd = (Button) contentView.findViewById(R.id.btn_add_size);
        mGridView = (MyGridView) contentView.findViewById(R.id.myGridView);

        mGridView.setAdapter(sizeadapter);
        llBecak.setOnClickListener(this);
        tvAddSize.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        //显示PopupWindow
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }

    //    显示分类选择
    private void showPopupWindowClass() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_class_layout, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        TextView tv_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
        LinearLayout llBecak = (LinearLayout) contentView.findViewById(ll_blank);
        final ListView lv_left = (ListView) contentView.findViewById(R.id.lv_left);
        final ListView lv_right = (ListView) contentView.findViewById(R.id.lv_right);
        tv_ok.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        llBecak.setOnClickListener(this);
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                classadapter.setCheckedPosition(i);
                childBean = classlists.get(i).getChild();
                rightAdapter.addData(childBean);

            }
        });

        lv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                rightAdapter.setCheckedPosition(i);
                place = i;
                mPresenter.getAttriFromServer(childBean.get(i).getCat_id());
            }
        });
//        mPopWindow.setOutsideTouchable(true);
//        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        lv_left.setAdapter(classadapter);
        lv_right.setAdapter(rightAdapter);
        //显示PopupWindow
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    //    显示商品属性
    private void showPopupWindowAttri() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_attri_layout, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
//        mPopWindow.setOutsideTouchable(true);
//        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        LinearLayout llBecak = (LinearLayout) contentView.findViewById(ll_blank);
        btnAdd = (Button) contentView.findViewById(R.id.btn_attri_ok);
        mListView = (ListView) contentView.findViewById(R.id.mListView);
        attriAdapter =new AttriAdapter(this);
        attriAdapter.addData(beass.getData());
        mListView.setAdapter(attriAdapter);

        btnAdd.setOnClickListener(this);
        llBecak.setOnClickListener(this);
        //显示PopupWindow
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }



    public static void startActivity(Context mContext) {
        Intent intext = new Intent(mContext, AddGoodsActivity.class);
        mContext.startActivity(intext);
    }





    @OnClick({R.id.img_back, R.id.tv_color, R.id.tv_size, R.id.tv_class, R.id.tv_bute, R.id.btn_submit, R.id.btn_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_color:

                UiUtils.HideKeyboard(tvColor);
                showPopupWindow();
                break;
            case R.id.tv_size:
                UiUtils.HideKeyboard(tvColor);
                showPopupWindowSize();
                break;
            case R.id.tv_class:
                UiUtils.HideKeyboard(tvColor);
                showPopupWindowClass();
                break;
            case R.id.tv_bute:
                UiUtils.HideKeyboard(tvColor);
                if (TextUtils.isEmpty(cat_id)) {
                    ToastUtil.showToast("请选择商品分类");
                } else {
                    try {
                    showPopupWindowAttri();

                    }catch (Exception e){}
                }
                break;
            case R.id.btn_submit:
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                String goodsprice = etGoodsPrice.getText().toString();
                String goodspickprice = etPackingPrice.getText().toString();
                color = getListColor();
                if (TextUtils.isEmpty(color)){
                    color=selectedcolor;
                }
                size = getListSizeId();
                if (TextUtils.isEmpty(size)){
                    size= selectedsize;
                }
                jsonText = attriAdapter.getAttriId();
                if (TextUtils.isEmpty(jsonText)){
                    jsonText=selectedattri;
                }
                String weight =  etWeight.getText().toString();


                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < images.size(); i++) {
                    if (sb.length()!=0){
                        sb.append(",");
                    }
                    if (i!=posint){
                        sb.append("0");
                    }else {
                        sb.append("1");
                    }
                }

                if (!TextUtils.isEmpty(goods_id)){
                     mPresenter.editGoods(title,content,goodsprice,goodspickprice, color, size,cat_id, jsonText,weight,sb.toString()
                             ,images,goods_id,mswitch.isChecked(),delsb.toString());
                }else {
                     mPresenter.AddGoods(title,content,goodsprice,goodspickprice, color, size,cat_id, jsonText,weight,sb.toString(),images,mswitch.isChecked());
                }
                break;
            case R.id.btn_reset:
                //确认收货
                CenterActionDialog dialog =   new CenterActionDialog(this);
                dialog.setActionString("确定重置信息吗？",
                        "确定",
                        "取消");
                dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
                    @Override
                    public void sureAction() {

                        if (TextUtils.isEmpty(goods_id)){
                            etTitle.setText("");
                        }
                        etContent.setText("");
                        etGoodsPrice.setText("");
                        etPackingPrice.setText("");
                        etWeight.setText("");
                        tvColor.setText("");
                        tvSize.setText("");
                        tvBute.setText("");
                        images.clear();
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void cancelAction() {

                    }
                });
                dialog.show();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_color: //添加颜色
                String etcolor = etColor.getText().toString();
                if (TextUtils.isEmpty(etcolor)) {
                    ToastUtil.showToast("请输入颜色");
                } else {
                    mPresenter.putNewColor(etcolor);
                }
                break;
            case R.id.btn_add: //选择颜色完成
                if (TextUtils.isEmpty(getListColorName())) {
                    ToastUtil.showToast("请选择颜色");
                } else {
                    tvColor.setText(getListColorName());
                    if (mPopWindow != null && mPopWindow.isShowing()) {
                        mPopWindow.dismiss();
                    }
                }
                break;
            case R.id.btn_add_size:
                if (TextUtils.isEmpty(getListSizeName())) {
                    ToastUtil.showToast("请选择尺码");
                } else {
                    tvSize.setText(getListSizeName());
                    if (mPopWindow != null && mPopWindow.isShowing()) {
                        mPopWindow.dismiss();
                    }
                }
                break;
            case R.id.tv_add_size:
                String etsize = etSize.getText().toString();
                if (TextUtils.isEmpty(etsize)) {
                    ToastUtil.showToast("请输尺码");
                } else {
                    mPresenter.putNewSize(etsize);
                }
                break;
            case R.id.tv_cancel:
                if (mPopWindow != null && mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }
                break;
            case R.id.tv_ok:

//                    ToastUtil.showToast("请选择类别");

                    tvClass.setText(childBean.get(place).getCat_name_mobile());
                    cat_id = childBean.get(place).getCat_id()+"" ;
                    if (mPopWindow != null && mPopWindow.isShowing()) {
                        mPopWindow.dismiss();
                    }
                break;
            case ll_blank:
                if (mPopWindow != null && mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }
                break;
            case R.id.btn_ok:
                if (mPopWindow != null && mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                }
                break;
            case R.id.btn_attri_ok:
                if (TextUtils.isEmpty(attriAdapter.getTextAttri())) {
                    ToastUtil.showToast("请选择属性");
                } else {
                    tvBute.setText(attriAdapter.getTextAttri());
                    if (mPopWindow != null && mPopWindow.isShowing()) {
                        mPopWindow.dismiss();
                    }
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
    public void initView() {

    }

    @Override
    public void onBackPressed() {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        }
        super.onBackPressed();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    //获取颜色成功
    List<ColorBean2.DataBean> lists = new ArrayList();

    @Override
    public void getColorSucceed(ColorBean2 beass) {
        lists.addAll(beass.getData());
        adapter.addData(lists);
    }

    //获取尺寸成功
    List<SizeBean.DataBean> listssize = new ArrayList();

    @Override
    public void getSizeSucceed(SizeBean beass) {
        listssize.addAll(beass.getData());
        sizeadapter.addData(listssize);
    }

    //获取分类成功
    List<AddGoodsBean.DataBean> classlists = new ArrayList<>();

    @Override
    public void getClassSucceed(AddGoodsBean beass) {
        classlists.addAll(beass.getData());
        classadapter.addData(classlists);
        try {
            childBean = beass.getData().get(0).getChild();
            if (!TextUtils.isEmpty(cat_id)) {
                mPresenter.getAttriFromServer(childBean.get(0).getCat_id());

            }
            rightAdapter.addData(childBean);
        } catch (Exception e) {
        }
    }
    GoodsAttriBean beass ;
    //获取属性成功
    @Override
    public void getAttriSucceed(GoodsAttriBean beass) {
        this.beass=beass;
        attriAdapter.addData(beass.getData());

    }
String  selectedcolor ;
    String selectedsize;
    String selectedattri;
    @Override
    public void addGoodsSucceed() {
        finish();
    }
    ArrayList<ImageItem> images =new ArrayList<>();
    @Override
    public void getGoodsSuccreed(GoodsEditBean beass) {
      GoodsEditBean.DataBean data =  beass.getData();
        etTitle.setText(data.getGoods_name());
        etContent.setText(data.getGoods_description());
        etGoodsPrice.setText(data.getShop_price());
        etPackingPrice.setText(data.getPack_price());
        btnSubmit.setText("保存修改");
        etWeight.setText(data.getWeight());
        mswitch.setChecked(data.getIs_made().endsWith("1")?true:false);
        List<GoodsEditBean.DataBean.ImgListBean> imgLists=   data.getImg_list();
        cat_id = data.getCat_id();
        if (imgLists!=null){
            for (int i = 0; i < imgLists.size(); i++) {
                ImageItem item = new ImageItem();
                item.path= Constants.Base_IMG_URL+imgLists.get(i).getImage_path();
                item.addTime=Long.parseLong(imgLists.get(i).getImg_id());  //充当参数
                item.name="http";
                images.add(item);
                if (imgLists.get(i).getIs_default().equals("1")){
                    mAdapter.clickPosition(i);
                }
            }

            tvClass.setText(data.getCat_name());

            mPresenter.getAttriFromServer(data.getCat_id());
            mAdapter.bindList(images);
            StringBuffer sb = new StringBuffer();
            StringBuffer sb_id = new StringBuffer();
            if (data.getSelected_color()!=null) {
                for (int i = 0; i < data.getSelected_color().size(); i++) {
                    if (sb.length() != 0) {
                        sb.append(",");
                        sb_id.append(",");
                    }
                    sb.append(data.getSelected_color().get(i).getColor_name());
                    sb_id.append(data.getSelected_color().get(i).getColor_id());
                }
                selectedcolor = sb_id.toString();
                tvColor.setText(sb.toString());
            }
            if (data.getSelected_size()!=null) {
                StringBuffer sb1 = new StringBuffer();
                StringBuffer sb_size = new StringBuffer();
                for (int i = 0; i < data.getSelected_size().size(); i++) {
                    if (sb1.length() != 0) {
                        sb1.append(",");
                        sb_size.append(",");
                    }
                    sb1.append(data.getSelected_size().get(i).getSize_name());
                    sb_size.append(data.getSelected_size().get(i).getSize_id());
                }
                tvSize.setText(sb1.toString());
                selectedsize = sb_size.toString();
            }
            if (data.getSelected_attr()!=null) {
                StringBuffer sb2 = new StringBuffer();
                StringBuffer sb_attri = new StringBuffer();
                for (int i = 0; i < data.getSelected_attr().size(); i++) {
                    if (sb2.length() != 0) {
                        sb2.append(",");
                    }
                    sb_attri.append(data.getSelected_attr().get(i).getAttr_id());
                    sb_attri.append(":");
                    sb_attri.append(data.getSelected_attr().get(i).getAttr_value());
                    sb2.append(data.getSelected_attr().get(i).getAttr_value());
                }
                tvBute.setText(sb2.toString());
                selectedattri=sb_attri.toString();
            }
        }
    }


    //新增颜色成功
    @Override
    public void putNewColorSucceed(String etcolor, String id, String img) {
        ColorBean2.DataBean bean = new ColorBean2.DataBean();
        bean.setItem(etcolor);
        bean.setIscheck(false);
        bean.setColor_img(img);
        bean.setId(id);
        lists.add(bean);
        adapter.notifyDataSetChanged();
    }

    //新增尺寸
    @Override
    public void putNewSizeSucceed(String etcolor, String id) {
        SizeBean.DataBean bean = new SizeBean.DataBean();
        bean.setItem(etcolor);
        bean.setIscheck(false);
        bean.setId(id);
        listssize.add(bean);
        sizeadapter.notifyDataSetChanged();
    }


    //获取选中颜色id
    @Override
    public String getListColor() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getIscheck()) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(lists.get(i).getId());

            }
        }
        return sb.toString();
    }

    @Override
    public void deleteImgSuccreed(int i) {
        images.remove(i);
//        System.out.println(i);
//        adapter.addData(images);
        mAdapter.notifyDataSetChanged();
    }

    //获取选中的颜色名称
    public String getListColorName() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getIscheck()) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(lists.get(i).getItem());

            }
        }
        return sb.toString();
    }

    //获取选中尺寸id
    public String getListSizeId() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < listssize.size(); i++) {
            if (listssize.get(i).getIscheck()) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(listssize.get(i).getId());

            }
        }
        return sb.toString();
    }

    //获取选中尺寸名称
    public String getListSizeName() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < listssize.size(); i++) {
            if (listssize.get(i).getIscheck()) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(listssize.get(i).getItem());

            }
        }
        return sb.toString();
    }

    //获取选中类别名称
    public String getListClassName() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < listssize.size(); i++) {
            if (listssize.get(i).getIscheck()) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(listssize.get(i).getId());

            }
        }
        return sb.toString();
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

}
