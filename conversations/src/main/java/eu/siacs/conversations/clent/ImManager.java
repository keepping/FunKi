package eu.siacs.conversations.clent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import eu.siacs.conversations.entities.Account;
import eu.siacs.conversations.entities.Conversation;
import eu.siacs.conversations.entities.Message;
import eu.siacs.conversations.services.XmppConnectionService;
import eu.siacs.conversations.xmpp.forms.Data;
import eu.siacs.conversations.xmpp.jid.InvalidJidException;
import eu.siacs.conversations.xmpp.jid.Jid;

/**
 * Created by powyin on 2017/4/26.
 */

public class ImManager {

    private static ImManager mImManager;


    public static ImManager getDefault() {
        return mImManager;
    }

    private ImManager(Context context) {
        this.mContext = context;
    }

    private Context mContext;


    public static void init(Context context) {
        mImManager = new ImManager(context);
        Intent intent = new Intent(context, XmppConnectionService.class);
        context.startService(intent);
        context.bindService(intent, mImManager.mConnection, Context.BIND_AUTO_CREATE);
    }

    public static final String EXTRA_ACCOUNT = "account";
    public XmppConnectionService xmppConnectionService;


    protected ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            XmppConnectionService.XmppConnectionBinder binder = (XmppConnectionService.XmppConnectionBinder) service;
            xmppConnectionService = binder.getService();
            connetToJid();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.e(getClass().getSimpleName(), "appcation sisconnect service ? ");
        }
    };


//--------------------------------------------信息出口业务层不应该调用---------------------------------------------->>>>>>>>>

    // 用户关系改变
    public void invokeNotifyRelationship(Account account) {

        Log.i(getClass().getSimpleName(), "invokeNotifyRelationship : " + account);

    }

    // 会话记录
    public void invokeNotifyConversationRoster(Account account) {

        Log.i(getClass().getSimpleName(), "invokeNotifyConversationRoster : " + account);

    }

    // 用户请求操作批准
    public boolean invokeNotifyCaptchRequest(Account account, String id, Data data, Bitmap bitmap) {

        Log.i(getClass().getSimpleName(), "invokeNotifyConversationRoster : " + account);

        return false;
    }

    private final Set<OnUpdateConversation> updateConversations = new HashSet<>();

    // 会话设置 消息记录 未阅读消息数
    public void invokeUpdateConversation(Conversation conversation, Message... messages) {
        synchronized (updateConversations){
            for(OnUpdateConversation current : updateConversations){
                current.onFetch(conversation,messages);
            }
        }

        Log.i(getClass().getSimpleName(), "invokeUpdateConversation : " + conversation + messages);

    }

    // 单条消息变更
    public void invokeUpdateMessage(Message message) {
        Log.i(getClass().getSimpleName(), "invokeUpdateMessage : " + message);

    }

    // 用户信息变更
    public void invokeUpdateUserInfo(Jid jid) {
        Log.i(getClass().getSimpleName(), "invokeUpdateUserInfo : " + jid);

    }

    // 用户登陆状态改变
    public void invokeUpdateAccount(Account account) {
        Log.i(getClass().getSimpleName(), "invokeUpdateAccount : " + account);

    }

    //<<<<<<<<<--------------------------------------------信息出口业务层不应该调用-----------------------------------------
    private Jid jid;
    private Account mAccount;

    private void connetToJid() {
        try {
            jid = Jid.fromString("ccc@192.168.100.163/android");
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAccount = new Account(jid, "ccc");
        mAccount.setPort(5222);
        mAccount.setHostname(null);
        mAccount.setOption(Account.OPTION_USETLS, true);
        mAccount.setOption(Account.OPTION_USECOMPRESSION, true);
        mAccount.setOption(Account.OPTION_REGISTER, false);
        xmppConnectionService.createAccount(mAccount);
    }


    private final String Tag = "ImManager";





    //---------------------------------------------------------------------------------------->>> 普通业务

    public Conversation createConversation(String jid){
        if (TextUtils.isEmpty(jid)) {
            Log.e(Tag, "joinConferenceChat no jid");
            return null;
        }
        Jid conferenceJid = null;
        try {
            conferenceJid = Jid.fromString(jid);
        } catch (final InvalidJidException e) {
            return null;
        }
        return xmppConnectionService
                .findOrCreateConversation(mAccount,conferenceJid, true, true, true);
    }

    public void regester(OnUpdateConversation listener ) {
        synchronized (updateConversations){
            updateConversations.add(listener);
        }
    }

    public void unRegester( OnUpdateConversation listerner){
        updateConversations.remove(listerner);
    }


    public Conversation joinSingleChat(String jid) {
        return null;
    }


}
