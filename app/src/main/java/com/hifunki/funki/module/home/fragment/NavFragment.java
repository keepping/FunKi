package com.hifunki.funki.module.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.common.FragmentConst;
import com.hifunki.funki.module.home.BaseFragment;
import com.hifunki.funki.module.home.widget.NavigationButton;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.fragment.NavFragment.java
 * @link
 * @since 2017-03-07 13:41:41
 */
public class NavFragment extends BaseFragment {


    @BindView(R.id.nav_item_home)
    NavigationButton navItemHome;
    @BindView(R.id.nav_item_msg)
    NavigationButton navItemMsg;
    //    @BindView(R.id.nav_item)
//    ImageView navItem;
    @BindView(R.id.nav_item_store)
    NavigationButton navItemStore;
    @BindView(R.id.nav_item_me)
    NavigationButton navItemMe;

    private OnNavigationReselectListener mOnNavigationReselectListener;
    private Context mContext;
    private int mContainerId;
    private FragmentManager mFragmentManager;
    private NavigationButton mCurrentNavButton;
    private OnFragmentInteractionListener mListener;

    public NavFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        navItemHome.init(R.drawable.tab_icon_home,
                R.string.app_name,
                FragmentConst.HomeFragment);

        navItemMsg.init(R.drawable.tab_icon_msg,
                R.string.app_name,
                FragmentConst.MsgFragment);

        navItemStore.init(R.drawable.tab_icon_store,
                R.string.app_name,
                FragmentConst.StoreFragment);

        navItemMe.init(R.drawable.tab_icon_me,
                R.string.app_name,
                FragmentConst.MeFragment);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setup(Context context, FragmentManager fragmentManager, int contentId, OnNavigationReselectListener listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = contentId;
        mOnNavigationReselectListener = listener;

        // do clear
        clearOldFragment();

        // do select first
        doSelect(navItemHome);
    }

    @SuppressWarnings("RestrictedApi")
    private void clearOldFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }


    private void doSelect(NavigationButton newNavButton) {
        NavigationButton oldNavButton = null;
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton;
            if (oldNavButton == newNavButton) {
                onReselect(oldNavButton);
                return;
            }
            oldNavButton.setSelected(false);
        }
        newNavButton.setSelected(true);
        doTabChanged(oldNavButton, newNavButton);
        mCurrentNavButton = newNavButton;
    }

    private void onReselect(NavigationButton navigationButton) {
        OnNavigationReselectListener listener = mOnNavigationReselectListener;
        if (listener != null) {
            listener.onReselect(navigationButton);
        }
    }

    /**
     * 底部TAB的改变
     *
     * @param oldNavButton
     * @param newNavButton
     */
    private void doTabChanged(NavigationButton oldNavButton, NavigationButton newNavButton) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (oldNavButton != null) {
            if (oldNavButton.getFragment() != null) {
                ft.detach(oldNavButton.getFragment());
            }
        }
        if (newNavButton != null) {
            if (newNavButton.getFragment() == null) {
                Fragment fragment = null;
                if (newNavButton.equals(navItemHome)) {
                    fragment = HomeFragment.newInstance("te", "te");
                } else if (newNavButton.equals(navItemMsg)) {
                    fragment = MsgFragment.newInstance("te", "te");
                } else if (newNavButton.equals(navItemStore)) {
                    fragment = StoreFragment.newInstance("te", "te");
                } else if (newNavButton.equals(navItemMe)) {
                    fragment = MeFragment.newInstance("te", "te");
                }
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                ft.attach(newNavButton.getFragment());
            }
        }
        ft.commit();
    }


    @OnClick({R.id.nav_item_home, R.id.nav_item_msg, R.id.nav_item_store, R.id.nav_item_me})
    public void onClick(View view) {
        if (view instanceof NavigationButton) {
            NavigationButton nav = (NavigationButton) view;
            doSelect(nav);
        }
//        else if (view.getId() == R.id.nav_item) {//TODO show popupWindow
//            Toast.makeText(mContext, "开启popWindow", Toast.LENGTH_LONG).show();
//        }
    }


    public interface OnNavigationReselectListener {
        void onReselect(NavigationButton navigationButton);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
