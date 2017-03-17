package com.hifunki.funki.module.show.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseCoordinatorActivity;
import com.hifunki.funki.module.home.activity.HomeActivity;
import com.hifunki.funki.module.show.adapter.ShowAdapter;
import com.hifunki.funki.module.show.entity.ShowEntity;
import com.hifunki.funki.module.show.widget.ShowPayDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 节目预告界面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.showadvance.activity.ShowActivity.java
 * @link
 * @since 2017-03-16 10:12:12
 */
public class ShowActivity extends BaseCoordinatorActivity implements View.OnClickListener {


    @BindView(R.id.rl_show)
    RecyclerView rlShow;
    private String ImagePath = "http://img1.imgtn.bdimg.com/it/u=2227804654,860253351&fm=23&gp=0.jpg";
    private List<ShowEntity> entityList;
    private ShowPayDialogBuilder builder;

    /**
     * 跳转界面方法
     *
     * @param mActivity
     */
    public static void show(HomeActivity mActivity) {
        mActivity.startActivity(new Intent(mActivity, ShowActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initDatas() {
        entityList = new ArrayList<>();
        ShowEntity showEntity1 = new ShowEntity(ImagePath, "热门", "00:00", "韩国最著名的cosplay震撼登场", false, false, true, 25, 14, 25);
        ShowEntity showEntity2 = new ShowEntity(ImagePath, "热门", "00:00", "韩国最著名的cosplay震撼登场", true, false, true, 25, 14, 25);//已经购买的
        ShowEntity showEntity3 = new ShowEntity(ImagePath, "热门", "00:00", "韩国最著名的cosplay震撼登场", false, false, true, 25, 14, 25);
        ShowEntity showEntity4 = new ShowEntity(ImagePath, "热门", "00:00", "韩国最著名的cosplay震撼登场", false, false, true, 25, 14, 25);//vip直播
        ShowEntity showEntity5 = new ShowEntity(ImagePath, "热门", "00:00", "韩国最著名的cosplay震撼登场", true, false, false, 0, 0, 0);//普通直播
        ShowEntity showEntity6 = new ShowEntity(ImagePath, "热门", "00:00", "韩国最著名的cosplay震撼登场", true, false, false, 0, 0, 0);//普通直播
        ShowEntity showEntity7 = new ShowEntity(ImagePath, "热门", "00:00", "韩国最著名的cosplay震撼登场", true, false, false, 0, 0, 0);//普通直播
        ShowEntity showEntity8 = new ShowEntity(ImagePath, "热门", "00:00", "韩国最著名的cosplay震撼登场", true, false, false, 0, 0, 0);//普通直播
        ShowEntity showEntity9 = new ShowEntity(ImagePath, "热门", "00:00", "韩国最著名的cosplay震撼登场", true, false, false, 0, 0, 0);//普通直播

        entityList.add(showEntity1);
        entityList.add(showEntity2);
        entityList.add(showEntity3);
        entityList.add(showEntity4);
        entityList.add(showEntity5);
        entityList.add(showEntity6);
        entityList.add(showEntity7);
        entityList.add(showEntity8);
        entityList.add(showEntity9);

    }

    @Override
    protected void initTitleBar() {
        ShowAdapter adapter = new ShowAdapter(getApplicationContext(), R.layout.item_show, entityList);
        rlShow.setLayoutManager(new LinearLayoutManager(this));
        rlShow.setAdapter(adapter);
        //点击recycleView的item
        rlShow.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                switch (view.getId()) {
                    case R.id.ll_show_price:
//                        startActivity(new Intent(ShowActivity.this, TestDashLineActivity.class));
                        //这里需要判断余额问题
                        View rootView = LayoutInflater.from(ShowActivity.this).inflate(R.layout.dialog_show_pay, null);
                        builder = ShowPayDialogBuilder.getInstance(ShowActivity.this, rootView);
                        builder.setViewHeight(232,210);
                        builder.show();

                        break;
                    default:
                }
            }

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }

        });
    }

    @Override
    protected void initView() {
        TextView t = (TextView) findViewById(R.id.tg_show_day);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View rootView = LayoutInflater.from(ShowActivity.this).inflate(R.layout.activity_login, null);
                builder.setViewHeight(500,500);
                builder.refreshContentView(rootView );
                builder.show();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_show_close:
                Log.e("test", "onClick: " + "dimiss");
                builder.setDimiss();
                break;
        }
    }


}
