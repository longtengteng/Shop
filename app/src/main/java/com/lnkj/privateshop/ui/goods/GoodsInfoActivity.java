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
import com.bumptech.glide.Glide;
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
import com.lnkj.privateshop.ui.login.LoginActivity;
import com.lnkj.privateshop.ui.mybuy.feedback.BeedBackActivity;
import com.lnkj.privateshop.ui.shop.ShopCommentActivity;
import com.lnkj.privateshop.ui.shop.shopInfo.ShopInfoActivity;
import com.lnkj.privateshop.ui.shop.shopclass.ShopClassActivity;
import com.lnkj.privateshop.utils.CountDownUtil;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.MyListView;
import com.lnkj.privateshop.view.TranslucentScrollView;

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
    @Bind(R.id.tv_time_h)
    TextView tvTime_H;
    @Bind(R.id.tv_time_m)
    TextView tvTime_M;
    @Bind(R.id.tv_time_s)
    TextView tvTime_S;
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
    @Bind(R.id.tv_count_1)
    TextView tvCount1;
    @Bind(R.id.tv_count_2)
    TextView tvCount2;
    @Bind(R.id.tv_price_1)
    TextView tvPrice1;
    @Bind(R.id.tv_price_2)
    TextView tvPrice2;
    @Bind(R.id.tv_update)
    TextView tvUpdate;
    @Bind(R.id.tv_looking)
    TextView tvLooking;
    @Bind(R.id.tv_sell)
    TextView tvSell;
    @Bind(R.id.tv_good)
    TextView tvGood;
    @Bind(R.id.tv_comment)
    TextView tvComment;
    @Bind(R.id.img_shop_head)
    CircleImageView imgShopHead;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.tv_shop_address)
    TextView tvShopAddress;
    @Bind(R.id.tv_open_shop_date)
    TextView tvOpenShopDate;
    @Bind(R.id.tv_shop_sales)
    TextView tvShopSales;
    @Bind(R.id.tv_shop_update)
    TextView tvShopUpdate;
    @Bind(R.id.tv_shop_supplement)
    TextView tvShopSupplement;
    @Bind(R.id.mScrollView)
    TranslucentScrollView mScrollView;
    @Bind(R.id.img_fenlei)
    TextView imgFenlei;
    @Bind(R.id.img_chat)
    TextView imgChat;
    @Bind(R.id.img_shop)
    TextView imgShop;
    @Bind(tv_add_cart)
    TextView tvAddCart;
    @Bind(R.id.ll_bottom)
    RelativeLayout llBottom;
    @Bind(R.id.img_head)
    CircleImageView imgHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_context)
    TextView tvContext;
    ImageView ivMenu;
    @Bind(R.id.rl_bar_to)
    RelativeLayout rl_bar_to;
    @Bind(R.id.ll_goodcoment)
    LinearLayout ll_goodcoment;
    @Bind(R.id.tv_desc)
    TextView tv_desc;
    @Bind(R.id.myListView)
    MyListView myListView;
    GoodsAttrAdapter attrAdapter;
    private GoodsInfoPresenter presenter = new GoodsInfoPresenter(this, this);
    private String goods_id = "";
    private String shop_id;
    GoodsBean.DataBean dataBean;
    private GoodsBean.DataBean.GoodsInfoBean bean;
    private Boolean is_bogin;

    @Override
    public int initContentView() {
        goods_id = getIntent().getStringExtra("goods_id");

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
        List<GoodsBean.DataBean.GoodsDescBean> godsLists = beass.getData().getGoods_desc();
        dataBean = beass.getData();

        List<GoodsAttrBean> attrlist = new ArrayList<>();
        try {
            GoodsAttrBean bean1 = new GoodsAttrBean();
            bean1.setName("颜色");
            bean1.setValue(dataBean.getGoods_spec().getColor());
            attrlist.add(bean1);
            GoodsAttrBean bean0 = new GoodsAttrBean();
            bean0.setName("尺码");
            bean0.setValue(dataBean.getGoods_spec().getSize());
            attrlist.add(bean0);
            for (int i = 0; i < dataBean.getGoods_attr().size(); i++) {
                GoodsAttrBean bean2 = new GoodsAttrBean();
                bean2.setName(dataBean.getGoods_attr().get(i).getAttr_name());
                bean2.setValue(dataBean.getGoods_attr().get(i).getAttr_value());
                attrlist.add(bean2);
            }
        } catch (Exception e) {

        }
        myListView.setAdapter(new GoodsAttrAdapter(this, attrlist));
        WindowManager wm = getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        GoodsInfoGalleryAdapter adapter = new GoodsInfoGalleryAdapter(this, godsLists, width);
        mListView.setAdapter(adapter);
        bean = beass.getData().getGoods_info();
        GoodsBean.DataBean.Activity activity = bean.getActivity();
        if (activity != null) {
            ll_time.setVisibility(View.VISIBLE);
            tv_xianjia.setText("¥ " + activity.getActivity_price());
            tv_yuanjia.setText("¥ " + activity.getShop_price());
            tv_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            try {
                long time = Long.parseLong(activity.getEnd_time()) * 1000 - System.currentTimeMillis();
                CountDownUtil cdu = new CountDownUtil(time, 1000 * 60 * 60,
                        tvTime_H, 1);
                cdu.start();
                CountDownUtil cdu2 = new CountDownUtil(time, 1000 * 60,
                        tvTime_M, 2);
                cdu2.start();
                CountDownUtil cdu3 = new CountDownUtil(time, 1000,
                        tvTime_S, 3);
                cdu3.start();

            } catch (Exception e) {
            }

        }

        bean.getIs_on_sale();
        if (!TextUtils.isEmpty(bean.getIs_on_sale()) && bean.getIs_on_sale().equals("0")) {
            tvAddCart.setText("商品已下架");
            tvAddCart.setClickable(false);
            tvAddCart.setBackgroundColor(Color.parseColor("#666666"));
        }
        tvGoodsName.setText(bean.getGoods_name());
        tvCollect.setText(bean.getCollect_num());
        int basic = Integer.parseInt(bean.getBasic_amount()) - 1;
        tvCount1.setText(bean.getRetail_amount() + "—" + basic + "件");
        tvCount2.setText(bean.getBasic_amount() + "件以上");
        tvPrice1.setText("¥" + bean.getShop_price());
        tvPrice2.setText("¥" + bean.getPack_price());
        tvLooking.setText(bean.getClick_count() + "次浏览");
        tv_desc.setText(bean.getGoods_description());
        tvSell.setText("已售出" + bean.getSale_num() + "件");
        GoodsBean.DataBean.ShopInfoBean shopinfo = beass.getData().getShop_info();
        if (shopinfo != null) {
            with(this)
                    .load(Constants.Base_URL + shopinfo.getShop_logo())
                    .error(R.mipmap.de_photo)
                    .into(imgShopHead);
            tvShopName.setText(shopinfo.getShop_name());
            tvShopAddress.setText(shopinfo.getShop_addr());
            tvOpenShopDate.setText(shopinfo.getOpen_time());
            tvShopSales.setText(shopinfo.getMonth_sale_count() + "");
            tvShopUpdate.setText(shopinfo.getMonth_goods_count() + "");
            tvShopSupplement.setText(shopinfo.getSupplement() + "%");
            shop_id = shopinfo.getShop_id();

            tvCollect.setText(bean.getCollect_num());
            tvUpdate.setText(bean.getAdd_time() + "发布更新");
            tvLooking.setText(bean.getClick_count() + "次浏览");

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
        GoodsBean.DataBean.Shop_comment comment = beass.getData().getShop_comment();
        if (comment != null) {
            tvGood.setText(comment.getRavorableRate() + "%");
            tvComment.setText(comment.getCommentCount() + "条评论");
            List<GoodsBean.DataBean.Shop_comment.NewCommentBean> commentBeen = comment.getNewComment();
            if (commentBeen == null) {
                ll_goodcoment.setVisibility(View.GONE);
            } else {
                if (commentBeen.size() != 0) {
                    ll_goodcoment.setVisibility(View.VISIBLE);
                    if (TextUtils.isEmpty(commentBeen.get(0).getContent())) {
                        ll_goodcoment.setVisibility(View.GONE);
                    } else {
                        Glide
                                .with(this)
                                .load(Constants.Base_URL + commentBeen.get(0).getHead_pic())
                                .error(R.mipmap.de_photo)
                                .into(imgHead);
                        tvName.setText(commentBeen.get(0).getUser_name());
                        tvTime.setText(commentBeen.get(0).getAdd_time());
                        tvContext.setText(commentBeen.get(0).getContent());
                    }
                } else {
                    ll_goodcoment.setVisibility(View.GONE);

                    Glide
                            .with(this)
                            .load(Constants.Base_URL + commentBeen.get(0).getHead_pic())
                            .error(R.mipmap.de_photo)
                            .into(imgHead);
                    tvName.setText(commentBeen.get(0).getUser_name());
                    tvTime.setText(commentBeen.get(0).getAdd_time());
                    tvContext.setText(commentBeen.get(0).getContent());
                }
            }

        }
        try {

            GoodsBean.DataBean.Emchat emchat = beass.getData().getEmchat();
            if (emchat != null) {
                EaseUser easeUser = new EaseUser(emchat.getEmchat_username());
                easeUser.setNickname(emchat.getNickname());
                easeUser.setAvatar(Constants.Base_IMG_URL + emchat.getHead_pic());
                DemoHelper.getInstance().saveContact(easeUser);
            }
        } catch (Exception e) {
        }

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


    @OnClick({R.id.img_beak, R.id.img_fenlei, R.id.img_chat, R.id.img_shop, tv_add_cart, R.id.tv_comment, R.id.img_goods_car,
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
            case R.id.img_fenlei:
//                intent = new Intent(this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra("type", "goods_class");
//                startActivity(intent);

                intent = new Intent(this, ShopClassActivity.class);
                intent.putExtra("shop_id", shop_id);
                startActivity(intent);
                break;
            case R.id.img_chat:
                try {
                    if (TextUtils.isEmpty(dataBean.getEmchat().getEmchat_username())) {
                        ToastUtil.showToast("客服不在线");
                        return;
                    }
                    Boolean is_bogin = PreferencesUtils.getBoolean(this, "is_bogin");
                    if (!is_bogin) {
                        ToastUtil.showToast("您还没有登录，请去登录");
                        Intent i = new Intent(this, LoginActivity.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        return;
                    }
                    String emchat_username = PreferencesUtils.getString(this, "emchat_username");
                    if (emchat_username.equals(dataBean.getEmchat().getEmchat_username())) {
                        ToastUtil.showToast("不能跟自己聊天");
                        return;
                    }

                    intent = new Intent(GoodsInfoActivity.this, ChatActivity.class);
                    intent.putExtra("userId", dataBean.getEmchat().getEmchat_username());
                    ChatGoodBean chatGoodBean = new ChatGoodBean();
                    chatGoodBean.setCommodityName(dataBean.getGoods_info().getGoods_name());
                    chatGoodBean.setCommodityID(dataBean.getGoods_info().getGoods_id() + "");
                    if (dataBean.getGoods_gallery() != null && dataBean.getGoods_gallery().size() != 0) {
                        chatGoodBean.setCommodityImage(Constants.Base_IMG_URL + dataBean.getGoods_gallery().get(0).getImage_path());
                    }
                    chatGoodBean.setCommodityPrice("¥" + dataBean.getGoods_info().getShop_price());
                    chatGoodBean.setContent(dataBean.getGoods_info().getGoods_name());
                    chatGoodBean.setShareUrl("");
                    intent.putExtra("chat_good", chatGoodBean);
                    startActivity(intent);
                } catch (Exception e) {
                }
                break;
            case R.id.img_shop:
                try {
                    intent = new Intent(this, ShopInfoActivity.class);
                    intent.putExtra("shop_id", shop_id);
                    startActivity(intent);
                } catch (Exception e) {
                }
                break;
            case tv_add_cart:

                if (is_bogin) {
                    intent = new Intent(this, AddShopPingCarActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("lists", bean);
                    bundle.putString("goods_id", goods_id);
                    if (ll_time.getVisibility() == View.VISIBLE) {
                        bundle.putBoolean("activity", true);
                    }
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    intent = new Intent(this, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }


                break;
            case R.id.tv_comment:
                try {
                    intent = new Intent(this, ShopCommentActivity.class);
                    intent.putExtra("shop_id", shop_id);
                    startActivity(intent);
                } catch (Exception e) {
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
}
