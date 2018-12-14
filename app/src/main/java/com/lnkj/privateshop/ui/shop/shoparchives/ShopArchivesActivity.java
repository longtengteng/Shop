package com.lnkj.privateshop.ui.shop.shoparchives;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.ShopArchivesBean;
import com.lnkj.privateshop.ui.login.LoginActivity;
import com.lnkj.privateshop.ui.shop.ShopCommentActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.lnkj.privateshop.R.id.tv_price;

/**
 * +
 * 店铺档案
 */

public class ShopArchivesActivity extends BaseActivity implements ShopArchivesContract.View {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.img_shop_head)
    CircleImageView imgShopHead;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.im_grade1)
    ImageView imGrade1;
    @Bind(R.id.im_grade2)
    ImageView imGrade2;
    @Bind(R.id.im_grade3)
    ImageView imGrade3;
    @Bind(R.id.im_grade4)
    ImageView imGrade4;
    @Bind(R.id.im_grade5)
    ImageView imGrade5;
    @Bind(R.id.tv_open_shop_date)
    TextView tvOpenShopDate;
    @Bind(R.id.tv_shop_address)
    TextView tvShopAddress;
    @Bind(tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_good_comment)
    TextView tvGoodComment;
    @Bind(R.id.tv_count_comment)
    TextView tvCountComment;
    @Bind(R.id.rc_rate_mass)
    RatingBar rcRateMass;
    @Bind(R.id.tv_mass)
    TextView tvMass;
    @Bind(R.id.rb_rate_ervice)
    RatingBar rbRateErvice;
    @Bind(R.id.tv_service)
    TextView tvService;
    @Bind(R.id.rb_rate_velocity)
    RatingBar rbRateVelocity;
    @Bind(R.id.tv_velocity)
    TextView tvVelocity;
    @Bind(R.id.tv_fans_count)
    TextView tvFansCount;
    @Bind(R.id.tv_return_count)
    TextView tvReturnCount;
    @Bind(R.id.rl_parent)
    LinearLayout rlParent;
    @Bind(R.id.tv_people)
    TextView tvPeople;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.iv_phoen)
    ImageView ivPhoen;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_follow)
    TextView tv_follow;
    String shop_id;
    @Bind(R.id.tv_price_m)
    TextView tv_price_m;
    ShopArchivesPresenter mPresenter = new ShopArchivesPresenter(this);

    @Override
    public int initContentView() {
        return R.layout.activity_shop_archives;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tv_title.setText("店铺档案");
        shop_id = getIntent().getStringExtra("shop_id");
        mPresenter.setToken(token);
        mPresenter.getShomComment(shop_id);
    }

    @Override
    public void initUiAndListener() {

    }


    @OnClick({R.id.img_back, R.id.iv_phoen, R.id.tv_follow, R.id.tv_count_comment})
    public void onViewClicked(View view) {
        Boolean is_bogin = PreferencesUtils.getBoolean(this, "is_bogin");
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.iv_phoen:
                if (!is_bogin) {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                    return;
                }
                call(data.getUser_mobile());
                break;
            case R.id.tv_follow:
                if (!is_bogin) {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                    return;
                }
                mPresenter.setCollectShop(shop_id);
                break;
            case R.id.tv_count_comment:
                Intent intent = new Intent(this, ShopCommentActivity.class);
                intent.putExtra("shop_id", shop_id);
                startActivity(intent);
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

    private void call(String phone) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } catch (Exception e) {
        }
    }

    ShopArchivesBean.DataBean data;

    @Override
    public void getShomCommentSucceed(ShopArchivesBean beans) {
        data = beans.getData();
        tvShopName.setText(data.getShop_name());
        String shop_bond = data.getShop_bond();
        try {

            if (shop_bond.equals("0")) {
                tvPrice.setText("未缴纳保证金");
                tvPrice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ShopArchivesActivity.this, com.lnkj.privateshop.ui.mybuy.openshop.money.MoneyActivity.class);
                        intent.putExtra("shop_id", PreferencesUtils.getString(ShopArchivesActivity.this, "shop_id"));
                        startActivity(intent);
                    }
                });
            } else {
                tvPrice.setText(data.getShop_bond() + "元");
            }
        } catch (Exception e) {
        }
        Glide
                .with(this)
                .load(Constants.Base_URL + data.getShop_logo())
                .error(R.mipmap.bg_img)
                .into(imgShopHead);
        if (data.getIs_favorite() == 1) {
            tv_follow.setText("已关注");
            tv_follow.setBackgroundResource(R.drawable.button_default);
            tv_follow.setTextColor(Color.parseColor("#ffffff"));

        } else {
            tv_follow.setText("+关注");
            tv_follow.setBackgroundResource(R.drawable.button_bj_two);
            tv_follow.setTextColor(Color.parseColor("#999999"));
        }
        tvOpenShopDate.setText(data.getAdd_time());
        tvShopAddress.setText(data.getAddress());
        try {

            String number = data.getShop_bond();
            String intNumber = number.substring(0, number.indexOf("."));
            int num = Integer.parseInt(intNumber);
            if (num < 2000) {
                tv_price_m.setVisibility(View.GONE);
            } else if (num < 3000) {
                tv_price_m.setText("两千");
            } else if (num < 5000) {
                tv_price_m.setText("三千");
            } else if (num < 10000) {
                tv_price_m.setText("五千");
            } else if (num < 20000) {
                tv_price_m.setText("一万");
            } else if (num < 50000) {
                tv_price_m.setText("两万");
            } else {
                tv_price_m.setText("五万");
            }
        } catch (Exception e) {
        }

//        tv_price_m.setText(MoneyFormat.toChineseCharI(intNumber));


        ShopArchivesBean.DataBean.CommentBean comment = data.getComment();
        if (comment != null) {
            tvGoodComment.setText(comment.getFavorableRate() + "%");
            rcRateMass.setRating(Float.parseFloat(comment.getGoods_rank()));
            tvMass.setText(comment.getGoods_rank());
            rbRateErvice.setRating(Float.parseFloat(comment.getService_rank()));
            tvService.setText(comment.getService_rank());
            rbRateVelocity.setRating(Float.parseFloat(comment.getExpress_rank()));
            tvVelocity.setText(comment.getExpress_rank());
            tvCountComment.setText(comment.getCommentCount() + "条评论");
        }
        tvFansCount.setText(data.getCollect_num());
        tvReturnCount.setText(data.getRefund_num());
        tvPeople.setText(data.getContacts_name());
        tvPhone.setText(data.getUser_mobile());


        ShopArchivesBean.DataBean.Shop_grade grade = data.getShop_grade();
        if (grade != null) {
            if (grade.getType().equals("G1")) {
                imGrade1.setImageResource(R.mipmap.icon_heart);
                imGrade2.setImageResource(R.mipmap.icon_heart);
                imGrade3.setImageResource(R.mipmap.icon_heart);
                imGrade4.setImageResource(R.mipmap.icon_heart);
                imGrade5.setImageResource(R.mipmap.icon_heart);
                goneOrVisible(grade.getNum());
            } else if (grade.getType().equals("G2")) {
                imGrade1.setImageResource(R.mipmap.icon_dimon);
                imGrade2.setImageResource(R.mipmap.icon_dimon);
                imGrade3.setImageResource(R.mipmap.icon_dimon);
                imGrade4.setImageResource(R.mipmap.icon_dimon);
                imGrade5.setImageResource(R.mipmap.icon_dimon);
                goneOrVisible(grade.getNum());
            } else if (grade.getType().equals("G3")) {
                imGrade1.setImageResource(R.mipmap.icon_silvercrown);
                imGrade2.setImageResource(R.mipmap.icon_silvercrown);
                imGrade3.setImageResource(R.mipmap.icon_silvercrown);
                imGrade4.setImageResource(R.mipmap.icon_silvercrown);
                imGrade5.setImageResource(R.mipmap.icon_silvercrown);
                goneOrVisible(grade.getNum());
            } else if (grade.getType().equals("G4")) {
                imGrade1.setImageResource(R.mipmap.icon_goldcrown);
                imGrade2.setImageResource(R.mipmap.icon_goldcrown);
                imGrade3.setImageResource(R.mipmap.icon_goldcrown);
                imGrade4.setImageResource(R.mipmap.icon_goldcrown);
                imGrade5.setImageResource(R.mipmap.icon_goldcrown);
                goneOrVisible(grade.getNum());
            }
        }
    }

    @Override
    public void SetColloectShopSuccreed() {
        if (data.getIs_favorite() == 0) {
            data.setIs_favorite(1);
            tv_follow.setText("已关注");
            tv_follow.setBackgroundResource(R.drawable.button_default);
            tv_follow.setTextColor(Color.parseColor("#ffffff"));
        } else {
            tv_follow.setText("+关注");
            data.setIs_favorite(0);
            tv_follow.setBackgroundResource(R.drawable.button_bj_two);
            tv_follow.setTextColor(Color.parseColor("#999999"));
        }

    }

    public void goneOrVisible(int num) {
        if (num == 1) {
            imGrade1.setVisibility(View.VISIBLE);
            imGrade2.setVisibility(View.GONE);
            imGrade3.setVisibility(View.GONE);
            imGrade4.setVisibility(View.GONE);
            imGrade5.setVisibility(View.GONE);
        } else if (num == 2) {
            imGrade1.setVisibility(View.VISIBLE);
            imGrade2.setVisibility(View.VISIBLE);
            imGrade3.setVisibility(View.GONE);
            imGrade4.setVisibility(View.GONE);
            imGrade5.setVisibility(View.GONE);
        } else if (num == 3) {
            imGrade1.setVisibility(View.VISIBLE);
            imGrade2.setVisibility(View.VISIBLE);
            imGrade3.setVisibility(View.VISIBLE);
            imGrade4.setVisibility(View.GONE);
            imGrade5.setVisibility(View.GONE);
        } else if (num == 4) {
            imGrade1.setVisibility(View.VISIBLE);
            imGrade2.setVisibility(View.VISIBLE);
            imGrade3.setVisibility(View.VISIBLE);
            imGrade4.setVisibility(View.VISIBLE);
            imGrade5.setVisibility(View.GONE);
        } else if (num == 5) {
            imGrade1.setVisibility(View.VISIBLE);
            imGrade2.setVisibility(View.VISIBLE);
            imGrade3.setVisibility(View.VISIBLE);
            imGrade4.setVisibility(View.VISIBLE);
            imGrade5.setVisibility(View.VISIBLE);
        }
    }
}
