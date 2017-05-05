package com.hifunki.funki.module.msg.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hifunki.funki.R;
import com.hifunki.funki.base.adapter.BaseHeaderAdapter;
import com.hifunki.funki.base.entity.PinnedHeaderEntity;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.util.DisplayUtil;
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.msg.fragment.ContactsFansFragment.java
 * @link
 * @since 2017-05-04 09:57:57
 */
public class ContactsFansFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rv_contacts_fans)
    RecyclerView rvContactsFans;
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private BaseHeaderAdapter<PinnedHeaderEntity<Integer>> mAdapter;

    private int[] mDogs = {R.drawable.ic_account_box_white_24dp, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram};
    private int[] mCats = {R.drawable.ic_account_box_white_24dp, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram};
    private int[] mRabbits = {R.drawable.ic_account_box_white_24dp, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram};
    private int[] mPandas = {R.drawable.ic_account_box_white_24dp, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram, R.drawable.iv_start_live_instagram};
    private List<PinnedHeaderEntity<Integer>> data;

    public ContactsFansFragment() {
    }

    public static ContactsFansFragment newInstance(String param1, String param2) {
        ContactsFansFragment fragment = new ContactsFansFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    protected int getLayoutId() {
        return R.layout.fragment_contacts_fans;
    }

    @Override
    protected void initVariable() {
        super.initVariable();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        data = new ArrayList<>();
        data.add(new PinnedHeaderEntity<>(0, BaseHeaderAdapter.TYPE_HEADER, "Dog"));
        for (int dog : mDogs) {
            data.add(new PinnedHeaderEntity<>(dog, BaseHeaderAdapter.TYPE_DATA, "Dog"));
        }
        data.add(new PinnedHeaderEntity<>(0, BaseHeaderAdapter.TYPE_HEADER, "Cat"));
        for (int cat : mCats) {
            data.add(new PinnedHeaderEntity<>(cat, BaseHeaderAdapter.TYPE_DATA, "Cat"));
        }
        data.add(new PinnedHeaderEntity<>(0, BaseHeaderAdapter.TYPE_HEADER, "Rabbit"));
        for (int rabbit : mRabbits) {
            data.add(new PinnedHeaderEntity<>(rabbit, BaseHeaderAdapter.TYPE_DATA, "Rabbit"));
        }
        data.add(new PinnedHeaderEntity<>(0, BaseHeaderAdapter.TYPE_HEADER, "Panda"));
        for (int panda : mPandas) {
            data.add(new PinnedHeaderEntity<>(panda, BaseHeaderAdapter.TYPE_DATA, "Panda"));
        }
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        mAdapter = new BaseHeaderAdapter<PinnedHeaderEntity<Integer>>(data) {

            private SparseIntArray mRandomHeights;

            @Override
            protected void addItemTypes() {
                addItemType(BaseHeaderAdapter.TYPE_HEADER, R.layout.item_pinned_header);
                addItemType(BaseHeaderAdapter.TYPE_DATA, R.layout.item_data);
            }

            @Override
            protected void convert(BaseViewHolder holder, PinnedHeaderEntity<Integer> item) {
                switch (holder.getItemViewType()) {
                    case BaseHeaderAdapter.TYPE_HEADER:
                        holder.setText(R.id.tv_animal, item.getPinnedHeaderName());
                        break;
                    case BaseHeaderAdapter.TYPE_DATA:

                        int position = holder.getLayoutPosition();

                        if (rvContactsFans.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                            // 瀑布流布局记录随机高度，就不会导致Item由于高度变化乱跑，导致画分隔线出现问题
                            // 随机高度, 模拟瀑布效果.

                            if (mRandomHeights == null) {
                                mRandomHeights = new SparseIntArray(getItemCount());
                            }

                            if (mRandomHeights.get(position) == 0) {
                                mRandomHeights.put(position, (int) DisplayUtil.dip2Px(getContext(), (int) (100 + Math.random() * 100)));
                            }

                            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
                            lp.height = mRandomHeights.get(position);
                            holder.itemView.setLayoutParams(lp);

                        }

                        holder.setText(R.id.tv_pos, position + "");
                        Glide.with(getContext()).load(item.getData()).into((ImageView) holder.getView(R.id.iv_animal));
                        break;
                }
            }

        };

        rvContactsFans.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvContactsFans.addItemDecoration(new PinnedHeaderItemDecoration.Builder(BaseHeaderAdapter.TYPE_HEADER).setDividerId(R.drawable.divider).enableDivider(true)
                .create());

        rvContactsFans.setAdapter(mAdapter);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
