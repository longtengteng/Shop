package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.EditOrderPriceBean;
import com.lnkj.privateshop.utils.CashierInputFilter;

import java.math.BigDecimal;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 创建时间： 2017/9/22 0022.
 * 创建人：    赵林
 * 功能描述：编辑商品的adapter
 */

public class EditOrderAdapter extends BaseAdapter {
    private Context mContext;
    private List<EditOrderPriceBean.DataBean.OrderGoodsBean> lists;
    double dnewPrice;


    public OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void EditClick();
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public EditOrderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addData(List<EditOrderPriceBean.DataBean.OrderGoodsBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public EditOrderPriceBean.DataBean.OrderGoodsBean getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {


        final ViewHolder holder;
//        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_edit_order, null);
            holder = new ViewHolder(view);
//            view.setTag(holder);
//        } else {
//            holder = (ViewHolder) view.getTag();
//        }
        holder.tvGoodsName.setText(lists.get(i).getGoods_name());
        holder.tvGoodsPrice.setText("¥" + lists.get(i).getPrice());
        holder.tvGoodsCount.setText("数量" + lists.get(i).getTotal_buy_number());
        holder.tvEPrice.setText("¥" + lists.get(i).getAfter_money());
//        holder.etCountPrice.setText(lists.get(i).getAfter_money());

        InputFilter[] filters = {new CashierInputFilter()};
        holder.etCountPrice.setFilters(filters);

//        lists.get(i).setNewPrice(lists.get(i).getAfter_money());
        final String oldPrice = lists.get(i).getAfter_money();

        Glide.with(mContext).load(Constants.Base_URL + lists.get(i).getGoods_img())
                .error(R.mipmap.bg_img)
                .placeholder(R.mipmap.bg_img)
                .into(holder.ivGoodsImg);


        holder.rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int in) {
                switch (in) {
                    case R.id.rb_max:
                        lists.get(i).setIndex("1");
//                           if (!TextUtils.isEmpty(holder.etCountPrice.getText().toString())){
//                               dnewPrice = Double.parseDouble(holder.etCountPrice.getText().toString()) + Double.parseDouble(oldPrice);
//                           }
                        break;
                    case R.id.rb_small:
                        lists.get(i).setIndex("0");
//                            if (!TextUtils.isEmpty(holder.etCountPrice.getText().toString())){

//                                dnewPrice = Double.parseDouble(oldPrice) -Double.parseDouble(holder.etCountPrice.getText().toString());
//                                if (dnewPrice<0){
//                                    dnewPrice=0;
//                                }

//                            }
                        break;

                }

                String newPrice = holder.etCountPrice.getText().toString();
                Double goods_price;
                if (!TextUtils.isEmpty(newPrice)) {
                    lists.get(i).setChangePrice(newPrice);
                    if (lists.get(i).getIndex().equals("1")){
                        goods_price=   Double.parseDouble(oldPrice)+ Double.parseDouble(newPrice);

                    }else {
                        goods_price=   Double.parseDouble(oldPrice)- Double.parseDouble(newPrice);
                        if (goods_price<0){
                            goods_price=0.00d;
                        }
                    }

                    BigDecimal b2 = new BigDecimal(goods_price);
                    lists.get(i).setAfter_money( b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"");
                    holder.tvEPrice.setText("¥" + lists.get(i).getAfter_money());
                    mOnItemClickListener.EditClick();
                }


//                if (!TextUtils.isEmpty(holder.etCountPrice.getText().toString())) {
//                    mOnItemClickListener.EditClick();
//                        BigDecimal b = new BigDecimal(dnewPrice);
//                        double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//                        holder.tvEPrice.setText("¥"+ f1);
//                        lists.get(i).setNewPrice(f1+"");
//                     mOnItemClickListener.EditClick(f1+"",i);


//                }
            }
        });
//        holder.rbMax.setChecked(true);

        holder.etCountPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int in, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newPrice = holder.etCountPrice.getText().toString();
                Double goods_price;


                if (!TextUtils.isEmpty(newPrice)) {
                    lists.get(i).setChangePrice(newPrice);
                    if (lists.get(i).getIndex().equals("1")){
                        goods_price=   Double.parseDouble(oldPrice)+ Double.parseDouble(newPrice);

                    }else {
                        goods_price=   Double.parseDouble(oldPrice)- Double.parseDouble(newPrice);
                        if (goods_price<0){
                            goods_price=0.00d;
                        }
                    }

                    BigDecimal b2 = new BigDecimal(goods_price);
                    lists.get(i).setAfter_money( b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"");
                    holder.tvEPrice.setText("¥" + lists.get(i).getAfter_money());
                    mOnItemClickListener.EditClick();
                } else {
                    lists.get(i).setAfter_money(oldPrice);
                    holder.tvEPrice.setText("¥" + lists.get(i).getAfter_money());
                    lists.get(i).setChangePrice("0");
                    mOnItemClickListener.EditClick();
//                        etPrice=holder.etCountPrice.getText().toString();

//                    if (holder.rbMax.isChecked()){
//                        dnewPrice = Double.parseDouble(etPrice) + Double.parseDouble(oldPrice);
//                    }else {
//                        dnewPrice = Double.parseDouble(oldPrice) -Double.parseDouble(etPrice);
//                        if (dnewPrice<0){
//                            dnewPrice=0;
//                        }
//                    }
//                    lists.get(i).setChangePrice(etPrice);
//
//                    BigDecimal b = new BigDecimal(dnewPrice);
//                    double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//
//                    holder.tvEPrice.setText("¥"+ f1);
//                    lists.get(i).setNewPrice(f1+"");
//                    mOnItemClickListener.EditClick(holder.etCountPrice.getText().toString(), i);
                }
            }
        });


        return view;
    }

    static class ViewHolder {
        @Bind(R.id.iv_goods_img)
        ImageView ivGoodsImg;
        @Bind(R.id.tv_goods_name)
        TextView tvGoodsName;
        @Bind(R.id.tv_goods_count)
        TextView tvGoodsCount;
        @Bind(R.id.tv_goods_price)
        TextView tvGoodsPrice;
        @Bind(R.id.rb_small)
        RadioButton rbSmall;
        @Bind(R.id.rb_max)
        RadioButton rbMax;
        @Bind(R.id.rg_1)
        RadioGroup rg1;
        @Bind(R.id.et_count_price)
        EditText etCountPrice;
        @Bind(R.id.tv_e_price)
        TextView tvEPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
