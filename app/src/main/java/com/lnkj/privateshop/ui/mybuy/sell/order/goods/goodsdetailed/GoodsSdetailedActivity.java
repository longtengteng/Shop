package com.lnkj.privateshop.ui.mybuy.sell.order.goods.goodsdetailed;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.GoodsInfoGalleryAdapter;
import com.lnkj.privateshop.entity.GoodsBean;
import com.lnkj.privateshop.ui.addgoods.AddGoodsActivity;
import com.lnkj.privateshop.view.MyListView;
import com.lnkj.privateshop.view.TranslucentScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class GoodsSdetailedActivity extends BaseActivity implements GoodsDetailedContract.View {
    GoodsDetailedPresenter mPresenter = new GoodsDetailedPresenter(this);
    @Bind(R.id.rl_bar)
    RelativeLayout rlBar;
    @Bind(R.id.mCb)
    ConvenientBanner mCb;
    @Bind(R.id.tv_goods_name)
    TextView tvGoodsName;
    @Bind(R.id.tv_collect)
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
    @Bind(R.id.img_head)
    CircleImageView imgHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.mScrollView)
    TranslucentScrollView mScrollView;
    @Bind(R.id.tv_delete)
    TextView tvDelete;
    @Bind(R.id.tv_down)
    TextView tvDown;
    @Bind(R.id.ll_bottom)
    LinearLayout llBottom;
    private String goodsid;
    @Bind(R.id.mListView)
    MyListView mListView;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_context)
    TextView tvContext;
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
    //    @Bind(R.id.rl_bar)
    RelativeLayout rl_bar;
    @Bind(R.id.rl_bar_to)
    RelativeLayout rl_bar_to;
    @Bind(R.id.img_shop_head)
    ImageView img_shop_head;
    String from_shop_id;

    @Override
    public int initContentView() {
        return R.layout.activity_goods_sdetailed;
    }

    private static String FLAG;

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        from_shop_id = getIntent().getStringExtra("from_shop_id");
        rl_bar = (RelativeLayout) findViewById(R.id.rl_bar);
        FLAG = getIntent().getStringExtra("flag");
        goodsid = getIntent().getStringExtra("goodsid");
        mPresenter.getToken(token);
        if (FLAG.equals("down")) {
            tvDown.setText("上架");
            Drawable drawable = getResources().getDrawable(R.mipmap.good_icon_up);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvDown.setCompoundDrawables(drawable, null, null, null);
        } else {
            Drawable drawable = getResources().getDrawable(R.mipmap.good_icon_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvDown.setCompoundDrawables(drawable, null, null, null);
            tvDown.setText("下架");
        }
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
        mPresenter.getGoodsFromServer(goodsid, from_shop_id);
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

    private List<String> imgurllist = new ArrayList<>();
    private GoodsBean.DataBean.GoodsInfoBean bean;

    @Override
    public void getGoodssucceed(GoodsBean beass) {
        //1219
     /*   imgurllist.clear();
        for (int i = 0; i < beass.getData().getGoods_gallery().size(); i++) {
            imgurllist.add(Constants.Base_IMG_URL+beass.getData().getGoods_gallery().get(i).getImage_path());
        }

        mCb.setPages(new CBViewHolderCreator<GoodsSdetailedActivity.ImageViewHolder>() {
            @Override
            public GoodsSdetailedActivity.ImageViewHolder createHolder() {
                return new GoodsSdetailedActivity.ImageViewHolder();
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
        List<GoodsBean.DataBean.GoodsDescBean> godsLists= beass.getData().getGoods_desc();
        WindowManager wm = getWindowManager();
        int  width = wm.getDefaultDisplay().getWidth();
        GoodsInfoGalleryAdapter adapter = new GoodsInfoGalleryAdapter(this,godsLists,width);
        mListView.setAdapter(adapter);
        bean = beass.getData().getGoods_info();
        tvGoodsName.setText(bean.getGoods_name());
        tvCollect.setText(bean.getCollect_num());
        tvCount1.setText(bean.getRetail_amount()+"-"+bean.getBasic_amount()+"件");
        tvCount2.setText(bean.getBasic_amount()+"件以上");
        tvPrice1.setText("¥"+ bean.getShop_price());
        tvPrice2.setText("¥"+ bean.getPack_price());
        tvUpdate.setText(bean.getAdd_time() + "发布更新");
        tvLooking.setText(bean.getClick_count()+"次浏览");
        tvSell.setText("已卖出"+ bean.getSale_num()+"件");
        tvComment.setText(bean.getComment_num()+"条评论");
        tvSell.setText("已卖出"+ bean.getSale_num()+"件");
        GoodsBean.DataBean.ShopInfoBean shopinfo =  beass.getData().getShop_info();
        if (shopinfo!=null) {

            Glide
                    .with(this)
                    .load(Constants.Base_URL + shopinfo.getShop_logo())
                    .error(R.mipmap.bg_img)
                    .into(img_shop_head);
            tvShopName.setText(shopinfo.getShop_name());
            tvShopAddress.setText(shopinfo.getShop_addr());
            tvOpenShopDate.setText(shopinfo.getOpen_time());
            tvShopSales.setText(shopinfo.getMonth_sale_count()+"");
            tvShopUpdate.setText(shopinfo.getMonth_goods_count()+"");
            tvShopSupplement.setText(shopinfo.getSupplement()+"%");
//            shop_id=shopinfo.getShop_id();
            tvCollect.setText(bean.getCollect_num());

//            if (bean.getIs_favorite()==0){
//                Drawable nav_up=getResources().getDrawable(R.mipmap.my_icon_collection);
//                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//                tvCollect.setCompoundDrawables(nav_up, null, null, null);
//            } else {
//                Drawable nav_up=getResources().getDrawable(R.mipmap.gooddetail_icon_liked);
//                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
//                tvCollect.setCompoundDrawables(nav_up, null, null, null);
//            }
//            tvCollect.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    presenter.CollectGoods(goods_id);
//
//                }
//            });
            GoodsBean.DataBean.Shop_comment comment = beass.getData().getShop_comment();
            if (comment != null) {
                tvGood.setText(comment.getRavorableRate() + "%");
                tvComment.setText(comment.getCommentCount() + "条评论");
                List<GoodsBean.DataBean.Shop_comment.NewCommentBean> commentBeen = comment.getNewComment();
                if (commentBeen != null && commentBeen.size() != 0) {
                    Glide
                            .with(this)
                            .load(Constants.Base_URL + commentBeen.get(0).getHead_pic())
                            .error(R.mipmap.bg_img)
                            .into(imgHead);
                    tvName.setText(commentBeen.get(0).getUser_name());
                    tvTime.setText(commentBeen.get(0).getAdd_time());
                    tvContext.setText(commentBeen.get(0).getContent());
                }
            }

        }
*/
    }

    @Override
    public void GoodsSucceed() {

    }

    @Override
    public void DownGoodsSucceed() {
        finish();
    }

    @Override
    public void upGoodsSucceed() {
        finish();
    }


    @OnClick({R.id.img_beak, R.id.tv_delete, R.id.tv_down, R.id.img_beak_to})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_beak:
                finish();
                break;
            case R.id.img_beak_to:
                finish();
                break;
            case R.id.tv_delete: //编辑
                Intent intent = new Intent(this, AddGoodsActivity.class);
                intent.putExtra("goods_id", goodsid);
                startActivity(intent);
                break;
            case R.id.tv_down:
                mPresenter.getToken(token);
                if (FLAG.equals("down")) {
                    mPresenter.upGoods(goodsid);
                } else {
                    mPresenter.downGoods(goodsid);
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
            Glide.with(GoodsSdetailedActivity.this)
                    .load(data)
                    .placeholder(new ColorDrawable(Color.parseColor("#cccccc")))
                    .into(imageView);
        }

    }
}
