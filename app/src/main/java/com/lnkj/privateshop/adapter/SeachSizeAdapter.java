package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.SizeBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class SeachSizeAdapter extends BaseAdapter {
    private Context mContext;
    public List<SizeBean.DataBean> lists;

    public SeachSizeAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void addData(List<SizeBean.DataBean> lists){
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
    if (contentview==null){
        contentview = View.inflate(mContext, R.layout.list_item_size_seach, null);
    }
      CheckBox mCheckbox = (CheckBox) contentview.findViewById(R.id.mCheckbox);
        LinearLayout ll_size = (LinearLayout) contentview.findViewById(R.id.ll_size);
        mCheckbox.setText(lists.get(i).getItem());
        mCheckbox.setChecked(lists.get(i).getIscheck());

        ll_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
//                    }
//                }
                notifyDataSetChanged();
            }
        });
        return contentview;
    }
}
