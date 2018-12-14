package com.lnkj.privateshop.chat;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseNotifier;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.util.EMLog;
import com.lnkj.privateshop.MyApplication;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.chat.parse.UserProfileManager;
import com.lnkj.privateshop.entity.ShopEmchatBean;
import com.lnkj.privateshop.greendao.UserInfoDaoUtils;

import java.util.List;

/**
 * Created by llt on 2017/6/5.
 */

public class EaseHelper {
    Context context;

    public final String TAG = "easeHelper";
    public static EaseHelper easeHelper;
    public static EaseUI easeUI;
    public boolean isVoiceCalling;
    public boolean isVideoCalling;
    public CallReceiver callReceiver;
    UserInfoDaoUtils daoUtils;
    private UserProfileManager userProManager;
    /**
     * data sync listener
     */
    public interface DataSyncListener {
        /**
         * sync complete
         * @param success true：data sync successful，false: failed to sync data
         */
        void onSyncComplete(boolean success);
    }
    public MyApplication instance = MyApplication.getInstance();


    public static EaseHelper getInstance() {
        if (null == easeHelper) {
            easeHelper = new EaseHelper();
        }
        return easeHelper;
    }
    public boolean isLoggedIn() {
        return EMClient.getInstance().isLoggedInBefore();
    }
    public void init(Context context) {
        this.context = context;
        daoUtils = new UserInfoDaoUtils(context);
        EMOptions options = initChatOptions();
        if (EaseUI.getInstance().init(context, options)) {
            easeUI = EaseUI.getInstance();
            PreferenceManager.init(context);
            setEaseUIProviders();
            setEaseUISettingProvider();
            EMLog.debugMode = true;
            EMClient.getInstance().chatManager().addMessageListener(listener);

            IntentFilter callFilter = new IntentFilter(EMClient.getInstance().callManager().getIncomingCallBroadcastAction());
            if (callReceiver == null) {
                callReceiver = new CallReceiver();
            }
            context.registerReceiver(callReceiver, callFilter);
        }
    }
    private String username;
    /**
     * set current username
     * @param username
     */
    public void setCurrentUserName(String username){
        this.username = username;
        PreferenceManager.getInstance().setCurrentUserName(username);
    }
    public UserProfileManager getUserProfileManager() {
        if (userProManager == null) {
            userProManager = new UserProfileManager();
        }
        return userProManager;
    }
    private EMOptions initChatOptions() {

        EMOptions options = new EMOptions();
        // set if accept the invitation automatically
        options.setAcceptInvitationAlways(false);
        // set if you need read ack
        options.setRequireAck(true);
        // set if you need delivery ack
        options.setRequireDeliveryAck(false);
        //设置是否自动接受加群邀请
        options.setAutoAcceptGroupInvitation(false);
        options.setAutoLogin(true);
        //设置是否自动接受加好友邀请。缺省true
        options.setAcceptInvitationAlways(false);
        options.setDeleteMessagesAsExitGroup(true);


        return options;
    }

    public void getFriendInfoByChatName(final String chatname, final ImageView avatarView, final TextView nickView) {
    }

    public void setEaseUIProviders() {
        easeUI.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
            @Override
            public EaseUser getUser(String username, ImageView avaterView, TextView nickView) {
                EaseUser easeUser = new EaseUser(username);
                getFriendInfoByChatName(username, avaterView, nickView);
                return easeUser;
            }

            @Override
            public EaseUser getUser(String username) {
                EaseUser easeUser = new EaseUser(username);
                List<ShopEmchatBean> been = daoUtils.queryUserInfoByEId(username);
                if (been != null && been.size() > 0) {
                    ShopEmchatBean bean = been.get(0);
//                    easeUser.setAvatar(Constants.Base_URL + bean.getHead_pic());
//                    easeUser.setNickname(bean.getNickname());
//                    easeUser.setNick(bean.getNickname());
                }
                return easeUser;
            }

            @Override
            public EMGroup getGroup(String groupId, ImageView logoView) {
                return null;
            }

            @Override
            public EMGroup getGroup(String groupId, TextView nameView) {
                return null;
            }

            @Override
            public EaseUser getUser(String username, String appendString, TextView nickView) {
                return null;
            }
        });
    }


    public void setEaseUISettingProvider() {
        easeUI.setSettingsProvider(new EaseUI.EaseSettingsProvider() {
            @Override
            public boolean isMsgNotifyAllowed(EMMessage message) {
                return true;
            }

            @Override
            public boolean isMsgSoundAllowed(EMMessage message) {
                return true;
            }

            @Override
            public boolean isMsgVibrateAllowed(EMMessage message) {
                return true;
            }

            @Override
            public boolean isSpeakerOpened() {
                return true;
            }
        });

        easeUI.getNotifier().setNotificationInfoProvider(new EaseNotifier.EaseNotificationInfoProvider() {

            @Override
            public String getTitle(EMMessage message) {
                //you can update title here
                return MyApplication.getInstance().getString(R.string.app_name);
            }

            @Override
            public int getSmallIcon(EMMessage message) {
                //you can update icon here
                return R.mipmap.logo;
            }

            @Override
            public String getDisplayedText(EMMessage message) {
                // be used on notification bar, different text according the message type.
                String ticker = EaseCommonUtils.getMessageDigest(message, context);
                if (message.getType() == EMMessage.Type.TXT) {
                    ticker = ticker.replaceAll("\\[.{2,3}\\]", "[表情]");
                }
                return null;
            }

            @Override
            public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
                // here you can customize the text.
                // return fromUsersNum + "contacts send " + messageNum + "messages to you";
                return null;
            }

            @Override
            public Intent getLaunchIntent(EMMessage message) {
                // you can set what activity you want display when user click the notification
                Intent intent = new Intent(context, ChatActivity.class);
                // open calling activity if there is call
                if (isVideoCalling) {
//                    intent = new Intent(context, VideoCallActivity.class);
                } else if (isVoiceCalling) {
//                    intent = new Intent(context, VoiceCallActivity.class);
                } else {
                    EMMessage.ChatType chatType = message.getChatType();
                    if (chatType == EMMessage.ChatType.Chat) { // single chat message
                        intent.putExtra("userId", message.getFrom());
                        intent.putExtra("chatType", EaseConstant.CHATTYPE_SINGLE);
                    } else { // group chat message
                        // message.getTo() is the group id
                        intent.putExtra("userId", message.getTo());
                        if (chatType == EMMessage.ChatType.GroupChat) {
                            intent.putExtra("chatType", EaseConstant.CHATTYPE_GROUP);
                        } else {
                            intent.putExtra("chatType", EaseConstant.CHATTYPE_CHATROOM);
                        }

                    }
                }
                return intent;
            }
        });
    }

//    public void notifyContactsSyncListener(boolean success){
//        for (DataSyncListener listener : syncContactsListeners) {
//            listener.onSyncComplete(success);
//        }
//    }

    EMMessageListener listener = new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            for (EMMessage message : list) {
                EMLog.d(TAG, "onMessageReceived id : " + message.getMsgId());
                // in background, do not refresh UI, notify it in notification bar
                if (!easeUI.hasForegroundActivies()) {
                    getNotifier().onNewMsg(message);
                }
                String userName = message.getStringAttribute("userName", "");
                String userPic = message.getStringAttribute("userPic", "");
                String hxIdFrom = message.getFrom();
                EaseUser easeUser = new EaseUser(hxIdFrom);
                easeUser.setAvatar(userPic);
                easeUser.setNick(userName);

//                // 存入内存
//                getContactList();
//                contactList.put(hxIdFrom, easeUser);
//                // 存入db
//                UserDao dao = new UserDao(MainApplication.getContext());
//                List<EaseUser> users = new ArrayList<EaseUser>();
//                users.add(easeUser);
//                dao.saveContactList(users);
//
//                getModel().setContactSynced(true);
//
//                // 通知listeners联系人同步完毕

//                if (isGroupsSyncedWithServer()) {
//
//                }
            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageRead(List<EMMessage> list) {

        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {

        }

        @Override
        public void onMessageRecalled(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };

    public EaseNotifier getNotifier() {
        return easeUI.getNotifier();
    }
}
