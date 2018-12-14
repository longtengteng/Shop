package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.AddressBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class AddressAdapter extends BaseAdapter {
    private Context mContext;
    private List<AddressBean.DataBean> lists;

    public AddressAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addData(List<AddressBean.DataBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lists==null?0:lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_addressto, null);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tvAddress.setText(lists.get(i).getRegion_name());
         holder.cb.setChecked(lists.get(i).getIscheck());
        holder.llCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lists.get(i).setIscheck(!lists.get(i).getIscheck());
                notifyDataSetChanged();
            }
        });

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.tv_address)
        TextView tvAddress;
        @Bind(R.id.cb)
        CheckBox cb;
        @Bind(R.id.ll_check)
        LinearLayout llCheck;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public String getAddressName(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getIscheck()){
            if (sb.length()!=0){
                sb.append(",");
            }
            sb.append(lists.get(i).getRegion_name());

            }
        }

        return sb.toString();
    }
    public String getAddressId(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getIscheck()){
                if (sb.length()!=0){
                    sb.append(",");
                }
                sb.append(lists.get(i).getId());

            }
        }

        return sb.toString();
    }
}
