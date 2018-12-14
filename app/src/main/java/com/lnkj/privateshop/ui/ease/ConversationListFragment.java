package com.lnkj.privateshop.ui.ease;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.chat.ChatActivity;

public class ConversationListFragment extends EaseConversationListFragment {
    private TextView errorText;

    @Override
    protected void initView() {
        super.initView();
        View errorView = (LinearLayout) View.inflate(getActivity(),R.layout.em_chat_neterror_item, null);
        errorItemContainer.addView(errorView);
        errorText = (TextView) errorView.findViewById(R.id.tv_connect_errormsg);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        // register context menu
        registerForContextMenu(conversationListView);
        conversationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = conversationListView.getItem(position);
                String username = conversation.conversationId();
                if (username.equals(EMClient.getInstance().getCurrentUser()))
                    Toast.makeText(getActivity(), "不能和自己聊天", Toast.LENGTH_SHORT).show();
                else {
                    // start chat acitivity
                    Intent intent = new Intent(getActivity(), ChatActivity.class);
                    if (conversation.isGroup()) {
                        if (conversation.getType() == EMConversation.EMConversationType.ChatRoom) {
                            // it's group chat
                            intent.putExtra(Constants.EXTRA_CHAT_TYPE, Constants.CHATTYPE_CHATROOM);
                        } else {
                            intent.putExtra(Constants.EXTRA_CHAT_TYPE, Constants.CHATTYPE_GROUP);
                        }

                    }
                    // it's single chat
                    intent.putExtra(Constants.EXTRA_USER_ID, username);
                    startActivity(intent);
                }
            }
        });
//        //red packet code : 红包回执消息在会话列表最后一条消息的展示
//        conversationListView.setConversationListHelper(new EaseConversationListHelper() {
//            @Override
//            public String onSetItemSecondaryText(EMMessage lastMessage) {
//                if (lastMessage.getBooleanAttribute(RPConstant.MESSAGE_ATTR_IS_RED_PACKET_ACK_MESSAGE, false)) {
//                    String sendNick = lastMessage.getStringAttribute(RPConstant.EXTRA_RED_PACKET_SENDER_NAME, "");
//                    String receiveNick = lastMessage.getStringAttribute(RPConstant.EXTRA_RED_PACKET_RECEIVER_NAME, "");
//                    String msg;
//                    if (lastMessage.direct() == EMMessage.Direct.RECEIVE) {
//                        msg = String.format(getResources().getString(R.string.msg_someone_take_red_packet), receiveNick);
//                    } else {
//                        if (sendNick.equals(receiveNick)) {
//                            msg = getResources().getString(R.string.msg_take_red_packet);
//                        } else {
//                            msg = String.format(getResources().getString(R.string.msg_take_someone_red_packet), sendNick);
//                        }
//                    }
//                    return msg;
//                }
//                return null;
//            }
//        });
//        super.setUpView();
//        //end of red packet code
//    }

//    @Override
//    protected void onConnectionDisconnected() {
//        super.onConnectionDisconnected();
//        if (NetUtils.hasNetwork(getActivity())){
//            errorText.setText(R.string.can_not_connect_chat_server_connection);
//        } else {
//            errorText.setText(R.string.the_current_network);
//        }
//    }

//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
//        getActivity().getMenuInflater().inflate(R.menu.em_delete_message, menu);
//    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        boolean deleteMessage = false;
//        if (item.getItemId() == R.id.delete_message) {
//            deleteMessage = true;
//        } else if (item.getItemId() == R.id.delete_conversation) {
//            deleteMessage = false;
//        }
//        EMConversation tobeDeleteCons = conversationListView.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
//        if (tobeDeleteCons == null) {
//            return true;
//        }
//        if(tobeDeleteCons.getType() == EMConversationType.GroupChat){
//            EaseAtMessageHelper.get().removeAtMeGroup(tobeDeleteCons.conversationId());
//        }
//        try {
//            // delete conversation
//            EMClient.getInstance().chatManager().deleteConversation(tobeDeleteCons.conversationId(), deleteMessage);
//            InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(getActivity());
//            inviteMessgeDao.deleteMessage(tobeDeleteCons.conversationId());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        refresh();
//
//        // update unread count
//        ((MainActivity) getActivity()).updateUnreadLabel();
//        return true;
//    }
    }
}
