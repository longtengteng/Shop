package com.lnkj.privateshop.ui.mybuy.sell.order.shopdata;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.ShopGradeBean;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.lnkj.privateshop.utils.HttpUtil.meApi;

public class ShopGradeActivity extends BaseActivity {

    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
@Bind(R.id.tv_score)
TextView tv_score;
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
    @Bind(R.id.tv_top1)
    TextView tv_top1;
    @Bind(R.id.tv_top2)
    TextView tv_top2;
    @Bind(R.id.tv_top3)
    TextView tv_top3;

    @Override
    public int initContentView() {
        return R.layout.activity_shop_grade;
    }

    @Override
    public void initInjector() {
        ButterKnife.bind(this);
        tvTitle.setText("等级信息");

        tv_top1.setText(Html.fromHtml("蓝钻会员，累计成长值进入计算值 <font color='#ff7722'>251分</font>"));
        tv_top2.setText(Html.fromHtml("银冠会员，累计成长值进入计算值 <font color='#ff7722'>10001分</font>"));
        tv_top3.setText(Html.fromHtml("金冠会员，累计成长值进入计算值 <font color='#ff7722'>500001分</font>"));
    }

    @Override
    public void initUiAndListener() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        progressDialog.show();
        meApi.shopGrade(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        LLog.d("数据", data);
                        progressDialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(data);
                            int status = object.getInt("status");
                            String info = object.getString("info");
                            if (status == 1) {
                                ShopGradeBean bean = JSON.parseObject(data, ShopGradeBean.class);
                                paseData(bean.getData());
                            } else {
                                ToastUtil.showToast(info);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        progressDialog.dismiss();
                        LLog.d("数据错误", throwable.toString() + "");
                    }
                });

    }

    public void paseData(ShopGradeBean.DataBean bean) {
        tv_score.setText(bean.getNext_upgrade()+"分");
        ShopGradeBean.DataBean.ShopLevelBean grade =  bean.getShop_level();
        if (grade!=null){
            System.out.println("33333");
            if (grade.getType().equals("G1")){
                imGrade1.setImageResource(R.mipmap.icon_heart);
                imGrade2.setImageResource(R.mipmap.icon_heart);
                imGrade3.setImageResource(R.mipmap.icon_heart);
                imGrade4.setImageResource(R.mipmap.icon_heart);
                imGrade5.setImageResource(R.mipmap.icon_heart);
                goneOrVisible(grade.getNum());
            }else if (grade.getType().equals("G2")){
                imGrade1.setImageResource(R.mipmap.icon_dimon);
                imGrade2.setImageResource(R.mipmap.icon_dimon);
                imGrade3.setImageResource(R.mipmap.icon_dimon);
                imGrade4.setImageResource(R.mipmap.icon_dimon);
                imGrade5.setImageResource(R.mipmap.icon_dimon);
                goneOrVisible(grade.getNum());
            }else if (grade.getType().equals("G3")){
                imGrade1.setImageResource(R.mipmap.icon_silvercrown);
                imGrade2.setImageResource(R.mipmap.icon_silvercrown);
                imGrade3.setImageResource(R.mipmap.icon_silvercrown);
                imGrade4.setImageResource(R.mipmap.icon_silvercrown);
                imGrade5.setImageResource(R.mipmap.icon_silvercrown);
                goneOrVisible(grade.getNum());
            }else if (grade.getType().equals("G4")) {
                imGrade1.setImageResource(R.mipmap.icon_goldcrown);
                imGrade2.setImageResource(R.mipmap.icon_goldcrown);
                imGrade3.setImageResource(R.mipmap.icon_goldcrown);
                imGrade4.setImageResource(R.mipmap.icon_goldcrown);
                imGrade5.setImageResource(R.mipmap.icon_goldcrown);
                goneOrVisible(grade.getNum());
            }
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
    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
