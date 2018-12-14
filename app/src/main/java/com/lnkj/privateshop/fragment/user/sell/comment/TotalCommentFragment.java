package com.lnkj.privateshop.fragment.user.sell.comment;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.adapter.ClientShopCommentListAdapter;
import com.lnkj.privateshop.entity.ClientCommentBean;
import com.lnkj.privateshop.ui.mybuy.sell.order.appraiseadmin.CommentAdminActivity;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.utils.UiUtils;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class TotalCommentFragment extends BaseFragment implements TotalCommentContract.View,PullLoadMoreRecyclerView.PullLoadMoreListener {
    private int index ;
    private String shopId;
    private String type;
    int p = 1;
    private TotalCommentPresenter mPresenter = new TotalCommentPresenter(this,getActivity());

    PullLoadMoreRecyclerView mpullLoadMoreRecyclerView;
    LinearLayout layout_no_datas;
    ClientShopCommentListAdapter adapter ;
    private View rootview;
    private EditText etContent;
    private Button  mButton;
    private int posent;
    public static TotalCommentFragment newInstance() {
        TotalCommentFragment fragment = new TotalCommentFragment();
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.fragment_total_comment;
    }

    @Override
    protected void init(View view) {
        rootview = view;
        super.init(view);
        mpullLoadMoreRecyclerView= (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        layout_no_datas= (LinearLayout) view.findViewById(R.id.layout_no_datas);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getShomComment(shopId,p,index+1+"");
    }

    @Override
    protected void loadDatas() {
        super.loadDatas();
        index= getArguments().getInt("index");
        shopId = getArguments().getString("shopID");
        mPresenter.setToken(token);
        //设置是否可以下拉刷新
        mpullLoadMoreRecyclerView.setPullRefreshEnable(true);
        mpullLoadMoreRecyclerView.setPushRefreshEnable(true);
        mpullLoadMoreRecyclerView.setFooterViewText("拼命加载中");
        mpullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mpullLoadMoreRecyclerView.setLinearLayout();
        mpullLoadMoreRecyclerView.setRefreshing(true);
        mpullLoadMoreRecyclerView.setLinearLayout();
        adapter = new ClientShopCommentListAdapter(getActivity());
        mpullLoadMoreRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ClientShopCommentListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                posent = i;
                showPopupWindowAttri(lists.get(i).getComment_id());
//                UiUtils.ShowKeyBoard(etContent);


            }
        });
    }
    private PopupWindow mPopWindow;
    private void showPopupWindowAttri(final String comment_id) {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_layout_comment, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        etContent = (EditText) contentView.findViewById(R.id.et_content);

        etContent.requestFocus();
        etContent.setFocusable(true);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//这里给它设置了弹出的时间，
        imm.toggleSoftInput(1000, InputMethodManager.HIDE_NOT_ALWAYS);


        LinearLayout ll_parent = (LinearLayout) contentView.findViewById(R.id.ll_parent);
        ll_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        mButton = (Button) contentView.findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etContent.getText().toString())){
                    ToastUtil.showToast("请输入内容");
                }else {
                mPresenter.putCommtent(etContent.getText().toString(),comment_id);
                    mButton.setClickable(false);
                }


            }
        });

        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {
        if (mButton!=null){
            mButton.setClickable(true);
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        mpullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void setTitle(String s) {

    }

    List<ClientCommentBean.DataBean> lists = new ArrayList<>();
    @Override
    public void getShopCommentSucceed(ClientCommentBean beans) {
            if (p==1){
                lists.clear();
            }

        if (index==0){
            for (int i = 0; i < beans.getData().size(); i++) {
                if (beans.getData().get(i).getReview()==null){
                    lists.add(beans.getData().get(i));
                }
            }
        }else if (index==1){
            lists.addAll(beans.getData());
        }
        adapter.addAllData(lists);
        if (lists.size()==0){
            layout_no_datas.setVisibility(View.VISIBLE);
            mpullLoadMoreRecyclerView.setVisibility(View.GONE);
        }else {
            layout_no_datas.setVisibility(View.GONE);
            mpullLoadMoreRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void puCommentSuccreed() {
        if (mButton!=null){
            mButton.setClickable(true);
        }
                        if (mPopWindow!=null){
                    mPopWindow.dismiss();
                }
                etContent.setText("");
        UiUtils.HideKeyboard(etContent);
        lists.remove(posent);
        adapter.notifyDataSetChanged();

          CommentAdminActivity commentAct = (CommentAdminActivity) getActivity();
            commentAct.getDataFromServer();
    }

    @Override
    public void putCommentFailure() {
        ToastUtil.showToast("回复失败");
        if (mButton!=null){
            mButton.setClickable(true);
        }
        if (mPopWindow!=null){
            mPopWindow.dismiss();
        }
        etContent.setText("");
        UiUtils.HideKeyboard(etContent);
    }

    @Override
    public void onRefresh() {
        p=1;
        mPresenter.getShomComment(shopId,p,index+1+"");
    }

    @Override
    public void onLoadMore() {
        p++;
        mPresenter.getShomComment(shopId,p,index+1+"");
    }
}
