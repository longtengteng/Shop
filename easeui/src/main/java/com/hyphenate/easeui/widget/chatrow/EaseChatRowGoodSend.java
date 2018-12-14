package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.ChatType;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.adapter.EaseMessageAdapter;
import com.hyphenate.exceptions.HyphenateException;

public class EaseChatRowGoodSend extends EaseChatRow {

    private TextView tv_good_name;
    private TextView tv_good_price;
    private TextView btn_good_send;
    private String toChatUsername;

    public EaseChatRowGoodSend(Context context, EMMessage message, int position, String username, BaseAdapter adapter) {
        super(context, message, position, adapter);
        toChatUsername = username;
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
                R.layout.ease_row_good : R.layout.ease_row_good, this);
    }

    @Override
    protected void onFindViewById() {
        tv_good_name = (TextView) findViewById(R.id.tv_good_name);
        tv_good_price = (TextView) findViewById(R.id.tv_good_price);
        btn_good_send = (TextView) findViewById(R.id.btn_good_send);
    }

    @Override
    public void onSetUpView() {
        try {
            tv_good_name.setText(message.getStringAttribute("commodityName"));
            tv_good_price.setText(message.getStringAttribute("commodityPrice"));
            Glide.with(context).load(message.getStringAttribute("commodityImage")).asBitmap().centerCrop().
                    error(R.drawable.ease_default_avatar).into(userAvatarView);
            btn_good_send.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        EMMessage sendMessage = EMMessage.createTxtSendMessage(message.getStringAttribute("commodityName"), toChatUsername);
                        sendMessage.setAttribute("type", "CommodityInfo");
                        sendMessage.setAttribute("commodityName", message.getStringAttribute("commodityName"));
                        sendMessage.setAttribute("commodityPrice", message.getStringAttribute("commodityPrice"));
                        sendMessage.setAttribute("commodityImage", message.getStringAttribute("commodityImage"));
                        sendMessage.setAttribute("commodityID", message.getStringAttribute("commodityID"));
                        EMClient.getInstance().chatManager().sendMessage(sendMessage);
                        ((EaseMessageAdapter)adapter).refreshSelectLast();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        handleTextMessage();
    }

    protected void handleTextMessage() {
        if (message.direct() == EMMessage.Direct.SEND) {
            setMessageSendCallback();
            switch (message.status()) {
                case CREATE:
                    progressBar.setVisibility(View.GONE);
                    statusView.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    progressBar.setVisibility(View.GONE);
                    statusView.setVisibility(View.GONE);
                    break;
                case FAIL:
                    progressBar.setVisibility(View.GONE);
                    statusView.setVisibility(View.VISIBLE);
                    break;
                case INPROGRESS:
                    progressBar.setVisibility(View.VISIBLE);
                    statusView.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        } else {
            if (!message.isAcked() && message.getChatType() == ChatType.Chat) {
                try {
                    EMClient.getInstance().chatManager().ackMessageRead(message.getFrom(), message.getMsgId());
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onUpdateView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onBubbleClick() {
        // TODO Auto-generated method stub
    }


}
