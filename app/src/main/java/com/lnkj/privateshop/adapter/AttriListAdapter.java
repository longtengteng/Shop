package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.AtrirListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class AttriListAdapter extends BaseAdapter {
    private Context mContext;
    private   List<AtrirListBean>  lists;

    public AttriListAdapter(Context mContext, List<AtrirListBean> lists) {
        this.mContext = mContext;
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
    public View getView(final int i, View contentview, ViewGroup viewGroup) {

        if (contentview==null){
            contentview = View.inflate(mContext, R.layout.list_item_size_goods, null);
        }
        CheckBox mCheckbox = (CheckBox) contentview.findViewById(R.id.mCheckbox);
        LinearLayout ll_size = (LinearLayout) contentview.findViewById(R.id.ll_size);
        mCheckbox.setText(lists.get(i).getText());

        mCheckbox.setChecked(lists.get(i).getIscheck());

        System.out.println("_________"+lists.get(i).getIscheck());

        ll_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int j = 0; j < lists.size(); j++) {
                    lists.get(j).setIscheck(false);
                }
                    if (lists.get(i).getIscheck()) {
                        lists.get(i).setIscheck(false);
                    } else {
                        lists.get(i).setIscheck(true);
                    }
                notifyDataSetChanged();
            }
        });
        return contentview;
    }

}
