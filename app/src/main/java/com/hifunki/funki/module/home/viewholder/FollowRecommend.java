package com.hifunki.funki.module.home.viewholder;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hifunki.funki.R;
import com.hifunki.funki.module.search.adapter.HotSearchAdapter;
import com.hifunki.funki.module.search.entity.PersonEntity;
import com.hifunki.funki.net.back.Post;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.viewholder.FollowRecommend.java
 * @link
 * @since 2017-04-13 14:03:03
 */
public class FollowRecommend extends PowViewHolder<Post> {

    @BindView(R.id.rl_follow_recommend)
    RecyclerView rlFollowRecommend;
    private List<PersonEntity> personEntities;

    public FollowRecommend(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this, mItemView);
        initData();
    }

    private void initData() {
        List<String> imagePath = new ArrayList<>();
        imagePath.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1489226909&di=838a1cbd7a5ac44cd14a75defdbb3e15&src=http://pic1.5442.com:82/2015/0409/01/15.jpg!960.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953985&di=5972d42ed1127338cf6e55fee281c2d7&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F14%2F25%2F43Y58PICfJB_1024.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=14bea02cb3b79970900533f7150d4cc5&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150604%2F139-150604162F5.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=14bea02cb3b79970900533f7150d4cc5&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150604%2F139-150604162F5.jpg");

        PersonEntity personEntity = new PersonEntity(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg",
                1, "水源席子", 1, 31, "我爱北京天安门", imagePath
        );

        PersonEntity personEntity1 = new PersonEntity(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg",
                1, "水源席子", 1, 31, "我爱北京天安门", imagePath
        );

        PersonEntity personEntity2 = new PersonEntity(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489236953984&di=3ba08e016f9b18d0be82354d4c18ff00&imgtype=0&src=http%3A%2F%2Ftupian.enterdesk.com%2F2013%2Fxll%2F011%2F13%2F2%2F7.jpg",
                1, "水源席子", 1, 31, "我爱北京天安门", imagePath
        );
        personEntities = new ArrayList<>();
        personEntities.add(personEntity);
        personEntities.add(personEntity1);
        personEntities.add(personEntity2);
    }

    @Override
    protected int getItemViewRes() {
        return R.layout.viewholder_follow_recommend_;
    }

    @Override
    public void loadData(AdapterDelegate<? super Post> multipleAdapter, Post data, int postion) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        HotSearchAdapter eighteenAdapter = new HotSearchAdapter(mActivity, R.layout.item_search_hot_recommend, personEntities);
        rlFollowRecommend.setLayoutManager(linearLayoutManager);
        rlFollowRecommend.setAdapter(eighteenAdapter);

    }

}
