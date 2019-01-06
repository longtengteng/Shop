package com.lnkj.privateshop.fragment.looking;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lnkj.privateshop.BaseFragment;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.GoodsCategoryBean;
import com.lnkj.privateshop.fragment.looking.gangedrecyclerview.CheckListener;
import com.lnkj.privateshop.fragment.looking.gangedrecyclerview.ItemHeaderDecoration;
import com.lnkj.privateshop.fragment.looking.gangedrecyclerview.RvListener;
import com.lnkj.privateshop.fragment.looking.gangedrecyclerview.SortAdapter;
import com.lnkj.privateshop.fragment.looking.gangedrecyclerview.SortDetailFragment;
import com.lnkj.privateshop.utils.LLog;
import com.lnkj.privateshop.view.ProgreesDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/28 0028
 */

public class LookingFragment extends BaseFragment implements LookingContract.View, CheckListener {
    LookingPresenter mPresenter = new LookingPresenter(this);

    RecyclerView rvSort;
    private SortAdapter mSortAdapter;
    private Context mContext;

    SortDetailFragment mSortDetailFragment;
    private LinearLayoutManager mLinearLayoutManager;
    @Override
    protected int getContentResid() {
        return R.layout.fragment_looking;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        mContext=getActivity();
        rvSort = (RecyclerView) view.findViewById(R.id.rv_sort);
        ButterKnife.bind(view);
    }

    @Override
    protected void loadDatas() {
        super.loadDatas();
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        rvSort.setLayoutManager(mLinearLayoutManager);
        mPresenter.getDataFromService();


    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void showLoading() {
        ProgreesDialog.ProgeesDialogShow();
    }

    @Override
    public void hideLoading() {
        ProgreesDialog.ProgeesDialogDiss();
    }

    @Override
    public void initView() {

    }

    @Override
    public void updateView(int id) {

    }

    @Override
    public void upateFragmentData(int type) {

    }
    private boolean isMoved;
    private int targetPosition;//点击左边某一个具体的item的位置
    private    GoodsCategoryBean beans ;
    @Override
    public void liftData(GoodsCategoryBean beans) {
        LLog.d("数据", "_______");
        //获取asset目录下的资源文件
//        String assetsData = getAssetsData("sort.json");
//        Gson gson = new Gson();
//        mSortBean = gson.fromJson(assetsData, SortBean.class);
//        List<SortBean.CategoryOneArrayBean> categoryOneArray = mSortBean.getCategoryOneArray();


            this.beans=beans;
            List<String> list = new ArrayList<>();
            //初始化左侧列表数据
            for (int i = 0; i < beans.getData().size(); i++) {
                list.add(beans.getData().get(i).getCat_name_mobile());
            }
            mSortAdapter = new SortAdapter(mContext, list, new RvListener() {
                @Override
                public void onItemClick(int id, int position) {
                    if (mSortDetailFragment != null) {
                        isMoved = true;
                        targetPosition = position;
                        setChecked(position, true);
                    }
                }
            });
            rvSort.setAdapter(mSortAdapter);
            createFragment();
    }



    private void setChecked(int position, boolean isLeft) {
        Log.d("p-------->", String.valueOf(position));
        if (isLeft) {
            mSortAdapter.setCheckedPosition(position);
            //此处的位置需要根据每个分类的集合来进行计算
            int count = 0;
            for (int i = 0; i < position; i++) {
                try {
                count += beans.getData().get(i).getChild().size();
                }catch (Exception e){}
            }
            count += position;
            mSortDetailFragment.setData(count);
        } else {
            if (isMoved) {
                isMoved = false;
            } else
                mSortAdapter.setCheckedPosition(position);
            ItemHeaderDecoration.setCurrentTag(String.valueOf(targetPosition));

        }
        moveToCenter(position);

    }
    public void createFragment() {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        mSortDetailFragment = new SortDetailFragment();
        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("right", mSortBean.getCategoryOneArray());
        bundle.putParcelableArrayList("right", beans.getData());
        mSortDetailFragment.setArguments(bundle);
        mSortDetailFragment.setListener(this);
        fragmentTransaction.add(R.id.lin_fragment, mSortDetailFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void check(int position, boolean isScroll) {
        setChecked(position, isScroll);
    }
    //将当前选中的item居中
    private void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = rvSort.getChildAt(position - mLinearLayoutManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - rvSort.getHeight() / 2);
            rvSort.smoothScrollBy(0, y);
        }

    }



}
