package com.hifunki.funki.im;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;

import eu.siacs.conversations.entities.Account;
import eu.siacs.conversations.services.XmppConnectionService;
import eu.siacs.conversations.xmpp.OnUpdateBlocklist;
import eu.siacs.conversations.xmpp.forms.Data;
import eu.siacs.conversations.xmpp.jid.Jid;

/**
 * Created by powyin on 2017/4/24.
 */

public class ImManager {
    private static ImManager mImManager;
    public static ImManager getDefault(Context context){
        if(context == null){
            throw  new RuntimeException("content cannot be null");
        }


        if(mImManager==null){
            mImManager = new ImManager(context);
        }

        return mImManager;
    }

    private Context mContext;

    private ImManager(Context context){
        this.mContext = context;
    }

    public void init(){
        Intent intent = new Intent(mContext, XmppConnectionService.class);
        mContext.startService(intent);
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public static final String EXTRA_ACCOUNT = "account";
    public XmppConnectionService xmppConnectionService;


    protected ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            System.out.println("item------------------------bindSuccess");
            XmppConnectionService.XmppConnectionBinder binder = (XmppConnectionService.XmppConnectionBinder) service;
            xmppConnectionService = binder.getService();
            registerListeners();
            connetToJid();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            System.out.println("item------------------------bindAutoCancel");
        }
    };


    private void connetToJid(){
        System.out.println("detail loging ---------------------------------------->>>");
        Jid jid = null;
        try {
            jid = Jid.fromString("ccc@192.168.100.154");
        }catch (Exception e){
            e.printStackTrace();
        }
        Account mAccount = new Account(jid,"ccc");
        mAccount.setPort(5222);
        mAccount.setHostname(null);
        mAccount.setOption(Account.OPTION_USETLS, true);
        mAccount.setOption(Account.OPTION_USECOMPRESSION, true);
        mAccount.setOption(Account.OPTION_REGISTER, false);
        xmppConnectionService.createAccount(mAccount);
    }


    private final String Tag = "ImManager";
    private void registerListeners() {

        // 回话更新 Conversation
        xmppConnectionService.setOnConversationListChangedListener(new XmppConnectionService.OnConversationUpdate() {
            @Override
            public void onConversationUpdate() {

                Log.i(Tag,"onConversationUpdate");
            }
        });

        // 账号更新
        xmppConnectionService.setOnAccountListChangedListener(new XmppConnectionService.OnAccountUpdate(){
            @Override
            public void onAccountUpdate() {

                Log.i(Tag,"onAccountUpdate");

            }
        });

        xmppConnectionService.setOnCaptchaRequestedListener(new XmppConnectionService.OnCaptchaRequested() {
            @Override
            public void onCaptchaRequested(Account account, String id, Data data, Bitmap captcha) {
                Log.i(Tag,"onCaptchaRequested");
            }
        });

        // 单聊名册更新
        xmppConnectionService.setOnRosterUpdateListener(new XmppConnectionService.OnRosterUpdate() {
            @Override
            public void onRosterUpdate() {
                Log.i(Tag,"onRosterUpdate");
            }
        });

        // 多聊名册
        xmppConnectionService.setOnMucRosterUpdateListener(new XmppConnectionService.OnMucRosterUpdate() {
            @Override
            public void onMucRosterUpdate() {
                Log.i(Tag,"onMucRosterUpdate");
            }
        });

        xmppConnectionService.setOnUpdateBlocklistListener(new OnUpdateBlocklist() {
            @Override
            public void OnUpdateBlocklist(Status status) {

            }
        });

        this.xmppConnectionService.setOnShowErrorToastListener(new XmppConnectionService.OnShowErrorToast() {
            @Override
            public void onShowErrorToast(int resId) {
                System.out.println(".....................eeeorror"+resId);
            }
        });
    }









}
