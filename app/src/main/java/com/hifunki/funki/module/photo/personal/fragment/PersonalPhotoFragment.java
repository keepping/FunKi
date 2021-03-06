package com.hifunki.funki.module.photo.personal.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.photo.personal.activity.PersonalPhotoActivity;
import com.hifunki.funki.module.photo.personal.adapter.PersonalPhotoAdapter;
import com.hifunki.funki.module.photo.personal.entity.PersonalPhotoEntity;
import com.hifunki.funki.module.photo.personal.inter.OnPhotoSelectAllListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.photo.personal.fragment.PersonalPhotoFragment.java
 * @link
 * @since 2017-04-13 10:56:56
 */
public class PersonalPhotoFragment extends BaseFragment {

    @BindView(R.id.rl_person_photo)
    RecyclerView rlPersonPhoto;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<PersonalPhotoEntity> personalPhotoEntities;
    private PersonalPhotoAdapter adapter;
    private String TAG = getClass().getSimpleName();
    private HashMap<Integer, Boolean> mapSelect;
    private boolean isSelect = false;

    public static PersonalPhotoFragment newInstance(int param1, String param2) {
        PersonalPhotoFragment fragment = new PersonalPhotoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
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
        return R.layout.fragment_person_photo;
    }

    @Override
    protected void initVariable() {
        super.initVariable();
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        personalPhotoEntities = new ArrayList<>();
        if (mParam1 == PersonalPhotoActivity.VALUE_ME_PHOTO_TO_GALLERY) {
            PersonalPhotoEntity personalEntity = new PersonalPhotoEntity(PersonalPhotoEntity.CAMERA, CommonConst.photo);
            personalPhotoEntities.add(personalEntity);
        }
        for (int i = 0; i < 50; i++) {
            PersonalPhotoEntity personalEntity1 = new PersonalPhotoEntity(PersonalPhotoEntity.PHOTO, CommonConst.photo);
            personalPhotoEntities.add(personalEntity1);
        }
        for (int j = 0; j < personalPhotoEntities.size(); j++) {
            mapSelect = new HashMap<>();
            mapSelect.put(j, false);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        PersonalPhotoActivity activity = (PersonalPhotoActivity) getActivity();
        activity.setOnPhotoSelectAllListener(new OnPhotoSelectAllListener() {
            @Override
            public void selectAll(final boolean isSelectAll) {
                isSelect = isSelectAll;
                adapter.setIsSelect(isSelectAll);
            }
        });

    }

    @Override
    protected void initAdapter() {
        super.initAdapter();

        adapter = new PersonalPhotoAdapter(personalPhotoEntities, mapSelect);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rlPersonPhoto.setLayoutManager(gridLayoutManager);
        rlPersonPhoto.setAdapter(adapter);
    }

    public void onButtonPressed(Uri uri, boolean isSelectAl) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri, isSelectAl);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri, boolean isSelectAll);
    }

}
