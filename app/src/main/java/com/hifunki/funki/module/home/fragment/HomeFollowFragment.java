package com.hifunki.funki.module.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.home.viewholder.Follow_Live;
import com.hifunki.funki.module.home.viewholder.Follow_Movie;
import com.hifunki.funki.module.home.viewholder.Follow_Pic;
import com.hifunki.funki.net.back.Post;
import com.powyin.scroll.adapter.MultipleRecycleAdapter;
import com.powyin.scroll.widget.SwipeControl;
import com.powyin.scroll.widget.SwipeRefresh;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


/**
 * 首页关注Fragment
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.fragment.HomeFollowFragment.java
 * @link
 * @since 2017-03-13 16:46:46
 */
public class HomeFollowFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String[][] images = new String[][]{{"http://t2.27270.com/uploads/tu/201510/249/8.jpg", "640", "960"}
            , {"http://t2.27270.com/uploads/tu/201606/76/32.jpg", "640", "640"}
            , {"http://t2.27270.com/uploads/tu/201510/249/9.jpg", "640", "640"}
            , {"http://t2.27270.com/uploads/tu/201606/112/17.jpg", "640", "640"}
            , {"http://t2.27270.com/uploads/tu/201510/249/3.jpg", "640", "640"}
            , {"http://t2.27270.com/uploads/tu/201606/62/28.jpg", "640", "640"}
            , {"http://t2.27270.com/uploads/tu/201606/76/34.jpg", "640", "640"}
            , {"http://t2.27270.com/uploads/tu/201606/73/slt.jpg", "640", "640"}
            , {"http://img2.imgtn.bdimg.com/it/u=3347259689,1828160575&fm=21&gp=0.jpg", "640", "640"}
            , {"http://img1.imgtn.bdimg.com/it/u=3607821315,1190508392&fm=21&gp=0.jpg", "640", "640"}
            , {"http://img4.imgtn.bdimg.com/it/u=2495945657,2561148855&fm=21&gp=0.jpg", "640", "640"}
            , {"http://t2.27270.com/uploads/tu/201510/249/7.jpg", "800", "650"}};

    private String mParam1;
    private String mParam2;


    public static HomeFollowFragment newInstance(String param1, String param2) {
        HomeFollowFragment fragment = new HomeFollowFragment();
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
        return R.layout.fragment_home_follow;
    }

    @BindView(R.id.swipe_refresh)
    SwipeRefresh swipeRefresh;
    @BindView(R.id.recycle)
    RecyclerView recyclerView;

    MultipleRecycleAdapter<Post> multipleRecycleAdapter;

    List<String> uri = Arrays.asList("http://t2.27270.com/uploads/tu/201606/112/17.jpg",

            "http://t2.27270.com/uploads/tu/201510/249/3.jpg",
            "http://t2.27270.com/uploads/tu/201606/62/28.jpg",
            "http://t2.27270.com/uploads/tu/201606/76/34.jpg",
            "http://t2.27270.com/uploads/tu/201606/73/slt.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3347259689,1828160575&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=3607821315,1190508392&fm=21&gp=0.jpg");

    @Override
    protected void initView(View root) {
        super.initView(root);

        multipleRecycleAdapter = MultipleRecycleAdapter.getByViewHolder(getActivity(), Follow_Live.class, Follow_Movie.class, Follow_Pic.class);
        recyclerView.setAdapter(multipleRecycleAdapter);

        for(int i=0 ;i < 3;i++){
            multipleRecycleAdapter.addLast(new Post(i%3+1, uri));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        swipeRefresh.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("---------------------------onRefresh");
                multipleRecycleAdapter.deleteAllData();
                multipleRecycleAdapter.addLast(new Post(2, uri));
                multipleRecycleAdapter.addLast(new Post(2, uri));
                multipleRecycleAdapter.addLast(new Post(1, uri));
                multipleRecycleAdapter.addLast(new Post(3, uri));
                multipleRecycleAdapter.addLast(new Post(3, uri));
                multipleRecycleAdapter.addLast(new Post(1, uri));
                if(multipleRecycleAdapter.getDataList().size()>=5){
                    swipeRefresh.setSwipeModel(SwipeControl.SwipeModel.SWIPE_BOTH);
                }
                swipeRefresh.finishRefresh();
            }

            @Override
            public void onLoading() {
                multipleRecycleAdapter.addLast(new Post(2, uri));
                multipleRecycleAdapter.addLast(new Post(2, uri));
                multipleRecycleAdapter.addLast(new Post(1, uri));
                System.out.println("---------------------------onLoading");

            }
        });


    }
}
