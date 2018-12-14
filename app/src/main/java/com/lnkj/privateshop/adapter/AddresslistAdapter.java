package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.AddressListBean;
import com.lnkj.privateshop.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class AddresslistAdapter extends BaseAdapter {
    private Context mContext;
    private List<AddressListBean.DataBean> lists;
    private int index=0;
    public AddresslistAdapter(Context mContext, List<AddressListBean.DataBean> lists) {
        this.mContext = mContext;
        this.lists = lists;
    }
    public void address(int index){
        this.index=index;
    }
    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
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
        final ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_address, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(lists.get(i).getConsignee());
        holder.tvPhone.setText(lists.get(i).getMobile());
        holder.tvAddress.setText(lists.get(i).getAddress());
        if (lists.get(i).getIs_default() == 1) {
            holder.cbAddress.setChecked(true);
            holder.cbAddress.setText("  默认地址");
            holder.cbAddress.setTextColor(Color.parseColor("#FF7722"));
        } else {
            holder.cbAddress.setChecked(false);
            holder.cbAddress.setText("  设为默认");
            holder.cbAddress.setTextColor(Color.parseColor("#333333"));
        }
        if (index==0){
            holder.tvDeit.setVisibility(View.VISIBLE);
            holder.tvDelete.setVisibility(View.VISIBLE);
            holder.tv_lin.setVisibility(View.VISIBLE);
        }else {
            holder.tvDeit.setVisibility(View.GONE);
            holder.tvDelete.setVisibility(View.GONE);
            holder.tv_lin.setVisibility(View.GONE);
        }


        holder.ll_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lists.get(i).getIs_default() == 1){
                    ToastUtil.showToast("不能取消默认地址，请设置另一处地址为默认.");
                } else {

                if (lists.get(i).getIs_default()==1){
                    lists.get(i).setIs_default(0);
//                    mAddressClickListener.onSetupCilck(lists,i,0);
//                    holder.cbAddress.setChecked(false);
                }else {
                    lists.get(i).setIs_default(1);
//                    holder.cbAddress.setChecked(true);
                    mAddressClickListener.onSetupCilck(lists,i,1);
                }
                for (int j = 0; j < lists.size(); j++) {
                    if (j!=i){
                    lists.get(j).setIs_default(0);
                    }
                }
                notifyDataSetChanged();
                }



            }
        });
        holder.tvDeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mAddressClickListener.onEditCilck(lists,i);
            }
        });
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddressClickListener.onDeleteCilck(lists,i);
            }
        });
        return view;
    }


    static class ViewHolder {
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.cb_address)
        CheckBox cbAddress;
        @Bind(R.id.tv_phone)
        TextView tvPhone;
        @Bind(R.id.tv_address)
        TextView tvAddress;
        @Bind(R.id.tv_deit)
        TextView tvDeit;
        @Bind(R.id.tv_delete)
        TextView tvDelete;
    @Bind(R.id.tv_lin)
            TextView tv_lin;
        @Bind(R.id.ll_cb)
        LinearLayout ll_cb;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public AddressClickListener mAddressClickListener;
    public void setAddressClickListener( AddressClickListener mAddressClickListener) {
        this.mAddressClickListener = mAddressClickListener;
    }

    public interface AddressClickListener {
        void onDeleteCilck(List<AddressListBean.DataBean> lists, int position ); //删除订单

        void onEditCilck( List<AddressListBean.DataBean> lists,int position); //查看物流

        void onSetupCilck( List<AddressListBean.DataBean> lists,int position,int flag);

    }
}
