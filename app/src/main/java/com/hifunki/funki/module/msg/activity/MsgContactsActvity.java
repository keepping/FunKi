package com.hifunki.funki.module.msg.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.module.msg.adapter.MsgContactsAdapter;
import com.hifunki.funki.module.msg.fragment.ContactsCloseFriendFragment;
import com.hifunki.funki.module.msg.fragment.ContactsFansFragment;
import com.hifunki.funki.module.msg.fragment.ContactsFriendFragment;
import com.hifunki.funki.widget.bar.TopBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MsgContactsActvity extends BaseActivity implements ContactsFansFragment.OnFragmentInteractionListener, ContactsFriendFragment.OnFragmentInteractionListener, ContactsCloseFriendFragment.OnFragmentInteractionListener {

    @BindView(R.id.tbv_msg)
    TopBarView tbvMsg;
    @BindView(R.id.tb_msg_contacts)
    TabLayout tbMsgContacts;
    @BindView(R.id.vp_msg_contacts)
    ViewPager vpMsgContacts;
    private List<String> mTabTitle;


    @Override
    protected int getViewResId() {
        return R.layout.activity_msg_contacts;
    }

    @Override
    protected void initVariable() {
        //封装tab数据,数据和tab保持一致
        mTabTitle = new ArrayList<>();
        mTabTitle.add("密友");
        mTabTitle.add("好友");
        mTabTitle.add("粉丝");

        tbMsgContacts.addTab(tbMsgContacts.newTab().setText(mTabTitle.get(0)));
        tbMsgContacts.addTab(tbMsgContacts.newTab().setText(mTabTitle.get(1)));
        tbMsgContacts.addTab(tbMsgContacts.newTab().setText(mTabTitle.get(2)));
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initAdapter() {
        super.initAdapter();


        MsgContactsAdapter adapter = new MsgContactsAdapter(getSupportFragmentManager(), mTabTitle);
        vpMsgContacts.setAdapter(adapter);
        tbMsgContacts.setupWithViewPager(vpMsgContacts);
    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, MsgContactsActvity.class));
    }

    @OnClick({R.id.tbv_msg, R.id.tb_msg_contacts, R.id.vp_msg_contacts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tbv_msg:
                break;
            case R.id.tb_msg_contacts:
                break;
            case R.id.vp_msg_contacts:
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
