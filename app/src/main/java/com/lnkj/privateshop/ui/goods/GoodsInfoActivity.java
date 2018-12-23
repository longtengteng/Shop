package com.lnkj.privateshop.ui.goods;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.ChatGoodBean;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.GoodsAttrAdapter;
import com.lnkj.privateshop.adapter.GoodsInfoGalleryAdapter;
import com.lnkj.privateshop.chat.ChatActivity;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.entity.GoodsAttrBean;
import com.lnkj.privateshop.entity.GoodsBean;
import com.lnkj.privateshop.entity.ShopEmchatBean;
import com.lnkj.privateshop.ui.MainActivity;
import com.lnkj.privateshop.ui.addshoppingcart.AddShopPingCarActivity;
import com.lnkj.privateshop.ui.ease.EaseConversationListActivity;
import com.lnkj.privateshop.ui.goods.spec.SpecActivity;
import com.lnkj.privateshop.ui.login.LoginActivity;
import com.lnkj.privateshop.ui.mybuy.feedback.BeedBackActivity;
import com.lnkj.privateshop.ui.shop.shopInfo.ShopInfoActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.MyListView;
import com.lnkj.privateshop.view.TranslucentScrollView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static com.bumptech.glide.Glide.with;
import static com.lnkj.privateshop.R.id.tv_add_cart;
import static com.lnkj.privateshop.R.id.tv_collect;

/**
 * 商品详情
 */
public class GoodsInfoActivity extends BaseActivity implements GoodsInfoContract.View {
    @Bind(R.id.tv_xianjia)
    TextView tv_xianjia;
    @Bind(R.id.tv_yuanjia)
    TextView tv_yuanjia;
    @Bind(R.id.ll_time)
    LinearLayout ll_time;
    @Bind(R.id.rl_bar)
    RelativeLayout rl_bar;
    @Bind(R.id.mListView)
    MyListView mListView;
    @Bind(R.id.img_beak)
    ImageView imgBeak;
    @Bind(R.id.img_goods_car)
    ImageView imgGoodsCar;
    @Bind(R.id.img_menu)
    ImageView imgMenu;
    @Bind(R.id.mCb)
    ConvenientBanner mCb;
    @Bind(R.id.tv_goods_name)
    TextView tvGoodsName;
    @Bind(tv_collect)
    TextView tvCollect;
    @Bind(R.id.img_shop_head)
    CircleImageView imgShopHead;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.tv_shop_address)
    TextView tvShopAddress;
    @Bind(R.id.tv_shop_supplement)
    TextView tvShopSupplement;
    @Bind(R.id.mScrollView)
    TranslucentScrollView mScrollView;
    @Bind(R.id.img_chat)
    TextView imgChat;
    @Bind(tv_add_cart)
    TextView tvAddCart;
    @Bind(R.id.ll_bottom)
    RelativeLayout llBottom;
    ImageView ivMenu;
    @Bind(R.id.rl_bar_to)
    RelativeLayout rl_bar_to;
    @Bind(R.id.tv_desc)
    TextView tv_desc;
    @Bind(R.id.myListView)
    MyListView myListView;
    GoodsAttrAdapter attrAdapter;
    @Bind(R.id.tv_time_h)
    TextView tvTimeH;
    @Bind(R.id.tv_time_m)
    TextView tvTimeM;
    @Bind(R.id.tv_time_s)
    TextView tvTimeS;
    @Bind(R.id.tv_new_price)
    TextView tvNewPrice;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_volume)
    TextView tvVolume;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.ll_shop)
    LinearLayout llShop;
    @Bind(R.id.tv_describe)
    TextView tvDescribe;
    @Bind(R.id.tv_service)
    TextView tvService;
    @Bind(R.id.tv_logistics)
    TextView tvLogistics;
    @Bind(R.id.tv_miaos)
    TextView tvMiaos;
    @Bind(R.id.img_beak_to)
    ImageView imgBeakTo;
    @Bind(R.id.img_goods_car_to)
    ImageView imgGoodsCarTo;
    @Bind(R.id.img_menu_to)
    ImageView imgMenuTo;
    @Bind(R.id.img_collect)
    TextView imgCollect;
    @Bind(R.id.tv_self)
    TextView tv_self;
    @Bind(R.id.ll_spec)
    LinearLayout ll_spec;
    @Bind(R.id.tv_spec)
    TextView tv_spec;
    private GoodsInfoPresenter presenter = new GoodsInfoPresenter(this, this);
    private String goods_id = "";
    private String shop_id;
    GoodsBean.DataBean dataBean;
    private GoodsBean.DataBean.GoodsInfoBean bean;
    private Boolean is_bogin;
    private String act_id;

    @Override
    public int initContentView() {
        goods_id = getIntent().getStringExtra("goods_id");
        act_id = getIntent().getStringExtra("act_id");
        return R.layout.activity_goods_info;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        ivMenu = (ImageView) findViewById(R.id.img_menu);
        is_bogin = PreferencesUtils.getBoolean(this, "is_bogin");
        presenter.setToken(token);
        presenter.getGoodsInfo(goods_id);
//        presenter.getShopEmchat(goods_id + "");
        rl_bar.setAlpha(0);
        mScrollView.setOnTranslucent(new TranslucentScrollView.TranslucentListener() {
            @Override
            public void onTranslucent(float alpha) {
                rl_bar.setAlpha(alpha);
//                System.out.println("alpha:"+alpha);
                if (alpha < 0.017) {
                    rl_bar_to.setVisibility(View.VISIBLE);
                } else {
                    rl_bar_to.setVisibility(View.GONE);
                }
            }
        });

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
    public void initView() {

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
    public void finsh() {
        finish();
    }

    private List<String> imgurllist = new ArrayList<>();
//    GoodsBean beass;

    @Override
    public void getGoodsInfoSucceed(GoodsBean beass) {
        imgurllist.clear();
        for (int i = 0; i < beass.getData().getGoods_gallery().size(); i++) {
            imgurllist.add(Constants.Base_IMG_URL + beass.getData().getGoods_gallery().get(i).getImage_path());
        }
        mCb.setPages(new CBViewHolderCreator<ImageViewHolder>() {
            @Override
            public ImageViewHolder createHolder() {
                return new ImageViewHolder();
            }
        }, imgurllist)
                .setPageIndicator(new int[]{R.drawable.ponit_normal, R.drawable.point_select}) //设置两个点作为指示器
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//设置指示器的方向水平  居中
                .startTurning(3000)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

//                        ToastUtil.showToast("点击了" + position);

                    }
                })
                .setCanLoop(true);
        /*图片详情*/
        List<GoodsBean.DataBean.GoodsDescBean> godsLists = beass.getData().getGoods_desc();
        dataBean = beass.getData();
        /*属性*/
        List<GoodsAttrBean> attrlist = new ArrayList<>();
        //  myListView.setAdapter(new GoodsAttrAdapter(this, attrlist));
        WindowManager wm = getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        /*商品详情图*/
        GoodsInfoGalleryAdapter adapter = new GoodsInfoGalleryAdapter(this, godsLists, width);
        mListView.setAdapter(adapter);
        bean = beass.getData().getGoods_info();
        tvTime.setText(bean.getDay_ago() + "天前发布");
        tvVolume.setText("月销" + bean.getSale_num());

        tvGoodsName.setText(bean.getGoods_name());
        tvCollect.setText(bean.getCollect_num());
        GoodsBean.DataBean.ShopInfoBean shopinfo = beass.getData().getShop_info();
        if (shopinfo != null) {
            //是否是自营
            if (shopinfo.getShop_id() != null && shopinfo.getShop_id().equals("0")) {
                tv_self.setVisibility(View.VISIBLE);
            } else {
                tv_self.setVisibility(View.GONE);
            }
            tvAddress.setText(shopinfo.getProvince() + shopinfo.getCity() + shopinfo.getCountry());
            /*店铺信息*/
            with(this)
                    .load(Constants.Base_URL + shopinfo.getShop_logo())
                    .error(R.mipmap.de_photo)
                    .into(imgShopHead);
            tvDescribe.setText(shopinfo.getRank() + "");
            tvService.setText(shopinfo.getService_rank() + "");
            tvLogistics.setText(shopinfo.getExpress_rank() + "");


            tvShopName.setText(shopinfo.getShop_name());
            tvShopAddress.setText(shopinfo.getProvince() + shopinfo.getCity() + shopinfo.getCountry());
            shop_id = shopinfo.getShop_id();

            tvCollect.setText(bean.getCollect_num());

            if (bean.getIs_favorite() == 0) {
                Drawable nav_up = getResources().getDrawable(R.mipmap.my_icon_collection);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                tvCollect.setCompoundDrawables(nav_up, null, null, null);
            } else {
                Drawable nav_up = getResources().getDrawable(R.mipmap.gooddetail_icon_liked);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                tvCollect.setCompoundDrawables(nav_up, null, null, null);
            }
            tvCollect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (is_bogin) {
                        String myshop_id = PreferencesUtils.getString(GoodsInfoActivity.this, "shop_id");
                        if (TextUtils.isEmpty(myshop_id) && myshop_id.equals(shop_id)) {
                            ToastUtil.showToast("不能收藏自己店铺商品");
                            return;
                        }
                        presenter.CollectGoods(goods_id);
                    } else {
                        ToastUtil.showToast("您还没有登录，请去登录");
                        Intent intent = new Intent(GoodsInfoActivity.this, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            });

        }
        /*try {

            GoodsBean.DataBean.Emchat emchat = beass.getData().getEmchat();
            if (emchat != null) {
                EaseUser easeUser = new EaseUser(emchat.getEmchat_username());
                easeUser.setNickname(emchat.getNickname());
                easeUser.setAvatar(Constants.Base_IMG_URL + emchat.getHead_pic());
                DemoHelper.getInstance().saveContact(easeUser);
            }
        } catch (Exception e) {
        }*/

    }

    @Override
    public void CollectGoodsSucceed() {
        if (bean.getIs_favorite() == 0) {
            bean.setIs_favorite(1);
            Drawable nav_up = getResources().getDrawable(R.mipmap.gooddetail_icon_liked);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            tvCollect.setCompoundDrawables(nav_up, null, null, null);
            bean.setCollect_num(Integer.parseInt(bean.getCollect_num()) + 1 + "");
        } else {
            bean.setIs_favorite(0);
            Drawable nav_up = getResources().getDrawable(R.mipmap.my_icon_collection);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            tvCollect.setCompoundDrawables(nav_up, null, null, null);
            bean.setCollect_num(Integer.parseInt(bean.getCollect_num()) - 1 + "");
        }
        tvCollect.setText(bean.getCollect_num());
    }


    @OnClick({R.id.ll_spec, R.id.ll_attr, R.id.img_beak, R.id.img_chat, tv_add_cart, R.id.img_goods_car,
            R.id.img_beak_to, R.id.img_goods_car_to, R.id.img_menu_to, R.id.img_menu, R.id.ll_shop})
    public void onViewClicked(View view) {
        Intent intent;

//            Boolean is_bogin = PreferencesUtils.getBoolean(this, "is_bogin");
//            if (!is_bogin){
//                ToastUtil.showToast("您还没有登录，请去登录");
//                Intent i= new Intent(this,LoginActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//                return;
//            }

        switch (view.getId()) {
            case R.id.ll_spec:
                /*商品规格选择*/
                intent = new Intent(this, SpecActivity.class);
                intent.putExtra("speclist", (Serializable) dataBean.getGoods_spec());
                intent.putExtra("img", dataBean.getGoods_info().getGoods_img());
                intent.putExtra("price", dataBean.getGoods_info().getShop_price());
                intent.putExtra("storage", dataBean.getGoods_info().getStorage());
                intent.putExtra("goods_id", dataBean.getGoods_info().getGoods_id());
                startActivityForResult(intent, 69);
                break;
            case R.id.ll_shop:
                try {
                    intent = new Intent(this, ShopInfoActivity.class);
                    intent.putExtra("shop_id", shop_id);
                    startActivity(intent);
                } catch (Exception e) {
                }
                break;
            case R.id.img_goods_car:
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("type", "addshop");
                startActivity(intent);

                break;
            case R.id.ll_attr:
                /*商品属性显示*/
                intent = new Intent(this, AttrActivity.class);
                intent.putExtra("attrlist", (Serializable) dataBean.getGoods_attr());
                startActivity(intent);
                break;
            case R.id.img_goods_car_to:
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("type", "addshop");
                startActivity(intent);
                break;
            case R.id.img_beak:
                finish();
                break;
            case R.id.img_beak_to:
                finish();
                break;
            case R.id.img_menu_to:
                showPopupWindow();
                break;
            case R.id.img_menu:
                showPopupWindow();
                break;
            case R.id.img_chat:
                break;

            case tv_add_cart:
                if (is_bogin) {
                    if (TextUtils.isEmpty(spec_content3)) {
                        ToastUtil.showToast("请选择规格");
                        return;
                    }
                    presenter.addCart(spec_content3, goods_id, "1", "", "0");
                } else {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    intent = new Intent(this, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }


                break;

        }
    }


    public class ImageViewHolder implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            with(GoodsInfoActivity.this)
                    .load(data)
                    .placeholder(new ColorDrawable(Color.parseColor("#cccccc")))
                    .into(imageView);
        }

    }

    String spec_content3, spec_name;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 69 && resultCode == RESULT_OK) {
            spec_content3 = data.getStringExtra("spec_content3");
            spec_name = data.getStringExtra("spec_name");
            tv_spec.setText(spec_name);
        }
    }

    private PopupWindow mPopWindow;

    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_item, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(ivMenu, Gravity.BOTTOM, 20);
        TextView item_add = (TextView) contentView.findViewById(R.id.item_add);
        TextView tv_message = (TextView) contentView.findViewById(R.id.tv_message);
        TextView tv_feedback = (TextView) contentView.findViewById(R.id.tv_feedback);
        TextView tv_count = (TextView) contentView.findViewById(R.id.tv_count);
        if (UnreadMsgCount > 0) {
            tv_count.setVisibility(View.VISIBLE);
            tv_count.setText(UnreadMsgCount + "");
        } else {
            tv_count.setVisibility(View.GONE);
        }
        tv_feedback.setVisibility(View.VISIBLE);
        tv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_bogin) {
                    Intent intent = new Intent(GoodsInfoActivity.this, EaseConversationListActivity.class);
                    startActivity(intent);
                } else {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    Intent intent = new Intent(GoodsInfoActivity.this, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }


            }
        });
        item_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsInfoActivity.this, MainActivity.class);
                intent.putExtra("type", "home");
                startActivity(intent);
            }
        });
        tv_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopWindow != null) {
                    mPopWindow.dismiss();
                }
                Intent intent = new Intent(GoodsInfoActivity.this, BeedBackActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getShopEmchat(final ShopEmchatBean bean) {

    }

    @Override
    public void addCart() {

    }
}
