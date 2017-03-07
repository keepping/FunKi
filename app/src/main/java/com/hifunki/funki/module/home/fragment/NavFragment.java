package com.hifunki.funki.module.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.module.home.BaseFragment;
import com.hifunki.funki.module.home.widget.NavigationButton;
import com.hifunki.funki.util.ToastUtil;
import com.hifunki.funki.util.ViewUtil;

import java.util.List;

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


    NavigationButton mNavHome;
    NavigationButton mNavMsg;
    NavigationButton mNavStore;
    NavigationButton mNavMe;

    private OnNavigationReselectListener mOnNavigationReselectListener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private int mContainerId;
    private FragmentManager mFragmentManager;
    private NavigationButton mCurrentNavButton;
    private OnFragmentInteractionListener mListener;

    public NavFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavFragment newInstance(String param1, String param2) {
        NavFragment fragment = new NavFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mNavHome = (NavigationButton) root.findViewById(R.id.nav_item_home);

        // do select first
        doSelect(mNavHome);

        mNavMsg = (NavigationButton) root.findViewById(R.id.nav_item_msg);
        mNavStore = (NavigationButton) root.findViewById(R.id.nav_item_store);
        mNavMe = (NavigationButton) root.findViewById(R.id.nav_item_me);

        ViewUtil.setViewsOnClickListener(onClickListener, mNavHome, mNavMsg, mNavStore, mNavMe);//设置监听

        mNavHome.init(R.drawable.tab_icon_new,
                R.string.app_name,
                HomeFragment.class);

        mNavMsg.init(R.drawable.tab_icon_new,
                R.string.app_name,
                MsgFragment.class);

        mNavStore.init(R.drawable.tab_icon_new,
                R.string.app_name,
                StoreFragment.class);

        mNavMe.init(R.drawable.tab_icon_new,
                R.string.app_name,
                MeFragment.class);
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
        // If the new navigation is me info fragment, we intercept it
        /*
        if (newNavButton == mNavMe) {
            if (interceptMessageSkip())
                return;
        }
        */

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
//                Fragment fragment = Fragment.instantiate(mContext, newNavButton.getClx().getName(), null);
                Fragment fragment = null;
                if (newNavButton.equals(mNavHome)) {
                    fragment = NavFragment.newInstance("", "");
                } else if (newNavButton.equals(mNavMsg)) {
                    fragment = MsgFragment.newInstance("", "");
                } else if (newNavButton.equals(mNavStore)) {
                    fragment = StoreFragment.newInstance("", "");
                } else if (newNavButton.equals(mNavMe)) {
                    fragment = MeFragment.newInstance("", "");
                }
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                ft.attach(newNavButton.getFragment());
            }
        }
        ft.commit();
    }


    public interface OnNavigationReselectListener {
        void onReselect(NavigationButton navigationButton);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /**
     * 监听
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v instanceof NavigationButton) {
                NavigationButton nav = (NavigationButton) v;
                doSelect(nav);
            } else if (v.getId() == R.id.nav_item_tweet_pub) {//TODO show popupWindow
                ToastUtil.showToast(getContext(), "开启预直播");
            }
        }
    };
}
