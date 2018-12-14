package com.lnkj.privateshop.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.BaseActivity;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.view.CenterActionDialog;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;


/**
 * Created by zhangyf on 2017/3/7.
 */

public class AlbumReturnAdapter extends BaseAdapter {
    ArrayList<ImageItem> images;
    private Activity mContext;

    public AlbumReturnAdapter(BaseActivity context, ArrayList<ImageItem> images) {
        this.mContext = context;
        this.images = images;

    }

    public void bindList(  ArrayList<ImageItem> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    public int getCount() {
        // 多返回一个用于展示添加图标
        if (images == null) {
            return 1;
        } else if (images.size() == 9) {
            return 9;
        } else {
            return images.size() + 1;
        }
    }

    public Object getItem(int position) {
        if (images != null
                && images.size() ==9) {
            return images.get(position);
        } else if (images == null || position - 1 < 0
                || position > images.size()) {
            return null;
        } else {
            return images.get(position - 1);
        }
    }

    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    public View getView(final int position, View convertView, ViewGroup parent) {
        // 所有Item展示不满一页，就不进行ViewHolder重用了，避免了一个拍照以后添加图片按钮被覆盖的奇怪问题
        convertView = View.inflate(mContext, R.layout.item_album, null);
        ImageView imageIv = (ImageView) convertView
                .findViewById(R.id.item_grid_image);
        TextView tv_fengmiantu = (TextView) convertView.findViewById(R.id.tv_fengmiantu);
        final ImageView imageDel = (ImageView) convertView
                .findViewById(R.id.item_grid_image_delete);

        if (isShowAddItem(position)) {
            Glide.with(mContext).load
                    (R.mipmap.btn_addgood)
                    .into(imageIv);
            imageDel.setVisibility(View.GONE);
            tv_fengmiantu.setVisibility(View.GONE);
        } else {

            tv_fengmiantu.setVisibility(View.GONE);

            final ImageItem item = images.get(position);

                Glide.with(mContext).load
                        ( item.path).error(R.mipmap.bg_img)
                        .placeholder(R.mipmap.bg_img)
                        .into(imageIv);

        }

        imageDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialogShow(position);
            }
        });
        return convertView;
    }

    private boolean isShowAddItem(int position) {
        int size = images == null ? 0 : images.size();
        return position == size;
    }

    public void deleteDialogShow(final int position) {

        CenterActionDialog dialog = new CenterActionDialog(mContext);
        dialog.setActionString("您确认删除吗？",
                "确认",
                "取消");
        dialog.setActionListener(new CenterActionDialog.ActionLisenter() {
            @Override
            public void sureAction() {
                images.remove(position);
                notifyDataSetChanged();

            }

            @Override
            public void cancelAction() {
            }
        });
        dialog.show();
    }


}