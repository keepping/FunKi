package com.hifunki.funki.module.search.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseTitleActivity;
import com.hifunki.funki.module.home.fragment.UserListFragment;
import com.hifunki.funki.module.login.entity.EighteenEntity;
import com.hifunki.funki.module.search.adapter.HotSearchAdapter;
import com.hifunki.funki.module.search.entity.PersonEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.search.activity.SearchActivity.java
 * @link
 * @since 2017-03-10 13:23:23
 */
public class SearchActivity extends BaseTitleActivity implements UserListFragment.OnFragmentInteractionListener {

    @BindView(R.id.iv_Title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_et_cancel)
    TextView tvEtCancel;
    @BindView(R.id.iv_et_close)
    ImageView ivEtClose;
    @BindView(R.id.etTitleCenter)
    EditText etTitleCenter;
    @BindView(R.id.rlEtTitle)
    RelativeLayout rlEtTitle;

//    @BindView(R.id.ll_result)
//    LinearLayout llResult;
//    @BindView(R.id.tb_home_search)
//    TabLayout tbHomeSearch;
//    @BindView(R.id.vp_search)
//    ViewPager vpSearch;

    //    @BindView(R.id.pull_recommend)
//    PullToRefreshScrollView pullRecommend;
    @BindView(R.id.rv_hot_recommend)
    RecyclerView rvHotRecommend;

    private List<EighteenEntity> eighteenEntities;

    private List<String> mTabTitle;
    private boolean isSearch;
    private List<PersonEntity> personEntities;

    public static void show(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initDatas() {
        mTabTitle = new ArrayList<>();
        mTabTitle.add("用户");
        mTabTitle.add("直播");
        mTabTitle.add("视频");
        mTabTitle.add("动态");
        List<String> imagePath = new ArrayList<>();
        imagePath.add("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=8e07ecabb7fb4316051f7d7a10a54642/5882b2b7d0a20cf482c772bf73094b36acaf997f.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489148926748&di=f84808bb9a10572a964b824b3cf95545&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F10dfa9ec8a1363270c254f53948fa0ec09fac782.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489148926912&di=61a3f3d5128f3fefb556d06deb159f99&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F0823dd54564e925838c205c89982d158ccbf4e26.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489148926912&di=61a3f3d5128f3fefb556d06deb159f99&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F0823dd54564e925838c205c89982d158ccbf4e26.jpg");

        PersonEntity personEntity = new PersonEntity(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489148832678&di=5d40ed37ef50274bcf1fe644ffd757ab&imgtype=0&src=http%3A%2F%2Fimg.25pp.com%2Fuploadfile%2Fapp%2Ficon%2F20160324%2F1458835090861273.jpg",
                1, "水源席子", 1, 31, "我爱北京天安门", imagePath
        );
        PersonEntity personEntity1 = new PersonEntity(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489148832678&di=5d40ed37ef50274bcf1fe644ffd757ab&imgtype=0&src=http%3A%2F%2Fimg.25pp.com%2Fuploadfile%2Fapp%2Ficon%2F20160324%2F1458835090861273.jpg",
                1, "水源席子", 1, 31, "我爱北京天安门", imagePath
        );
        personEntities = new ArrayList<>();
        personEntities.add(personEntity);
        personEntities.add(personEntity1);


        eighteenEntities = new ArrayList<>();

        EighteenEntity eighteenEntity1 = new EighteenEntity("1.以上所张氏的信息由企业自行提供，内容的真实性、准确性和合法性有发布企业负责。金泉刚对此不承担任何责任。");
        EighteenEntity eighteenEntity2 = new EighteenEntity("2.若用户提供给万企公司的帐号注册资料不准确，不真实，含有违法或不良信息的，万企公司有权不予注册，并保留终止用户使用万企各项服务的权利。若用户以虚假信息骗取帐号注" +
                "册或帐号头像、个人简介等注册资料存在违法和不良信息的，万企公司有权采取通知限期改正、暂停使用、注销登记等措施。对于冒用关联机构或社会名人注册帐号名称的，万企公司有权注销该帐号，并向政府主管部门进行报告。");
        EighteenEntity eighteenEntity3 = new EighteenEntity("3.承诺对其上传或者发表于星粉汇平台的所有信息，包括但不限于文字、图片、音乐、电影、表演和录音录像制品等均享有完整的知识产权或者已经得到相关权利人的合法授权。如签约主播违反上述各项规定造成星粉汇被第三人索赔的，签约主播同意全额补偿星粉汇。");
        EighteenEntity eighteenEntity4 = new EighteenEntity("4.当第三方认为签约主播上传或者发表于星粉汇的信息侵犯其权利，并根据《信息网络传播权保护条例》或者相关法律规定向星粉汇发送权利通知书时，签约主播同意星粉汇可以自行判断决定删除涉嫌侵权的签约主播上传或者发表于星粉汇的信息。");
        EighteenEntity eighteenEntity5 = new EighteenEntity("5. 本服务协议属于《万企用户注册协议》的组成部分，未规定内容依照《万企用户注册协议》及星粉汇平台公示的管理规范执行，包括但不限于《星粉汇主播行为规范》。");
        EighteenEntity eighteenEntity6 = new EighteenEntity("6.在星粉汇分享个人表演形成的表演者权归表演的签约主播，签约主播在星粉汇分享的原创内容之著作权归创作者，签约主播有权以自己的名义维护该等权利不受侵害；");
        eighteenEntities.add(eighteenEntity1);
        eighteenEntities.add(eighteenEntity2);
        eighteenEntities.add(eighteenEntity3);
        eighteenEntities.add(eighteenEntity4);
        eighteenEntities.add(eighteenEntity5);
        eighteenEntities.add(eighteenEntity6);
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initView() {
//        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(0)));
//        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(1)));
//        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(2)));
//        tbHomeSearch.addTab(tbHomeSearch.newTab().setText(mTabTitle.get(3)));


    }

    @Override
    protected void initListener() {
        etTitleCenter.addTextChangedListener(textWatcher);
    }

    @Override
    protected void initAdapter() {
        //设置rl的adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);

        HotSearchAdapter eighteenAdapter = new HotSearchAdapter(getApplicationContext(),R.layout.list_search_hot_recommend, personEntities);

        rvHotRecommend.setLayoutManager(linearLayoutManager);

        rvHotRecommend.setAdapter(eighteenAdapter);


//        HomeSearchAdapter homeSearchAdapter = new HomeSearchAdapter(this.getSupportFragmentManager(), mTabTitle);
//        vpSearch.setAdapter(homeSearchAdapter);
//        tbHomeSearch.setupWithViewPager(vpSearch);
    }
//    R.id.pull_recommend
//    R.id.tb_home_search, R.id.vp_search, R.id.ll_result,

    @OnClick({R.id.iv_Title_left, R.id.tv_et_cancel, R.id.iv_et_close, R.id.etTitleCenter, R.id.rlEtTitle, R.id.rv_hot_recommend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_Title_left:
                break;
            case R.id.tv_et_cancel:
                finish();
                break;
            case R.id.iv_et_close:
                break;
            case R.id.etTitleCenter:
                break;
            case R.id.rlEtTitle:
                break;
            case R.id.rv_hot_recommend:
                break;

//            case R.id.tb_home_search:
//                break;
//            case R.id.vp_search:
//                break;
//            case R.id.ll_result:
//                break;
//
//            case R.id.pull_recommend:
//                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * 中间输入框的监听
     */
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
//            if (!StringUtils.isEmpty(s)) {
//                isSearch = true;
//                pullRecommend.setVisibility(View.GONE);
//                llResult.setVisibility(View.VISIBLE);
//            } else {
//                isSearch = false;
//                pullRecommend.setVisibility(View.VISIBLE);
//                llResult.setVisibility(View.GONE);
//            }
        }
    };

}
