package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lnkj.privateshop.R;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public class GoodsListGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> strList ;

    public GoodsListGridAdapter(Context mContext, List<String> strList) {
        this.mContext = mContext;
//        if (spec!=null){
//            for (int i = 0; i < spec.size(); i++) {
//                for (int j = 0; j < spec.get(i).getSize().size(); j++) {
//                    strList.add(spec.get(i).getColor()+spec.get(i).getSize().get(j).getSize_name()+" x"+spec.get(i).getSize().get(j).getBuy_number());
//                }
//            }
//        }
this.strList=strList;

    }

    @Override
    public int getCount() {
        return strList==null?0:strList.size();
    }

    @Override
    public Object getItem(int i) {
        return strList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.list_item_text,null);
        TextView textView = (TextView) v.findViewById(R.id.tv_1);
        textView.setText(strList.get(i));

        return v;
    }
}
