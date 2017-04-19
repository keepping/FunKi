package com.hifunki.funki.module.photo.personal.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.photo.personal.adapter.PersonalPhotoAdapter;
import com.hifunki.funki.module.photo.personal.entity.PersonalPhotoEntity;

import java.util.ArrayList;
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

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<PersonalPhotoEntity> personalPhotoEntities;

    public static PersonalPhotoFragment newInstance(String param1, String param2) {
        PersonalPhotoFragment fragment = new PersonalPhotoFragment();
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
        return R.layout.fragment_person_photo;
    }

    @Override
    protected void initVariable() {
        super.initVariable();
        personalPhotoEntities = new ArrayList<>();
        PersonalPhotoEntity personalEntity = new PersonalPhotoEntity(PersonalPhotoEntity.CAMERA, CommonConst.photo);
        personalPhotoEntities.add(personalEntity);
        for (int i = 0; i < 50; i++) {
            PersonalPhotoEntity personalEntity1 = new PersonalPhotoEntity(PersonalPhotoEntity.PHOTO, CommonConst.photo);
            personalPhotoEntities.add(personalEntity1);
        }
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        PersonalPhotoAdapter personalPhotoAdapter = new PersonalPhotoAdapter(personalPhotoEntities);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rlPersonPhoto.setLayoutManager(gridLayoutManager);
        rlPersonPhoto.setAdapter(personalPhotoAdapter);
    }

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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
