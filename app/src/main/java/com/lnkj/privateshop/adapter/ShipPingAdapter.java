package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.ShipPingBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class ShipPingAdapter extends BaseAdapter {
    private Context mcontext;
    private List<ShipPingBean.DataBean> lists;

    public ShipPingAdapter(Context mcontext, List<ShipPingBean.DataBean> lists) {
        this.mcontext = mcontext;
        this.lists = lists;
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
        if (view==null){
            view=View.inflate(mcontext, R.layout.list_item_diglog_ship,null);
        }
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        final CheckBox cb = (CheckBox) view.findViewById(R.id.cb_check);
        LinearLayout ll_check = (LinearLayout) view.findViewById(R.id.ll_check);
        tvName.setText(lists.get(i).getName());

        if (lists.get(i).isIncheck()){
            cb.setChecked(true);
        }else {
            cb.setChecked(false);
        }
        ll_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lists.get(i).isIncheck()){
                    for (int j = 0; j < getCount(); j++) {
                        lists.get(j).setIncheck(false);
                    }
                }else {
                    for (int j = 0; j < getCount(); j++) {
                        lists.get(j).setIncheck(false);
                    }
                    lists.get(i).setIncheck(true);

                }
                notifyDataSetChanged();
            }
        });
        return view;
    }
    public String getChickId(){
        String id ="";
        try {
        for (int i = 0; i < lists.size(); i++) {
            if ( lists.get(i).isIncheck()){
                id = lists.get(i).getExpress_id();
            }

        }

        }catch (Exception e){}

        return id;
    }
    public String getChickName(){
        String name ="";
        try {
            for (int i = 0; i < lists.size(); i++) {
                if ( lists.get(i).isIncheck()){
                    name = lists.get(i).getName();
                    return name;
                }
            }

        }catch (Exception e){}


        return name;
    }

}
