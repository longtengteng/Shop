package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.domain.EaseUser;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.chat.ChatActivity;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.entity.DynamicragBean;
import com.lnkj.privateshop.ui.goods.GoodsInfoActivity;
import com.lnkj.privateshop.ui.login.LoginActivity;
import com.lnkj.privateshop.utils.PreferencesUtils;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.MyGridView;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class DynamicragAdapter extends RecyclerView.Adapter<DynamicragAdapter.ViewHolder> {

//    private boolean isOpen = false;
//
//    private LinearLayout.LayoutParams mLayoutParams;
//    private int mMaxlines = 2;//设定显示的最大行数
//    private int maxLine;//真正的最大行数

    private Context mContext;
    List<DynamicragBean.DataBean> orderlist;

    public DynamicragAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void addAllData(List<DynamicragBean.DataBean> orderlist) {
        this.orderlist = orderlist;
        notifyDataSetChanged();
    }

    public void clearData() {
        this.orderlist.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_tiem_dynamicre, parent, false);
        return new ViewHolder(v);
    }


       int mShortHeight;//限定行数高度
        int mLongHeight;//展开全文高度

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        final DynamicragBean.DataBean bean = orderlist.get(position);
        holder.itemView.setTag(position);
        try {
        Glide
                .with(mContext)
                .load(Constants.Base_URL +bean.getShop_info().getShop_logo())
                .error(R.mipmap.bg_img)
                .into(holder.ivHead);

        holder.tvName.setText(bean.getShop_info().getShop_name());
//        holder.tvContext.setText(bean.getContent());
        holder.expTv1.setText(bean.getContent());
            System.out.println(bean.getContent());

//
//            int width =   holder.tvContext.getMeasuredWidth();//宽度
//            TextView textView = new TextView(mContext);
//            textView.setText(bean.getContent());
//            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
//            textView.setMaxLines(mMaxlines);
//            int measureSpecWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);//宽度match
//            int measureSpecHeight = View.MeasureSpec.makeMeasureSpec(1920, View.MeasureSpec.AT_MOST);//高度wrap，1920为最大高度
//            textView.measure(measureSpecWidth, measureSpecHeight);
//            mShortHeight = textView.getMeasuredHeight();

//            holder.tvContext.post(new Runnable() {
//                @Override
//                public void run() {
//                    maxLine =   holder.tvContext.getLineCount();//获取完全显示饿行数
//
//                    mLongHeight = getLongHeight();//获取完全显示需要的高度
//
//                    Log.d("MainActivity", "长的" + mLongHeight + ",短的=" + mShortHeight);
//                    if (mLongHeight <= mShortHeight) {
////                        holder.tv_toggle.setVisibility(View.GONE);//完全显示需要的高度小于低的高度的时候，不需要展开
//                    }
//                }
//            });
            //显示少的高度
//            mLayoutParams = (LinearLayout.LayoutParams)  holder.tvContext.getLayoutParams();
//            mLayoutParams.height = mShortHeight;
//            holder.tvContext.setLayoutParams(mLayoutParams);
//            holder.tv_toggle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    toggle(holder.tvContext,holder.tv_toggle);//开始展开收起动画
//                }
//            });

        holder.tvTime.setText(bean.getAdd_time());
            int favorite = bean.getFavorite_shop();
            if (favorite==1){
                holder.tvBtnFollow.setText("已关注");
            }else {
                holder.tvBtnFollow.setText("+关注");
            }
        holder.tvBtnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOrderClickListener!=null){
                    mOrderClickListener.oncollectShop(position);
                }
            }
        });
        holder.tvBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast("分享");
            }
        });
        holder.tvBtnPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             Boolean   is_bogin = PreferencesUtils.getBoolean(mContext, "is_bogin");
               if (!is_bogin) {
                    ToastUtil.showToast("您还没有登录，请去登录");
                    Intent   intent = new Intent(mContext, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                    return;
                }
                try {
                String meemchat = PreferencesUtils.getString(mContext,"emchat_username");

                if (meemchat.equals(bean.getShop_info().getEmchat_username())){
                    ToastUtil.showToast("不能跟自己聊天");
                    return;
                }
//                    UserDao userDao = new UserDao(mContext);
                    EaseUser easeUser = new EaseUser(bean.getShop_info().getEmchat_username());
                    easeUser.setAvatar(Constants.Base_IMG_URL+bean.getShop_info().getShop_logo());
                    easeUser.setNickname(bean.getShop_info().getNickname());
//                    userDao.saveContact(easeUser);


                    List<EaseUser> users = new ArrayList<EaseUser>();
                    users.add(easeUser);
                    DemoHelper.getInstance().updateContactList(users);


                    Intent intent1 = new Intent(mContext, ChatActivity.class);
                    intent1.putExtra("userId", bean.getShop_info().getEmchat_username());
                    mContext.startActivity(intent1);
                }catch (Exception e){
                    ToastUtil.showToast("请登录");
                }

            }
        });
            DynamlistAdapter adapter = new DynamlistAdapter(bean.getGoods_list(),mContext);
            holder.myGridView.setAdapter(adapter);
            holder.myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra("goods_id",bean.getGoods_list().get(i).getGoods_id());
                    mContext.startActivity(intent);
                }
            });
        holder.ll_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOrderClickListener!=null){
                    mOrderClickListener.onOrderCilck(bean.getShop_id());
                }
            }
        });

        }catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        return orderlist == null ? 0 : orderlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_head)
        CircleImageView ivHead;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_time)
        TextView tvTime;
//        @Bind(R.id.tv_context)
//        TextView tvContext;
        @Bind(R.id.myGridView)
        MyGridView myGridView;
        @Bind(R.id.tv_btn_private)
        TextView tvBtnPrivate;
        @Bind(R.id.tv_btn_share)
        TextView tvBtnShare;
        @Bind(R.id.tv_btn_follow)
        TextView tvBtnFollow;
        @Bind(R.id.ll_parent)
        LinearLayout ll_parent;
//        @Bind(R.id.tv_toggle)
//        TextView tv_toggle;
        @Bind(R.id.expand_text_view)
        ExpandableTextView expTv1;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
//    private int getLongHeight() {//长文高度
//
//        int height = mShortHeight / mMaxlines * maxLine;
//
//        return height;
//    }
    public OrderClickListener mOrderClickListener;

    public void setmClickListener(OrderClickListener mOrderClickListener) {
        this.mOrderClickListener = mOrderClickListener;
    }

    public interface OrderClickListener {
        void onOrderCilck(String goodsId);
        void oncollectShop(int position);

    }
//    private void toggle(final TextView tvContext, final TextView tv_toggle) {
//        ValueAnimator animator;
//        if (isOpen) {
//            animator = ValueAnimator.ofInt(mLongHeight, mShortHeight);
//            isOpen = false;
//        } else {
//            animator = ValueAnimator.ofInt(mShortHeight, mLongHeight);
//            isOpen = true;
//        }
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                Integer value = (Integer) valueAnimator.getAnimatedValue();
//                mLayoutParams.height = value;
//                tvContext.setLayoutParams(mLayoutParams);
//            }
//        });
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//                if (isOpen) {
//                    tv_toggle.setText("收起全文");
//                } else {
//                    tv_toggle.setText("展开全文");
//                }
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
//        animator.setDuration(500);
//        animator.start();
//    }
}
