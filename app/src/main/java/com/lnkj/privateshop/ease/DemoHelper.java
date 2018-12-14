package com.lnkj.privateshop.ease;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMError;
import com.hyphenate.EMGroupChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMucSharedFile;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.model.EaseNotifier;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.lnkj.privateshop.Constants;
import com.lnkj.privateshop.R;
import com.lnkj.privateshop.ease.domain.InviteMessage;
import com.lnkj.privateshop.ease.domain.RobotUser;
import com.lnkj.privateshop.ease.domain.db.DemoDBManager;
import com.lnkj.privateshop.ease.domain.db.InviteMessgeDao;
import com.lnkj.privateshop.ease.domain.db.UserDao;
import com.lnkj.privateshop.ease.receiver.CallReceiver;
import com.lnkj.privateshop.ease.utils.PreferenceManager;
import com.lnkj.privateshop.ui.MainActivity;
import com.lnkj.privateshop.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DemoHelper {
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

    protected static final String TAG = "DemoHelper";
    
	private EaseUI easeUI;
	
    /**
     * EMEventListener
     */
    protected EMMessageListener messageListener = null;

	private Map<String, EaseUser> contactList;

	private Map<String, RobotUser> robotList;

	private UserProfileManager userProManager;

	private static DemoHelper instance = null;
	
	private DemoModel demoModel = null;
	
	/**
     * sync groups status listener
     */
    private List<DataSyncListener> syncGroupsListeners;
    /**
     * sync contacts status listener
     */
    private List<DataSyncListener> syncContactsListeners;
    /**
     * sync blacklist status listener
     */
    private List<DataSyncListener> syncBlackListListeners;

    private boolean isSyncingGroupsWithServer = false;
    private boolean isSyncingContactsWithServer = false;
    private boolean isSyncingBlackListWithServer = false;
    private boolean isGroupsSyncedWithServer = false;
    private boolean isContactsSyncedWithServer = false;
    private boolean isBlackListSyncedWithServer = false;
	
	public boolean isVoiceCalling;
    public boolean isVideoCalling;

	private String username;

    private Context appContext;

    private CallReceiver callReceiver;

    private InviteMessgeDao inviteMessgeDao;
    private UserDao userDao;

    private LocalBroadcastManager broadcastManager;

    private boolean isGroupAndContactListenerRegisted;

	private DemoHelper() {
		
	}

	public synchronized static DemoHelper getInstance() {
		if (instance == null) {
			instance = new DemoHelper();
		}
		return instance;
	}

	/**
	 * init helper
	 * 
	 * @param context
	 *            application context
	 */
	public void init(Context context) {
	    demoModel = new DemoModel(context);

	    EMOptions options = initChatOptions();
        //use default options if options is null
		if (EaseUI.getInstance().init(context, options)) {
		    appContext = context;
		    
		    //debug mode, you'd better set it to false, if you want release your App officially.
		    EMClient.getInstance().setDebugMode(true);
		    //get easeui instance
		    easeUI = EaseUI.getInstance();
//		    to set user's profile and avatar
		    setEaseUIProviders();
			//initialize preference manager
			PreferenceManager.init(context);
			//initialize profile manager
			getUserProfileManager().init(context);
//			
//			EMClient.getInstance().callManager().getVideoCallHelper().setAdaptiveVideoFlag(getModel().isAdaptiveVideoEncode());
//
			setGlobalListeners();
			broadcastManager = LocalBroadcastManager.getInstance(appContext);
	        initDbDao();
		}
	}

	
	private EMOptions initChatOptions(){

        EMOptions options = new EMOptions();
        // set if accept the invitation automatically
        options.setAcceptInvitationAlways(false);
        // set if you need read ack
        options.setRequireAck(true);
        // set if you need delivery ack
        options.setRequireDeliveryAck(false);

        //you need apply & set your own id if you want to use google cloud messaging.
//        options.setGCMNumber("324169311137");
//        //you need apply & set your own id if you want to use Mi push notification
//        options.setMipushConfig("2882303761517426801", "5381742660801");
//        //you need apply & set your own id if you want to use Huawei push notification
//        options.setHuaweiPushAppId("10492024");

        //set custom servers, commonly used in private deployment
        if(demoModel.isCustomServerEnable() && demoModel.getRestServer() != null && demoModel.getIMServer() != null) {
            options.setRestServer(demoModel.getRestServer());
            options.setIMServer(demoModel.getIMServer());
            if(demoModel.getIMServer().contains(":")) {
                options.setIMServer(demoModel.getIMServer().split(":")[0]);
                options.setImPort(Integer.valueOf(demoModel.getIMServer().split(":")[1]));
            }
        }
        
        options.allowChatroomOwnerLeave(getModel().isChatroomOwnerLeaveAllowed());
        options.setDeleteMessagesAsExitGroup(getModel().isDeleteMessagesAsExitGroup());
        options.setAutoAcceptGroupInvitation(getModel().isAutoAcceptGroupInvitation());
        
        return options;
    }
	
    protected void setEaseUIProviders() {
    	// set profile provider if you want easeUI to handle avatar and nickname
        easeUI.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {

            @Override
            public EaseUser getUser(String username, ImageView avaterView, TextView nickView) {
                return getUserInfo(username);
            }

            @Override
            public EaseUser getUser(String username) {
                return getUserInfo(username);
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
                return getUserInfo(username);
            }
        });

        //set options 
        easeUI.setSettingsProvider(new EaseUI.EaseSettingsProvider() {
            
            @Override
            public boolean isSpeakerOpened() {
                return demoModel.getSettingMsgSpeaker();
            }
            
            @Override
            public boolean isMsgVibrateAllowed(EMMessage message) {
                return demoModel.getSettingMsgVibrate();
            }
            
            @Override
            public boolean isMsgSoundAllowed(EMMessage message) {
                return demoModel.getSettingMsgSound();
            }
            
            @Override
            public boolean isMsgNotifyAllowed(EMMessage message) {
                if(message == null){
                    return demoModel.getSettingMsgNotification();
                }
                if(!demoModel.getSettingMsgNotification()){
                    return false;
                }else{
                    String chatUsename = null;
                    List<String> notNotifyIds = null;
                    // get user or group id which was blocked to show message notifications
                    if (message.getChatType() == EMMessage.ChatType.Chat) {
                        chatUsename = message.getFrom();
                        notNotifyIds = demoModel.getDisabledIds();
                    } else {
                        chatUsename = message.getTo();
                        notNotifyIds = demoModel.getDisabledGroups();
                    }

                    if (notNotifyIds == null || !notNotifyIds.contains(chatUsename)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        });
        
        
        //set notification options, will use default if you don't set it
        easeUI.getNotifier().setNotificationInfoProvider(new EaseNotifier.EaseNotificationInfoProvider() {
            
            @Override
            public String getTitle(EMMessage message) {
              //you can update title here
                return null;
            }
            
            @Override
            public int getSmallIcon(EMMessage message) {
              //you can update icon here
                return 0;
            }
            
            @Override
            public String getDisplayedText(EMMessage message) {
            	// be used on notification bar, different text according the message type.
                String ticker = EaseCommonUtils.getMessageDigest(message, appContext);
                if(message.getType() == EMMessage.Type.TXT){
                    ticker = ticker.replaceAll("\\[.{2,3}\\]", "[表情]");
                }
                EaseUser user = getUserInfo(message.getFrom());
                if(user != null){
                    if(EaseAtMessageHelper.get().isAtMeMsg(message)){
//                        return String.format(appContext.getString(R.string.at_your_in_group), user.getNick());
                    }
                    return user.getNick() + ": " + ticker;
                }else{
                    if(EaseAtMessageHelper.get().isAtMeMsg(message)){
//                        return String.format(appContext.getString(R.string.at_your_in_group), message.getFrom());
                    }
                    return message.getFrom() + ": " + ticker;
                }
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
                Intent intent = new Intent(appContext, MainActivity.class);
                // open calling activity if there is call
                if(isVideoCalling){
//                    intent = new Intent(appContext, VideoCallActivity.class);
                }else if(isVoiceCalling){
//                    intent = new Intent(appContext, VoiceCallActivity.class);
                }else{
                    EMMessage.ChatType chatType = message.getChatType();
                    if (chatType == EMMessage.ChatType.Chat) { // single chat message
                        intent.putExtra("userId", message.getFrom());
                        intent.putExtra("chatType", Constant.CHATTYPE_SINGLE);
                    } else { // group chat message
                        // message.getTo() is the group id
                        intent.putExtra("userId", message.getTo());
                        if(chatType == EMMessage.ChatType.GroupChat){
                            intent.putExtra("chatType", Constant.CHATTYPE_GROUP);
                        }else{
                            intent.putExtra("chatType", Constant.CHATTYPE_CHATROOM);
                        }
                        
                    }
                }
                return intent;
            }
        });
    }

    EMConnectionListener connectionListener;
    /**
     * set global listener
     */
    protected void setGlobalListeners(){
        syncGroupsListeners = new ArrayList<DataSyncListener>();
        syncContactsListeners = new ArrayList<DataSyncListener>();
        syncBlackListListeners = new ArrayList<DataSyncListener>();
        
        isGroupsSyncedWithServer = demoModel.isGroupsSynced();
        isContactsSyncedWithServer = demoModel.isContactSynced();
        isBlackListSyncedWithServer = demoModel.isBacklistSynced();
        
        // create the global connection listener
        connectionListener = new EMConnectionListener() {
            @Override
            public void onDisconnected(int error) {
                if (error == EMError.USER_REMOVED) {
                    onCurrentAccountRemoved();
                } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    onConnectionConflict();
                }
            }

            @Override
            public void onConnected() {
                // in case group and contact were already synced, we supposed to notify sdk we are ready to receive the events
                if (isGroupsSyncedWithServer && isContactsSyncedWithServer) {
                    EMLog.d(TAG, "group and contact already synced with servre");
                } else {
                    if (!isGroupsSyncedWithServer) {
                        asyncFetchGroupsFromServer(null);
                    }

                    if (!isContactsSyncedWithServer) {
                        asyncFetchContactsFromServer(null);
                    }

                    if (!isBlackListSyncedWithServer) {
                        asyncFetchBlackListFromServer(null);
                    }
                }
            }
        };

        IntentFilter callFilter = new IntentFilter(EMClient.getInstance().callManager().getIncomingCallBroadcastAction());
        if(callReceiver == null){
            callReceiver = new CallReceiver();
        }

        //register incoming call receiver
        appContext.registerReceiver(callReceiver, callFilter);    
        //register connection listener
        EMClient.getInstance().addConnectionListener(connectionListener);       
        //register group and contact event listener
        registerGroupAndContactListener();
        //register message event listener
        registerMessageListener();
        
    }
    
    private void initDbDao() {
        inviteMessgeDao = new InviteMessgeDao(appContext);
        userDao = new UserDao(appContext);
        getContactList();
    }
    
    /**
     * register group and contact listener, you need register when login
     */
    public void registerGroupAndContactListener(){
        if(!isGroupAndContactListenerRegisted){
            EMClient.getInstance().groupManager().addGroupChangeListener(new MyGroupChangeListener());
            EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
            isGroupAndContactListenerRegisted = true;
        }
        
    }

    /**
     * group change listener
     */
    class MyGroupChangeListener implements EMGroupChangeListener {

        @Override
        public void onInvitationReceived(String groupId, String groupName, String inviter, String reason) {

            new InviteMessgeDao(appContext).deleteMessage(groupId);

            // user invite you to join group
            InviteMessage msg = new InviteMessage();
            msg.setFrom(groupId);
            msg.setTime(System.currentTimeMillis());
            msg.setGroupId(groupId);
            msg.setGroupName(groupName);
            msg.setReason(reason);
            msg.setGroupInviter(inviter);
            msg.setStatus(InviteMessage.InviteMesageStatus.GROUPINVITATION);
            notifyNewInviteMessage(msg);
            broadcastManager.sendBroadcast(new Intent(Constant.ACTION_GROUP_CHANAGED));
        }

        @Override
        public void onRequestToJoinReceived(String s, String s1, String s2, String s3) {

        }

        @Override
        public void onRequestToJoinAccepted(String s, String s1, String s2) {

        }

        @Override
        public void onRequestToJoinDeclined(String s, String s1, String s2, String s3) {

        }

        @Override
        public void onInvitationAccepted(String groupId, String invitee, String reason) {

            new InviteMessgeDao(appContext).deleteMessage(groupId);

            //user accept your invitation
            boolean hasGroup = false;
            EMGroup _group = null;
            for (EMGroup group : EMClient.getInstance().groupManager().getAllGroups()) {
                if (group.getGroupId().equals(groupId)) {
                    hasGroup = true;
                    _group = group;
                    break;
                }
            }
            if (!hasGroup)
                return;

            InviteMessage msg = new InviteMessage();
            msg.setFrom(groupId);
            msg.setTime(System.currentTimeMillis());
            msg.setGroupId(groupId);
            msg.setGroupName(_group == null ? groupId : _group.getGroupName());
            msg.setReason(reason);
            msg.setGroupInviter(invitee);
            msg.setStatus(InviteMessage.InviteMesageStatus.GROUPINVITATION_ACCEPTED);
            notifyNewInviteMessage(msg);
            broadcastManager.sendBroadcast(new Intent(Constant.ACTION_GROUP_CHANAGED));
        }

        @Override
        public void onInvitationDeclined(String groupId, String invitee, String reason) {

            new InviteMessgeDao(appContext).deleteMessage(groupId);

            //user declined your invitation
            EMGroup group = null;
            for (EMGroup _group : EMClient.getInstance().groupManager().getAllGroups()) {
                if (_group.getGroupId().equals(groupId)) {
                    group = _group;
                    break;
                }
            }
            if (group == null)
                return;

            InviteMessage msg = new InviteMessage();
            msg.setFrom(groupId);
            msg.setTime(System.currentTimeMillis());
            msg.setGroupId(groupId);
            msg.setGroupName(group.getGroupName());
            msg.setReason(reason);
            msg.setGroupInviter(invitee);
            msg.setStatus(InviteMessage.InviteMesageStatus.GROUPINVITATION_DECLINED);
            notifyNewInviteMessage(msg);
            broadcastManager.sendBroadcast(new Intent(Constant.ACTION_GROUP_CHANAGED));
        }

        @Override
        public void onUserRemoved(String groupId, String groupName) {
            //user is removed from group
            broadcastManager.sendBroadcast(new Intent(Constant.ACTION_GROUP_CHANAGED));
        }

        @Override
        public void onGroupDestroyed(String groupId, String groupName) {
        	// group is dismissed,
            broadcastManager.sendBroadcast(new Intent(Constant.ACTION_GROUP_CHANAGED));
        }


        @Override
        public void onAutoAcceptInvitationFromGroup(String groupId, String inviter, String inviteMessage) {
            // got an invitation
            String st3 = appContext.getString(R.string.Invite_you_to_join_a_group_chat);
            EMMessage msg = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
            msg.setChatType(EMMessage.ChatType.GroupChat);
            msg.setFrom(inviter);
            msg.setTo(groupId);
            msg.setMsgId(UUID.randomUUID().toString());
            msg.addBody(new EMTextMessageBody(inviter + " " +st3));
            msg.setStatus(EMMessage.Status.SUCCESS);
            // save invitation as messages
            EMClient.getInstance().chatManager().saveMessage(msg);
            // notify invitation message
            getNotifier().vibrateAndPlayTone(msg);
            EMLog.d(TAG, "onAutoAcceptInvitationFromGroup groupId:" + groupId);
            broadcastManager.sendBroadcast(new Intent(Constant.ACTION_GROUP_CHANAGED));
        }

        @Override
        public void onMuteListAdded(String s, List<String> list, long l) {

        }

        @Override
        public void onMuteListRemoved(String s, List<String> list) {

        }

        @Override
        public void onAdminAdded(String s, String s1) {

        }

        @Override
        public void onAdminRemoved(String s, String s1) {

        }

        @Override
        public void onOwnerChanged(String s, String s1, String s2) {

        }

        @Override
        public void onMemberJoined(String s, String s1) {

        }

        @Override
        public void onMemberExited(String s, String s1) {

        }

        @Override
        public void onAnnouncementChanged(String s, String s1) {

        }

        @Override
        public void onSharedFileAdded(String s, EMMucSharedFile emMucSharedFile) {

        }

        @Override
        public void onSharedFileDeleted(String s, String s1) {

        }
    }

    /***
     * 好友变化listener
     * 
     */
    public class MyContactListener implements EMContactListener {

        @Override
        public void onContactAdded(String username) {
            // save contact
            Map<String, EaseUser> localUsers = getContactList();
            Map<String, EaseUser> toAddUsers = new HashMap<String, EaseUser>();
            EaseUser user = new EaseUser(username);

            if (!localUsers.containsKey(username)) {
                userDao.saveContact(user);
            }
            toAddUsers.put(username, user);
            localUsers.putAll(toAddUsers);

            broadcastManager.sendBroadcast(new Intent(Constant.ACTION_CONTACT_CHANAGED));
        }

        @Override
        public void onContactDeleted(String username) {
            Map<String, EaseUser> localUsers = DemoHelper.getInstance().getContactList();
            localUsers.remove(username);
            userDao.deleteContact(username);
            inviteMessgeDao.deleteMessage(username);

            broadcastManager.sendBroadcast(new Intent(Constant.ACTION_CONTACT_CHANAGED));
        }

        @Override
        public void onContactInvited(String username, String reason) {
            List<InviteMessage> msgs = inviteMessgeDao.getMessagesList();

            for (InviteMessage inviteMessage : msgs) {
                if (inviteMessage.getGroupId() == null && inviteMessage.getFrom().equals(username)) {
                    inviteMessgeDao.deleteMessage(username);
                }
            }
            // save invitation as message
            InviteMessage msg = new InviteMessage();
            msg.setFrom(username);
            msg.setTime(System.currentTimeMillis());
            msg.setReason(reason);
//            showToast(username + "apply to be your friend,reason: " + reason);
            // set invitation status
//            msg.setStatus(InviteMessageStatus.BEINVITEED);
            notifyNewInviteMessage(msg);
            broadcastManager.sendBroadcast(new Intent(Constant.ACTION_CONTACT_CHANAGED));
        }

        @Override
        public void onFriendRequestAccepted(String s) {

        }

        @Override
        public void onFriendRequestDeclined(String s) {

        }


    }
    
    /**
     * save and notify invitation message
     * @param msg
     */
    private void notifyNewInviteMessage(InviteMessage msg){
        if(inviteMessgeDao == null){
            inviteMessgeDao = new InviteMessgeDao(appContext);
        }
        inviteMessgeDao.saveMessage(msg);
        //increase the unread message count
        inviteMessgeDao.saveUnreadMessageCount(1);
        // notify there is new message
        getNotifier().vibrateAndPlayTone(null);
    }
    
    /**
     * user has logged into another device
     */
    protected void onConnectionConflict(){
        Intent intent = new Intent(appContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.ACCOUNT_CONFLICT, true);
        appContext.startActivity(intent);
    }
    
    /**
     * account is removed
     */
    protected void onCurrentAccountRemoved(){
        Intent intent = new Intent(appContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.ACCOUNT_REMOVED, true);
        appContext.startActivity(intent);
    }
	
	private EaseUser getUserInfo(String hxId){
		 // 获取user信息，demo是从内存的好友列表里获取，
	    // 实际开发中，可能还需要从服务器获取用户信息,
	    // 从服务器获取的数据，最好缓存起来，避免频繁的网络请求
	    if (hxId.equals(EMClient.getInstance().getCurrentUser())) {
	      EaseUser currentUserInfo = getUserProfileManager().getCurrentUserInfo();
	      return currentUserInfo;
	    }
	    EaseUser easeUser;
	    if (contactList != null && contactList.containsKey(hxId)) {
	    } else { // 如果内存中没有，则将本地数据库中的取出到内存中。
	      getContactList();
	    }
	    // // TODO 获取不在好友列表里的群成员具体信息，即陌生人信息，demo未实现
	    // if (user == null && getRobotList() != null) {
	    // user = getRobotList().get(hxId);
	    // }
        try {
        easeUser = contactList.get(hxId);
	    if(easeUser == null){
	      easeUser = new EaseUser(hxId);
	    } else {
	      if(TextUtils.isEmpty(easeUser.getNick())){ //如果名字为空，则显示环信号码
	        easeUser.setNick(easeUser.getUsername());
	      }
	    }
        }catch (Exception e){
          return  null;
        }
	    return easeUser;
	}
	
	 /**
     * Global listener
     * If this event already handled by an activity, you don't need handle it again
     * activityList.size() <= 0 means all activities already in background or not in Activity Stack
     */
    protected void registerMessageListener() {
    	messageListener = new EMMessageListener() {
            private BroadcastReceiver broadCastReceiver = null;
			
			@Override
			public void onMessageReceived(List<EMMessage> messages) {
//				  System.out.println("收到消息1"+messages);
			    for (EMMessage message : messages) {
			        EMLog.d(TAG, "onMessageReceived id : " + message.getMsgId());
//			        System.out.println("收到消息"+message);
			        // in background, do not refresh UI, notify it in notification bar
			        String userName = message.getStringAttribute("nick", "");
			        String userPic = message.getStringAttribute("avatar", "");

//                    String chatUserId = message.getStringAttribute("chatUserId", "");
                    if (!TextUtils.isEmpty(userName)||!TextUtils.isEmpty(userPic)){
                        if (!userPic.startsWith("http")){
                            userPic = Constants.Base_IMG_URL+userPic;
                        }

                        String hxIdFrom = message.getFrom();
                        EaseUser easeUser = new EaseUser(hxIdFrom);
                        easeUser.setAvatar(userPic);
//                        easeUser.setNick(userName);
//                        System.out.println("_______"+userName);
//                        System.out.println("_______"+userPic);
                        easeUser.setNickname(userName);

                        saveContact(easeUser);
                        // 存入内存
//                        getContactList();
//                        contactList.put(hxIdFrom, easeUser);
//                        // 存入db
//                        UserDao dao = new UserDao(MyApplication.getApplication());
//                        List<EaseUser> users = new ArrayList<EaseUser>();
//                        users.add(easeUser);
//                        dao.saveContactList(users);

                        getModel().setContactSynced(true);
                        // 通知listeners联系人同步完毕
                        notifyContactsSyncListener(true);
                        if (isGroupsSyncedWithServer()) {
//			          notifyForRecevingEvents();
                        }


                        if(!easeUI.hasForegroundActivies()){
                            getNotifier().onNewMsg(message);
                        }
                    }

			    }
			}
			
			@Override
			public void onCmdMessageReceived(List<EMMessage> messages) {
			    for (EMMessage message : messages) {
                    EMLog.d(TAG, "receive command message");
                    //get message body
                    String userName = message.getStringAttribute("nick", "");
			        String userPic = message.getStringAttribute("avatar", "");
			        String hxIdFrom = message.getFrom();
			        EaseUser easeUser = new EaseUser(hxIdFrom);
			        easeUser.setAvatar(userPic);
			        easeUser.setNick(userName);
			     // 存入内存
			        getContactList();
			        contactList.put(hxIdFrom, easeUser);
			        // 存入db
//			        UserDao dao = new UserDao(MyApplication.getApplication());
//			        List<EaseUser> users = new ArrayList<EaseUser>();
//			        users.add(easeUser);
////			        dao.saveContactList(users);
//                    updateContactList(users);

                    saveContact(easeUser);

			        getModel().setContactSynced(true);
			        // 通知listeners联系人同步完毕
			        notifyContactsSyncListener(true);
			        if (isGroupsSyncedWithServer()) {
//			          notifyForRecevingEvents();
			        }
                    EMCmdMessageBody cmdMsgBody = (EMCmdMessageBody) message.getBody();
                    final String action = cmdMsgBody.action();//获取自定义action
                    //red packet code : 处理红包回执透传消息
//                    if(!easeUI.hasForegroundActivies()){
//                        if (action.equals(RedPacketConstant.REFRESH_GROUP_RED_PACKET_ACTION)){
//                            RedPacketUtil.receiveRedPacketAckMessage(message);
//                            broadcastManager.sendBroadcast(new Intent(RedPacketConstant.REFRESH_GROUP_RED_PACKET_ACTION));
//                        }
//                    }
                    //end of red packet code
                    //获取扩展属性 此处省略
                    //maybe you need get extension of your message
                    //message.getStringAttribute("");
                    EMLog.d(TAG, String.format("Command：action:%s,message:%s", action,message.toString()));
                }
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
			public void onMessageChanged(EMMessage message, Object change) {
				
			}
		};
		
        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

	/**
	 * if ever logged in
	 * 
	 * @return
	 */
	public boolean isLoggedIn() {
		return EMClient.getInstance().isLoggedInBefore();
	}

	/**
	 * logout
	 * 
	 * @param unbindDeviceToken
	 *            whether you need unbind your device token
	 * @param callback
	 *            callback
	 */
	public void logout(boolean unbindDeviceToken, final EMCallBack callback) {
		endCall();
		EMClient.getInstance().logout(unbindDeviceToken, new EMCallBack() {

			@Override
			public void onSuccess() {
			    reset();
				if (callback != null) {
					callback.onSuccess();
				}

			}

			@Override
			public void onProgress(int progress, String status) {
				if (callback != null) {
					callback.onProgress(progress, status);
				}
			}

			@Override
			public void onError(int code, String error) {
                reset();
				if (callback != null) {
					callback.onError(code, error);
				}
			}
		});
	}
	
	/**
	 * get instance of EaseNotifier
	 * @return
	 */
	public EaseNotifier getNotifier(){
	    return easeUI.getNotifier();
	}
	
	public DemoModel getModel(){
        return (DemoModel) demoModel;
    }
	
	/**
	 * update contact list
	 * 
	 * @param
	 */
	public void setContactList(Map<String, EaseUser> aContactList) {
		if(aContactList == null){
		    if (contactList != null) {
		        contactList.clear();
		    }
			return;
		}
		
		contactList = aContactList;
	}
	
	/**
     * save single contact 
     */
    public void saveContact(EaseUser user){
    	contactList.put(user.getUsername(), user);
    	demoModel.saveContact(user);
    }
    
    /**
     * get contact list
     *
     * @return
     */
    public Map<String, EaseUser> getContactList() {
        if (isLoggedIn() && contactList == null) {
            contactList = demoModel.getContactList();
        }
        
        // return a empty non-null object to avoid app crash
        if(contactList == null){
        	return new Hashtable<String, EaseUser>();
        }
        
        return contactList;
    }
    
    /**
     * set current username
     * @param username
     */
    public void setCurrentUserName(String username){
    	this.username = username;
    	demoModel.setCurrentUserName(username);
    }
    
    /**
     * get current user's id
     */
    public String getCurrentUsernName(){
    	if(username == null){
    		username = demoModel.getCurrentUsernName();
    	}
    	return username;
    }

	public void setRobotList(Map<String, RobotUser> robotList) {
		this.robotList = robotList;
	}

	public Map<String, RobotUser> getRobotList() {
		if (isLoggedIn() && robotList == null) {
			robotList = demoModel.getRobotList();
		}
		return robotList;
	}

	 /**
     * update user list to cache and database
     *
     * @param
     */
    public void updateContactList(List<EaseUser> contactInfoList) {
        try {

         for (EaseUser u : contactInfoList) {
            contactList.put(u.getUsername(), u);
         }
         ArrayList<EaseUser> mList = new ArrayList<EaseUser>();
         mList.addAll(contactList.values());
         demoModel.saveContactList(mList);
        }catch (Exception e){ }
    }

	public UserProfileManager getUserProfileManager() {
		if (userProManager == null) {
			userProManager = new UserProfileManager();
		}
		return userProManager;
	}

	void endCall() {
		try {
			EMClient.getInstance().callManager().endCall();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
  public void addSyncGroupListener(DataSyncListener listener) {
        if (listener == null) {
            return;
        }
        if (!syncGroupsListeners.contains(listener)) {
            syncGroupsListeners.add(listener);
        }
    }

    public void removeSyncGroupListener(DataSyncListener listener) {
        if (listener == null) {
            return;
        }
        if (syncGroupsListeners.contains(listener)) {
            syncGroupsListeners.remove(listener);
        }
    }

    public void addSyncContactListener(DataSyncListener listener) {
        if (listener == null) {
            return;
        }
        if (!syncContactsListeners.contains(listener)) {
            syncContactsListeners.add(listener);
        }
    }

    public void removeSyncContactListener(DataSyncListener listener) {
        if (listener == null) {
            return;
        }
        if (syncContactsListeners.contains(listener)) {
            syncContactsListeners.remove(listener);
        }
    }

    public void addSyncBlackListListener(DataSyncListener listener) {
        if (listener == null) {
            return;
        }
        if (!syncBlackListListeners.contains(listener)) {
            syncBlackListListeners.add(listener);
        }
    }

    public void removeSyncBlackListListener(DataSyncListener listener) {
        if (listener == null) {
            return;
        }
        if (syncBlackListListeners.contains(listener)) {
            syncBlackListListeners.remove(listener);
        }
    }
	
	/**
    * Get group list from server
    * This method will save the sync state
    * @throws HyphenateException
    */
   public synchronized void asyncFetchGroupsFromServer(final EMCallBack callback){
       if(isSyncingGroupsWithServer){
           return;
       }
       
       isSyncingGroupsWithServer = true;
       
       new Thread(){
           @Override
           public void run(){
               try {
                   EMClient.getInstance().groupManager().getJoinedGroupsFromServer();
                   
                   // in case that logout already before server returns, we should return immediately
                   if(!isLoggedIn()){
                       isGroupsSyncedWithServer = false;
                       isSyncingGroupsWithServer = false;
                       noitifyGroupSyncListeners(false);
                       return;
                   }
                   
                   demoModel.setGroupsSynced(true);
                   
                   isGroupsSyncedWithServer = true;
                   isSyncingGroupsWithServer = false;
                   
                   //notify sync group list success
                   noitifyGroupSyncListeners(true);

                   if(callback != null){
                       callback.onSuccess();
                   }
               } catch (HyphenateException e) {
                   demoModel.setGroupsSynced(false);
                   isGroupsSyncedWithServer = false;
                   isSyncingGroupsWithServer = false;
                   noitifyGroupSyncListeners(false);
                   if(callback != null){
                       callback.onError(e.getErrorCode(), e.toString());
                   }
               }
           
           }
       }.start();
   }

   public void noitifyGroupSyncListeners(boolean success){
       for (DataSyncListener listener : syncGroupsListeners) {
           listener.onSyncComplete(success);
       }
   }
   
   ArrayList<String> usernames = null  ;
   public void asyncFetchContactsFromServer(final EMValueCallBack<List<String>> callback){
//       if(isSyncingContactsWithServer){
//           return;
//       }
//      usernames = new ArrayList<String>();
//       isSyncingContactsWithServer = true;
//       HttpUtils utils = new HttpUtils();
//       String Token;
//       try {
//       Token = SharedUtils.getString("token");
//       }catch (Exception e){
//           Token="00";
//       }
//       String XYURL= com.project.my.studystarteacher.utils.Constant.url.HUANXIN_URL+Token;
//
//       utils.send(HttpMethod.POST, XYURL, new RequestCallBack<String>() {
//			// 成功
//			@Override
//			public void onSuccess(ResponseInfo<String> responseInfo) {
//
//				String result = (String) responseInfo.result;
////				System.out.println("成功"+result);
//				JSONArray jsonArray;
//				try {
//					  if(!isLoggedIn()){
//	                       isContactsSyncedWithServer = false;
//	                       isSyncingContactsWithServer = false;
//	                       notifyContactsSyncListener(false);
//	                       return;
//	                   }
//					jsonArray = new JSONArray(result);
//					if (jsonArray.length()>0) {
//						  Map<String, EaseUser> userlist = new HashMap<String, EaseUser>();
//						for (int i = 0; i < jsonArray.length(); i++) {
//						JSONObject jsonObject5 = (JSONObject)jsonArray.opt(i);
//					 	String userName = jsonObject5.getString("parentName");
//				        String userPic = jsonObject5.getString("parentHeaderImg");
//				        String username = jsonObject5.getString("parentTelephone");
////                            System.out.println("11111111");
//                            EaseUser user = new EaseUser(username);
//	                       user.setNick(userName);
//	                       user.setNickname(userName);
//	                       user.setAvatar(com.project.my.studystarteacher.utils.Constant.url.URL_IMAGE_XS_HEAD() + userPic);
//	                       userlist.put(username, user);
//	                       usernames.add(hxIdFrom);
//	                       EaseCommonUtils.setUserInitialLetter(user);
//
//                        }
//						  getContactList().clear();
//		                   getContactList().putAll(userlist);
//		                    // save the contact list to database
//		                   UserDao dao = new UserDao(appContext);
//		                   List<EaseUser> users = new ArrayList<EaseUser>(userlist.values());
//		                   dao.saveContactList(users);
//
//		                   demoModel.setContactSynced(true);
//		                   EMLog.d(TAG, "set contact syn status to true");
//
//		                   isContactsSyncedWithServer = true;
//		                   isSyncingContactsWithServer = false;
//
//		                   //notify sync success
//		                   notifyContactsSyncListener(true);
//
//		                   getUserProfileManager().asyncFetchContactInfosFromServer(usernames,new EMValueCallBack<List<EaseUser>>() {
//
//		                       @Override
//		                       public void onSuccess(List<EaseUser> uList) {
//		                           updateContactList(uList);
//		                           getUserProfileManager().notifyContactInfosSyncListener(true);
//		                       }
//
//		                       @Override
//		                       public void onError(int error, String errorMsg) {
//		                       }
//		                   });
//		                   if(callback != null){
//		                       callback.onSuccess(usernames);
//		                   }
//						}
//				} catch (JSONException e) {
//									e.printStackTrace();
//									 demoModel.setContactSynced(false);
//					                   isContactsSyncedWithServer = false;
//					                   isSyncingContactsWithServer = false;
//					                   notifyContactsSyncListener(false);
//					                   e.printStackTrace();
//					                   if(callback != null){
//
//					                   }
//				}
//
//			}
//
//			@Override
//			public void onFailure(HttpException error, String msg) {
//
//
//			}
//		});
		

   }

   public void notifyContactsSyncListener(boolean success){
       for (DataSyncListener listener : syncContactsListeners) {
           listener.onSyncComplete(success);
       }
   }
   
   public void asyncFetchBlackListFromServer(final EMValueCallBack<List<String>> callback){
       
       if(isSyncingBlackListWithServer){
           return;
       }
       
       isSyncingBlackListWithServer = true;
       
       new Thread(){
           @Override
           public void run(){
               try {
                   List<String> usernames = EMClient.getInstance().contactManager().getBlackListFromServer();
                   
                   // in case that logout already before server returns, we should return immediately
                   if(!isLoggedIn()){
                       isBlackListSyncedWithServer = false;
                       isSyncingBlackListWithServer = false;
                       notifyBlackListSyncListener(false);
                       return;
                   }
                   
                   demoModel.setBlacklistSynced(true);
                   
                   isBlackListSyncedWithServer = true;
                   isSyncingBlackListWithServer = false;
                   
                   notifyBlackListSyncListener(true);
                   if(callback != null){
                       callback.onSuccess(usernames);
                   }
               } catch (HyphenateException e) {
                   demoModel.setBlacklistSynced(false);
                   
                   isBlackListSyncedWithServer = false;
                   isSyncingBlackListWithServer = true;
                   e.printStackTrace();
                   
                   if(callback != null){
                       callback.onError(e.getErrorCode(), e.toString());
                   }
               }
               
           }
       }.start();
   }
	
	public void notifyBlackListSyncListener(boolean success){
        for (DataSyncListener listener : syncBlackListListeners) {
            listener.onSyncComplete(success);
        }
    }
    
    public boolean isSyncingGroupsWithServer() {
        return isSyncingGroupsWithServer;
    }

    public boolean isSyncingContactsWithServer() {
        return isSyncingContactsWithServer;
    }

    public boolean isSyncingBlackListWithServer() {
        return isSyncingBlackListWithServer;
    }
    
    public boolean isGroupsSyncedWithServer() {
        return isGroupsSyncedWithServer;
    }

    public boolean isContactsSyncedWithServer() {
        return isContactsSyncedWithServer;
    }

    public boolean isBlackListSyncedWithServer() {
        return isBlackListSyncedWithServer;
    }
	
    synchronized void reset(){
        isSyncingGroupsWithServer = false;
        isSyncingContactsWithServer = false;
        isSyncingBlackListWithServer = false;
        
        demoModel.setGroupsSynced(false);
        demoModel.setContactSynced(false);
        demoModel.setBlacklistSynced(false);
        
        isGroupsSyncedWithServer = false;
        isContactsSyncedWithServer = false;
        isBlackListSyncedWithServer = false;

        isGroupAndContactListenerRegisted = false;
        
        setContactList(null);
        setRobotList(null);
        getUserProfileManager().reset();
        DemoDBManager.getInstance().closeDB();
    }

    public void pushActivity(Activity activity) {
        easeUI.pushActivity(activity);
    }

    public void popActivity(Activity activity) {
        easeUI.popActivity(activity);
    }

}
