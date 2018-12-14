package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.ChatType;
import com.hyphenate.easeui.R;
import com.hyphenate.exceptions.HyphenateException;

public class EaseChatRowGood extends EaseChatRow {

    private TextView tv_good_name;
    private TextView tv_good_price;
    private ImageView iv_good;

    public EaseChatRowGood(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
                R.layout.ease_row_received_good : R.layout.ease_row_sent_good, this);
    }

    @Override
    protected void onFindViewById() {
        tv_good_name = (TextView) findViewById(R.id.tv_good_name);
        tv_good_price = (TextView) findViewById(R.id.tv_good_price);
        iv_good = (ImageView) findViewById(R.id.iv_good);
    }

    @Override
    public void onSetUpView() {
        try {
            tv_good_name.setText(message.getStringAttribute("commodityName"));
            tv_good_price.setText(message.getStringAttribute("commodityPrice"));
            Glide.with(context).load(message.getStringAttribute("commodityImage")).asBitmap().centerCrop().
                    error(R.drawable.ease_default_image).into(iv_good);
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
//        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.setAction("com.liuniu.bubble.click");
        try {
            intent.putExtra("messageDirect", message.direct() == EMMessage.Direct.RECEIVE ? 0 : 1);
            intent.putExtra("goodId", message.getStringAttribute("commodityID"));
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        context.sendBroadcast(intent);


//        Intent intent = new Intent(context,GoodsInfoActivity.class);
    }


}
