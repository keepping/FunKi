package com.hifunki.funki.library.base.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.view.inputmethod.InputMethodManager;

import com.hifunki.funki.library.R;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.library.base.activity.AccountBaseActivity.java
 * @link
 * @since 2017-03-06 14:26:26
 */
public class AccountBaseActivity extends BaseTitleActivity {

    public static final String ACTION_ACCOUNT_FINISH_ALL = "funki.hifunki.com.action.finish.all";

    protected LocalBroadcastManager mManager;
    private BroadcastReceiver mReceiver;
    protected InputMethodManager mInputMethodManager;
    private boolean mKeyBoardIsActive;

    @Override
    protected int getViewResId() {
        return R.layout.activity_account_base;
    }

    @Override
    protected void initDatas() {
        registerLocalReceiver();
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    protected boolean sendLocalReceiver() {
        if (mManager != null) {
            Intent intent = new Intent();
            intent.setAction(ACTION_ACCOUNT_FINISH_ALL);
            return mManager.sendBroadcast(intent);
        }

        return false;
    }


    /**
     * register localReceiver
     */
    private void registerLocalReceiver() {
        if (mManager == null)
            mManager = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ACCOUNT_FINISH_ALL);
        if (mReceiver == null)
            mReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (ACTION_ACCOUNT_FINISH_ALL.equals(action)) {
                        finish();
                    }
                }
            };
        mManager.registerReceiver(mReceiver, filter);
    }

    protected void hideKeyBoard(IBinder windowToken) {
        InputMethodManager inputMethodManager = this.mInputMethodManager;
        if (inputMethodManager == null) return;
        boolean active = inputMethodManager.isActive();
        if (active) {
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        }
    }

    /**
     * update keyBord active status
     *
     * @param isActive isActive
     */
    protected void updateKeyBoardActiveStatus(boolean isActive) {
        this.mKeyBoardIsActive = isActive;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideKeyBoard(getCurrentFocus().getWindowToken());
        if (mManager != null) {
            if (mReceiver != null)
                mManager.unregisterReceiver(mReceiver);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
