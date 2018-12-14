package com.lnkj.privateshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lnkj.privateshop.R;
import com.lnkj.privateshop.entity.AtrirListBean;
import com.lnkj.privateshop.entity.GoodsAttriBean;
import com.lnkj.privateshop.view.MyGridView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class AttriAdapter extends BaseAdapter {
    private Context mContext ;
    private    List<GoodsAttriBean.DataBean>   lists;
    List<List<AtrirListBean>> beanlists = new ArrayList<>();

    public AttriAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void addData(  List<GoodsAttriBean.DataBean> lists){
        this.lists=lists;
        notifyDataSetChanged();
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view==null){
            view=View.inflate(mContext, R.layout.list_item_attri,null);
        }
        TextView mTextView = (TextView) view.findViewById(R.id.mTextView);
        MyGridView mGridView = (MyGridView) view.findViewById(R.id.myGridView);
        mTextView.setText(lists.get(i).getAttr_name());
        List<AtrirListBean> bean = new ArrayList<>();
        for (int j = 0; j < lists.get(i).get_values().size(); j++) {
            AtrirListBean listbean = new AtrirListBean();
            listbean.setText(lists.get(i).get_values().get(j));
            listbean.setIscheck(false);
            listbean.setId(lists.get(i).getAttr_id());
            bean.add(listbean);
        }
        beanlists.add(bean);
        AttriListAdapter adapter = new AttriListAdapter(mContext,bean);
        mGridView.setAdapter(adapter);
        return view;
    }
    public String getTextAttri(){
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < beanlists.size(); i++) {
            List<AtrirListBean> bean = beanlists.get(i);
            for (int j = 0; j < bean.size(); j++) {
                if (bean.get(j).getIscheck()){
                if (sb.length()!=0){
                    sb.append(",");
                }
                sb.append(bean.get(j).getText());
                }
            }
        }
        return  sb.toString();
    }

    public String getAttriId(){
      StringBuffer sb = new StringBuffer();
        if (beanlists!=null){
        for (int i = 0; i < beanlists.size(); i++) {
            List<AtrirListBean> bean = beanlists.get(i);
            for (int j = 0; j < bean.size(); j++) {
                if (bean.get(j).getIscheck()){
                    if (sb.length()!=0){
                        sb.append(",");
                    }
                    sb.append(bean.get(j).getId());
                    sb.append(":");
                    sb.append(bean.get(j).getText());
                }
            }
        }

        }
        return sb.toString();
    }

}
