package com.lnkj.privateshop;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.utils.PreferencesUtils;

import java.util.Map;

import static com.lnkj.privateshop.view.ProgreesDialog.progressDialog;

/**
 * Created by sll on 2016/3/9.
 */
public abstract class BaseFragment extends Fragment {
    //    protected Dialog progressDialog;// 加载框
    public String token;

    protected abstract int getContentResid();

//    public BaseFragment() {
//        setDialog();
//
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        token = PreferencesUtils.getString(MyApplication.getInstance(), "token");
        return inflater.inflate(getContentResid(), container, false);
    }

    public int UnreadMsgCount = 0;
    public boolean is_bogin2;

    @Override
    public void onResume() {
        super.onResume();
        UnreadMsgCount = 0;
        is_bogin2 = PreferencesUtils.getBoolean(MyApplication.getInstance(), "is_bogin");
        if (is_bogin2) {
            if (DemoHelper.getInstance().isLoggedIn()) {
                Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
                for (String key : conversations.keySet()) {
                    EMConversation value = conversations.get(key);
                    UnreadMsgCount = UnreadMsgCount + value.getUnreadMsgCount();
                }
            }
        } else {
            UnreadMsgCount = 0;
        }

    }

    /**
     * 设置登陆等待框
     */
    private void setDialog() {
        progressDialog = new Dialog(MyApplication.mContext, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog_commom);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        TextView msg = (TextView) progressDialog
                .findViewById(R.id.id_tv_loadingmsg);
        msg.setText("拼命加载中...");
    }

    /**
     * 该方法会紧跟着onCreateView被调用
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init(view);
        loadDatas();
    }

    /**
     * 初始化方法
     *
     * @param view
     */
    protected void init(View view) {

    }

    /**
     * 加载数据的方法
     */
    protected void loadDatas() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDatas(getArguments());
    }

    protected void getDatas(Bundle bundle) {

    }
}
