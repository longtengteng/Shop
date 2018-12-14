package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.Express_TemplatBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class CarrIageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Express_TemplatBean> Lists;

    public CarrIageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void AddData(List<Express_TemplatBean> Lists) {
        this.Lists = Lists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Lists == null ? 0 : Lists.size();
    }

    @Override
    public Object getItem(int i) {
        return Lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder ;
//        if (view == null) {
            view = View.inflate(mContext, R.layout.view_crarriag, null);
            holder=new ViewHolder(view);
            view.setTag(holder);

//        }else {
//            holder= (ViewHolder) view.getTag();
//        }
//
        holder.tvWeightTo.setText(Lists.get(i).getExpress_template_name());
        holder.et1.setText(Lists.get(i).getFirst_money());
        holder.et2.setText(Lists.get(i).getAdd_money());

        holder.ivReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddressClickListener.onDeleteCilck(i);
            }
        });
        holder.tvWeightTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddressClickListener.onAddAddoress(i);
            }
        });


        holder.et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int e, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int e, int i1, int i2){
                try {
                Lists.get(i).setFirst_money(holder.et1.getText().toString());
                }catch (Exception f){}


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });


        holder.et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int e, int i1, int i2) {
                try {
                Lists.get(i).setAdd_money( holder.et2.getText().toString());
                }catch (Exception f){ }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.iv_reduce)
        ImageView ivReduce;
        @Bind(R.id.tv_weight_to)
        TextView tvWeightTo;
        @Bind(R.id.ll_1)
        LinearLayout ll1;
        @Bind(R.id.et1)
        EditText et1;
        @Bind(R.id.et2)
        EditText et2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public AddressClickListener mAddressClickListener;
    public void setAddressClickListener( AddressClickListener mAddressClickListener) {
        this.mAddressClickListener = mAddressClickListener;
    }

    public interface AddressClickListener {
        void onDeleteCilck(int position ); //删除订单
        void onAddAddoress(int position);

    }
}
