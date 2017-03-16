package com.hifunki.funki.module.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.home.adapter.HomeHotAdapter;
import com.hifunki.funki.module.home.entity.HomeHotEntity;
import com.hifunki.funki.module.home.widget.banner.Banner;
import com.hifunki.funki.module.home.widget.banner.GlideBannerImageLoader;
import com.hifunki.funki.module.home.widget.banner.OnBannerListener;
import com.hifunki.funki.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 首页热门Fragment
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.fragment.HomeHotFragment.java
 * @link
 * @since 2017-03-13 16:43:43
 */
public class HomeHotFragment extends BaseFragment implements OnBannerListener {

    @BindView(R.id.rv_hot)
    RecyclerView rvHot;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private String imagePath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489567544808&di=7490422c9d0de0ba7db28352a7c138f3&imgtype=0&src=http%3A%2F%2Fsc.jb51.net%2Fuploads%2Fallimg%2F140628%2F10-14062PAGB04.jpg";

    private OnFragmentInteractionListener mListener;
    private List<HomeHotEntity> hotEntities;
    private Banner banner;

    public HomeHotFragment() {
    }


    public static HomeHotFragment newInstance(String param1, String param2) {
        HomeHotFragment fragment = new HomeHotFragment();
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
        return R.layout.fragment_home_hot;
    }

    @Override
    protected void initData() {
        super.initData();
        hotEntities = new ArrayList<>();
        //普通直播--big
        HomeHotEntity homeHotEntity1 = new HomeHotEntity(HomeHotEntity.NORMAL_LIVE, true, imagePath, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", imagePath, false, false, 0, false, false, 0, 0, 0, false);//pgc
        HomeHotEntity homeHotEntity2 = new HomeHotEntity(HomeHotEntity.NORMAL_LIVE, false, imagePath, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", imagePath, false, false, 0, false, false, 0, 0, 0, false);//ugc
        //等级直播--big
        HomeHotEntity homeHotEntity3 = new HomeHotEntity(HomeHotEntity.NORMAL_LIVE, true, imagePath, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", imagePath, false, true, 75, false, false, 0, 0, 0, false);//pgc
        HomeHotEntity homeHotEntity4 = new HomeHotEntity(HomeHotEntity.NORMAL_LIVE, true, imagePath, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", imagePath, false, true, 75, false, false, 0, 0, 0, false);//ugc
        //普通视频--big
        HomeHotEntity homeHotEntity5 = new HomeHotEntity(HomeHotEntity.NORMAL_LIVE, true, imagePath, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", imagePath, true, false, 0, false, false, 0, 0, 0, false);//only pgc

        //门票直播--small view
        HomeHotEntity homeHotEntity6 = new HomeHotEntity(HomeHotEntity.TICKET_LIVE, true, imagePath, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", imagePath, false, false, 0, false, true, 50, 25, 42, false);// pgc
        HomeHotEntity homeHotEntity7 = new HomeHotEntity(HomeHotEntity.TICKET_LIVE, true, imagePath, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", imagePath, false, false, 0, false, true, 100, 25, 84, false);//ugc
        //门票视频--small
        HomeHotEntity homeHotEntity8 = new HomeHotEntity(HomeHotEntity.TICKET_LIVE, true, imagePath, "嘻哈朋克", "新西兰", "Japanese", 45212, "欢迎来到我的直播间", "电音狂魔", "双性变", imagePath, true, false, 0, false, false, 0, 0, 0, true);//only pgc

        hotEntities.add(homeHotEntity1);
        hotEntities.add(homeHotEntity2);
        hotEntities.add(homeHotEntity3);
        hotEntities.add(homeHotEntity4);
        hotEntities.add(homeHotEntity5);
        hotEntities.add(homeHotEntity6);
        hotEntities.add(homeHotEntity7);
        hotEntities.add(homeHotEntity8);


    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        rvHot.setLayoutManager(new LinearLayoutManager(getContext()));
        HomeHotAdapter homeHotAdapter=new HomeHotAdapter(getContext(),hotEntities);
        rvHot.setAdapter(homeHotAdapter);
        homeHotAdapter.addHeaderView(getHeadView());//获取头部视图



    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
    //获取头部ViewPager视图
    private View getHeadView() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.vp_home_hot, null);
        banner = (Banner) headView.findViewById(R.id.banner_home_hot);

        banner.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DisplayUtil.dip2Px(getContext(),135)));
        //简单使用
        List<String> strings=new ArrayList<>();

        strings.add("http://img5.imgtn.bdimg.com/it/u=2946893755,898530310&fm=23&gp=0.jpg");
        strings.add("http://pic27.nipic.com/20130320/3822951_105204803000_2.jpg");
        strings.add("http://pic.58pic.com/58pic/14/27/56/97H58PICjsU_1024.jpg");
        strings.add("http://img05.tooopen.com/images/20150105/sy_78543795524.jpg");

        banner.setImages(strings)
                .setImageLoader(new GlideBannerImageLoader())
                .setOnBannerListener(this)
                .start();
//        home_hot_banner
        return banner;
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
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //banner点击
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getContext(),"你点击了："+position,Toast.LENGTH_SHORT).show();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
