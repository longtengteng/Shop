package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.shopcommentBean;
import com.lnkj.privateshop.view.picture.NineGridTestLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class ShopCommentListAdapter extends RecyclerView.Adapter<ShopCommentListAdapter.ViewHolder> {


    private Context mContext;
    List<shopcommentBean.DataBean> beans;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private OnItemClickListener mOnItemClickListener = null;

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public ShopCommentListAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void addAllData(List<shopcommentBean.DataBean> beans) {
        this.beans = beans;

        notifyDataSetChanged();
    }

    public void clearData() {
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item_shop_comment, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        holder.tvName.setText(beans.get(position).getUser_name());
        holder.tvContext.setText(beans.get(position).getContent());
        holder.rcRateMass.setRating(Float.parseFloat(beans.get(position).getRank()));
        holder.tvTime.setText(beans.get(position).getAdd_time());
        if (beans.get(position).getReview()==null){
            holder.llZhuiping.setVisibility(View.GONE);
        }else {
            holder.llZhuiping.setVisibility(View.VISIBLE);
            holder.tvChuiping.setText(beans.get(position).getReview().getContent());
        }
        Glide
                .with(mContext)
                .load(Constants.Base_URL +beans.get(position).getHead_pic())
                .error(R.mipmap.de_photo)
                .into(holder.imgShopHead);

        List<shopcommentBean.DataBean.CommentImgBean> imges   = beans.get(position).getComment_img();
        //图片
        List<String> imgess = new ArrayList<>();
        if (imges != null) {
            for (int i = 0; i < imges.size(); i++) {
                imgess.add(Constants.Base_URL + imges.get(i).getImg_url());
//                System.out.println(Constants.Base_URL + imges.get(i).getImg_url());
            }
            holder.mNineGridTestLayout.setVisibility(View.VISIBLE);
            holder.mNineGridTestLayout.setIsShowAll(false); // 当传入的图片数超过9张时，是否全部显示
            holder.mNineGridTestLayout.setSpacing(10); // 动态设置图片之间的间隔
            holder.mNineGridTestLayout.setUrlList(imgess); // 最后再设置图片url
            holder.mNineGridTestLayout.setUrlDa(imgess);
        } else {
            holder.mNineGridTestLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return beans==null?0:beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_shop_head)
        CircleImageView imgShopHead;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.rc_rate_mass)
        RatingBar rcRateMass;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_context)
        TextView tvContext;
        @Bind(R.id.tv_chuiping)
        TextView tvChuiping;
        @Bind(R.id.ll_zhuiping)
        LinearLayout llZhuiping;
        @Bind(R.id.mNineGridTestLayout)
        NineGridTestLayout mNineGridTestLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
