package com.hifunki.funki.module.dynamic.post.activity;

import android.content.Context;
import android.content.Intent;
import android.util.LongSparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.base.adapter.EmptyAdapter;
import com.hifunki.funki.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发表动态界面
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.post.activity.PostDynamicActivity.java
 * @link
 * @since 2017-03-24 13:27:27
 */
public class PostDynamicActivity extends BaseActivity {

    @BindView(R.id.iv_dynamic_add)
    ImageView ivDynamicAdd;
    @BindView(R.id.post_grid)
    GridView postPic;
    @BindView(R.id.et_dynamic_text)
    EditText etDynamicText;

    List<ViewHolder> holderList = new ArrayList<>();

    BaseAdapter gridAdapter = new EmptyAdapter() {
        @Override
        public int getCount() {
            return holderList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getPicView(position);
        }
    };


    @OnClick({R.id.iv_dynamic_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_dynamic_add://跳转到随手拍界面
                ShotActivity.show(getApplicationContext());
                break;
        }
    }

    private enum STATUS {
        UNINIT,
        SELECT,
        IMAGE,
        MOVIE
    }

    STATUS status = STATUS.SELECT;

    public static void show(Context context) {
        context.startActivity(new Intent(context, PostDynamicActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_post_dynamic;
    }


    @Override
    protected void initVariable() {
        LongSparseArray<Integer> mCheckedIdStates = new LongSparseArray<>();

        mCheckedIdStates.put(0, 12);
        mCheckedIdStates.put(0, 11);
        mCheckedIdStates.put(0, 10);
        mCheckedIdStates.put(0, 9);
        mCheckedIdStates.put(0, 8);

        System.out.println("show " + mCheckedIdStates.size());
        holderList.add(new ViewHolder());
        holderList.add(new ViewHolder());
        holderList.add(new ViewHolder());
        holderList.add(new ViewHolder());
        holderList.add(new ViewHolder());
    }

    @Override
    protected void initView() {
        ViewUtil.adjustScrollViewHei(postPic);
        postPic.setAdapter(gridAdapter);
        gridAdapter.notifyDataSetChanged();
        updateUI();
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    void updateUI() {
        switch (status) {
            case SELECT:
                ivDynamicAdd.setVisibility(View.VISIBLE);
                postPic.setVisibility(View.GONE);
                break;
            case IMAGE:
                ivDynamicAdd.setVisibility(View.GONE);
                postPic.setVisibility(View.VISIBLE);
                break;
            case MOVIE:
                ivDynamicAdd.setVisibility(View.GONE);
                postPic.setVisibility(View.GONE);
                break;
        }
    }

    private View getPicView(int index) {
        while (holderList.size() < index + 1) {
            holderList.add(new ViewHolder());
        }
        ViewHolder holder = holderList.get(index);
        holder.upDataUI(index == holderList.size() - 1);
        return holder.mainView;
    }


    class ViewHolder {
        View mainView;
        @BindView(R.id.post_delete)
        ImageView postDelte;
        @BindView(R.id.post_add)
        ImageView postAdd;
        @BindView(R.id.post_image)
        ImageView postImage;

        ViewHolder() {
            mainView = getLayoutInflater().inflate(R.layout.item_post_dynamic, (ViewGroup) null, false);
            ButterKnife.bind(this, mainView);
        }

        void upDataUI(boolean isAdd) {
            if (isAdd) {
                postDelte.setVisibility(View.GONE);
                postImage.setVisibility(View.GONE);
                postAdd.setVisibility(View.VISIBLE);
            } else {
                postDelte.setVisibility(View.VISIBLE);
                postImage.setVisibility(View.VISIBLE);
                postAdd.setVisibility(View.GONE);
            }
        }

        @OnClick({R.id.post_add, R.id.post_delete, R.id.post_image})
        void onClick(View view) {
            switch (view.getId()) {
                case R.id.post_add:


                    break;
                case R.id.post_image:

                    break;
                case R.id.post_delete:
                    holderList.remove(this);
                    gridAdapter.notifyDataSetChanged();

                    break;
            }
        }
    }

}





































































































