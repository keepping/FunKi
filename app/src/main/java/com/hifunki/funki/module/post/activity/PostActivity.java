package com.hifunki.funki.module.post.activity;

import android.content.Context;
import android.content.Intent;
import android.util.LongSparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.base.adapter.EmptyAdapter;
import com.hifunki.funki.module.home.widget.FKVideoView;
import com.hifunki.funki.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.post.activity.PostActivity.java
 * @link
 * @since 2017-03-24 13:27:27
 */
public class PostActivity extends BaseActivity {

    public static void show(Context context) {
        context.startActivity(new Intent(context,PostActivity.class));
    }

    private enum STATUS{
        unInit,
        select,
        pic,
        movie
    }
    STATUS status = STATUS.select;
    @BindView(R.id.post_add)
    ImageView postAdd;
    @BindView(R.id.post_grid)
    GridView postPic;
    @BindView(R.id.post_movie)
    FKVideoView postMovie;
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

    @Override
    protected int getViewResId() {
        return R.layout.activity_post;
    }

    @Override
    protected void initDatas() {

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

    void updateUI(){
        switch (status){
            case select:
                postAdd.setVisibility(View.VISIBLE);
                postPic.setVisibility(View.GONE);
                postMovie.setVisibility(View.GONE);
                break;
            case pic:
                postAdd.setVisibility(View.GONE);
                postPic.setVisibility(View.VISIBLE);
                postMovie.setVisibility(View.GONE);
                break;
            case movie:
                postAdd.setVisibility(View.GONE);
                postPic.setVisibility(View.GONE);
                postMovie.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({
            R.id.post_add,
    })
    void onClick(View view){

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
            mainView = getLayoutInflater().inflate(R.layout.item_post, (ViewGroup) null, false);
            ButterKnife.bind(this, mainView);
        }

        void upDataUI(boolean isAdd) {
            if(isAdd){
                postDelte.setVisibility(View.GONE);
                postImage.setVisibility(View.GONE);
                postAdd.setVisibility(View.VISIBLE);
            }else {
                postDelte.setVisibility(View.VISIBLE);
                postImage.setVisibility(View.VISIBLE);
                postAdd.setVisibility(View.GONE);
            }
        }
        @OnClick({
                R.id.post_add,
                R.id.post_delete,
                R.id.post_image
        })
        void onClick(View view){
            switch (view.getId()){
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





































































































