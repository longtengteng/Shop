package com.lnkj.privateshop.fragment.goodscar;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.GoodsListCarAdapter;
import com.lnkj.privateshop.entity.GoodsCraListBean;
import com.lnkj.privateshop.ui.goodscar.ClearingActivity;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.CenterTiteActionDialog;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lnkj.privateshop.R.id.tv_add_cart;

/**
 * 购物车
 */

public class GoodsFragment extends BaseFragment implements GoodsCraContract.View {
    GoodsCarPresenter mPresenter = new GoodsCarPresenter(this, getActivity());
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right_blue)
    TextView tvRightBlue;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.cb_check)
    CheckBox cbCheck;
    @Bind(tv_add_cart)
    TextView tvPay;

    @Bind(R.id.tv_price)
    TextView tv_price;
    GoodsListCarAdapter adapter;
    int state = 0;
    @Bind(R.id.tv_left_blue)
    TextView tvLeftBlue;
    @Bind(R.id.ll_total)
    LinearLayout ll_total;
    @Bind(R.id.ll_no_data)
    LinearLayout layout_no_datas;
    @Bind(R.id.btn)
    Button btn;

    @Override
    protected int getContentResid() {
        return R.layout.fragment_goods;
    }

    @Override
    protected void init(View view) {

        super.init(view);
        ButterKnife.bind(this, view);
        tvTitle.setText("购物车");
        imgBack.setVisibility(View.GONE);
        tvRightBlue.setText("编辑");
        tvRightBlue.setVisibility(View.VISIBLE);
//        tvLeftBlue.setText("完成");
        //设置是否可以下拉刷新
        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        pullLoadMoreRecyclerView.setRefreshing(false);
        pullLoadMoreRecyclerView.setPushRefreshEnable(false);
        pullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
//        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        pullLoadMoreRecyclerView.setLinearLayout();
        adapter = new GoodsListCarAdapter(getActivity());
        pullLoadMoreRecyclerView.setAdapter(adapter);
        mPresenter.setToken(token);

        adapter.setCheckListener(new GoodsListCarAdapter.onCheckListener() {
            @Override
            public void check() {
                price = 0.00;
                computationsPrice();
            }
        });


    }

    @Override
    protected void loadDatas() {
        super.loadDatas();


    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void initView() {
        mPresenter.getGoodsCar();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    List<GoodsCraListBean.DataBean.ListBean> lists = new ArrayList<>();
    Double price;

    @Override
    public void getShopCommentSucceed(GoodsCraListBean beans) {
        lists.clear();
        lists.addAll(beans.getData().getList());
        if (lists.size() == 0) {
            layout_no_datas.setVisibility(View.VISIBLE);
            tvRightBlue.setVisibility(View.GONE);
            pullLoadMoreRecyclerView.setVisibility(View.GONE);
        } else {
            tvRightBlue.setVisibility(View.VISIBLE);
            layout_no_datas.setVisibility(View.GONE);
            pullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
            adapter.addAllData((GoodsCraListBean.DataBean.ListBean) lists);
            adapter.addIndex(1);
            for (int i = 0; i < lists.size(); i++) {
                for (int j = 0; j < lists.get(i).getGoods().size(); j++) {
                    lists.get(i).getGoods().get(j).setIs_selected("1");
                }
            }

            cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        adapter.addIndex(1);
                        for (int i = 0; i < lists.size(); i++) {
                            for (int j = 0; j < lists.get(i).getGoods().size(); j++) {
                                lists.get(i).getGoods().get(j).setIs_selected("1");
                            }
                        }
                    } else {
                        adapter.addIndex(0);
                        for (int i = 0; i < lists.size(); i++) {
                            for (int j = 0; j < lists.get(i).getGoods().size(); j++) {
                                lists.get(i).getGoods().get(j).setIs_selected("0");
                            }
                        }
                    }
                    computationsPrice();
                }
            });
        }
        cbCheck.setChecked(false);
        computationsPrice();
    }

    @Override
    public void deleteGoodsCarSuccreed() {
        lists.clear();
        state = 0;
        mPresenter.getGoodsCar();
    }

    @Override
    public void getGoodsInfoSucceed() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).getGoods().size(); j++) {
                if (lists.get(i).getGoods().get(j).getIs_selected().equals("1")) {
                    if (sb.length() != 0) {
                        sb.append(",");
                    }
                    sb.append(lists.get(i).getGoods().get(j).getGoods_id());
                }
            }
        }
        Intent intent = new Intent(getActivity(), ClearingActivity.class);
        intent.putExtra("goods_ids", sb.toString());
        startActivity(intent);
    }

    @Override
    public void btnClickable(boolean clickable) {
        tvPay.setClickable(clickable);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_right_blue, tv_add_cart, R.id.tv_left_blue, R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right_blue:
                if (state == 0) {
                    tvRightBlue.setText("完成");
                    tvPay.setText("删除");
                    tvLeftBlue.setVisibility(View.GONE);
                    ll_total.setVisibility(View.GONE);
                    state = 1;
                } else {
                    tvRightBlue.setText("编辑");
                    tvPay.setText("去结算");
                    tvLeftBlue.setVisibility(View.GONE);
                    ll_total.setVisibility(View.VISIBLE);
                    state = 0;
                }
                break;
            case tv_add_cart:
                final StringBuffer sb = new StringBuffer();
                for (int i = 0; i < lists.size(); i++) {
                    for (int j = 0; j < lists.get(i).getGoods().size(); j++) {
                        if (lists.get(i).getGoods().get(j).getIs_selected().equals("1")) {
                            if (sb.length() != 0) {
                                sb.append(",");
                            }
                            sb.append(lists.get(i).getGoods().get(j).getGoods_id());
                        }
                    }
                }

                if (state == 0) {
                    if (price == 0) {
                        ToastUtil.showToast("请选择商品");
                    } else {
                        mPresenter.getGoodsInfo(sb.toString());
                    }
                } else {
                    if (TextUtils.isEmpty(sb.toString())) {
                        ToastUtil.showToast("请选择商品");
                        return;
                    }
                    CenterTiteActionDialog dialog = new CenterTiteActionDialog(getActivity());
                    dialog.setActionString("确定从购物车中删除所有选中的商品吗？", "确定", "取消", "删除商品");
                    dialog.setActionListener(new CenterTiteActionDialog.ActionLisenter() {
                        @Override
                        public void sureAction() {
                            //确定
                            mPresenter.deleteGoodsCar(sb.toString());
                        }

                        @Override
                        public void cancelAction() {

                        }
                    });
                    dialog.show();

                }
                break;
            case R.id.tv_left_blue:
                tvRightBlue.setText("编辑");
                tvPay.setText("去结算");
                tvRightBlue.setVisibility(View.VISIBLE);
                ll_total.setVisibility(View.VISIBLE);
                tvLeftBlue.setVisibility(View.GONE);
                state = 0;
                break;
            case R.id.btn:
                mOrderClickListener.onOrderCilck();
                break;
        }
    }

    public void computationsPrice() {
        try {
            price = 0.00;
            for (int i = 0; i < lists.size(); i++) {
                for (int j = 0; j < lists.get(i).getGoods().size(); j++) {
                    if (lists.get(i).getGoods().get(j).getIs_selected().equals("1")) {
                        price = price + Double.parseDouble(lists.get(i).getGoods().get(j).getPrice()) *
                                Integer.parseInt(lists.get(i).getGoods().get(j).getBuy_number());
                    }
                }
            }
            if (price == null) {
                tv_price.setText("¥" + 0.00);
            } else {

                BigDecimal bg = new BigDecimal(price);
//        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(f1);
                tv_price.setText("¥" + bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }

        } catch (Exception e) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        tvRightBlue.setText("编辑");
        tvPay.setText("去结算");
        tvLeftBlue.setVisibility(View.GONE);
        ll_total.setVisibility(View.VISIBLE);
        state = 0;
        lists.clear();
        mPresenter.getGoodsCar();
    }

    public OrderClickListener mOrderClickListener;

    public void setmClickListener(OrderClickListener mOrderClickListener) {
        this.mOrderClickListener = mOrderClickListener;
    }

    public interface OrderClickListener {
        void onOrderCilck();
    }

}
