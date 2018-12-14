package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.ColorBean2;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class CoodsAdapter extends BaseAdapter {
    private Context mContext;
    public List<ColorBean2.DataBean> lists;
    public CoodsAdapter(Context mContext) {
        this.mContext = mContext;

    }
    public void addData(List<ColorBean2.DataBean> lists){
        this.lists=lists;
        notifyDataSetChanged();
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
    public View getView(final int i, View contentview, ViewGroup viewGroup) {
        View view = View.inflate(mContext, R.layout.list_item_goods, null);

        RelativeLayout rl_color = (RelativeLayout) view.findViewById(R.id.rl_color);
        ImageView img_color = (ImageView) view.findViewById(R.id.img_color);
        TextView tv_color = (TextView) view.findViewById(R.id.tv_color);
        final ImageView cb_color = (ImageView) view.findViewById(R.id.cb_color);

        Glide
                .with(mContext)
                .load(Constants.Base_URL + lists.get(i).getColor_img())
                .error(R.mipmap.bg_img)
                .into(img_color);

        tv_color.setText(lists.get(i).getItem());
        if (lists.get(i).getIscheck()) {
            cb_color.setVisibility(View.VISIBLE);
        } else {
            cb_color.setVisibility(View.GONE);
        }

        rl_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    if (lists.get(i).getIscheck()) {
                        for (int j = 0; j < lists.size(); j++) {
                            lists.get(j).setIscheck(false);
                        }
                    } else {
                        for (int j = 0; j < lists.size(); j++) {
                            lists.get(j).setIscheck(false);
                        }
                        lists.get(i).setIscheck(true);
                    }
                } else {
                    if (!lists.get(0).getIscheck()) {
                        if (lists.get(i).getIscheck()) {
                            lists.get(i).setIscheck(false);
                        } else {
                            lists.get(i).setIscheck(true);
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });




        return view;
    }
}
