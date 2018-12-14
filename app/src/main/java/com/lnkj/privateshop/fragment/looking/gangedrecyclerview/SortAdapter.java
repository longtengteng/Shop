package com.lnkj.privateshop.fragment.looking.gangedrecyclerview;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.lnkj.privateshop.R;

import java.util.List;

public class SortAdapter extends RvAdapter<String> {
    private int checkedPosition;



    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        notifyDataSetChanged();
    }

    public SortAdapter(Context context, List<String> list, RvListener listener) {
        super(context, list, listener);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_sort_list;
    }

    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new SortHolder(view, viewType, listener);
    }

    private class SortHolder extends RvHolder<String> {

        private TextView tvName;
        private View mView;

        SortHolder(View itemView, int type, RvListener listener) {
            super(itemView, type, listener);
            this.mView = itemView;
            tvName = (TextView) itemView.findViewById(R.id.tv_sort);
        }

        @Override
        public void bindHolder(String string, int position) {
            tvName.setText(string);
//            if (mCurrentPos == i) {
//                mTextview.setEnabled(true);
//                ll_list_background.setBackgroundResource(R.mipmap.class_chose);
//            } else {
//                ll_list_background.setBackgroundColor(Color.parseColor("#F7F7F7"));
//                mTextview.setEnabled(false);
//            }
            if (position == checkedPosition) {
//                mView.setBackgroundColor(Color.parseColor("#f3f3f3"));
//                tvName.setTextColor(Color.parseColor("#0068cf"));
                tvName.setEnabled(true);
                mView.setBackgroundResource(R.mipmap.class_chose);
            } else {
//                mView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                mView.setBackgroundColor(Color.parseColor("#F7F7F7"));
//                tvName.setTextColor(Color.parseColor("#1e1d1d"));
                tvName.setEnabled(false);
            }
        }

    }
}
