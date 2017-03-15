package com.hifunki.funki.module.login;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.base.application.ApplicationMain;
import com.hifunki.funki.module.home.activity.HomeActivity;
import com.hifunki.funki.module.login.adapter.EighteenAdapter;
import com.hifunki.funki.module.login.entity.EighteenEntity;
import com.hifunki.funki.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 18禁页面
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.login.EighteenBearActivity.java
 * @link
 * @since 2017-03-08 16:23:23
 */
public class EighteenBearActivity extends BaseActivity {


    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.tv_enter)
    TextView tvEnter;
    @BindView(R.id.rl_eighteen_choose)
    RelativeLayout rlEighteenChoose;
    @BindView(R.id.rv_eighteen_choose)
    RecyclerView rvEighteenChoose;
    @BindView(R.id.rl_eighteen)
    RelativeLayout rlEighteen;
    private List<EighteenEntity> eighteenEntities;

    @Override
    protected int getViewResId() {
        return R.layout.activity_eighteen_bear;
    }

    @Override
    protected void initDatas() {
//        Utils.init(getApplicationContext());
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

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {
        EighteenAdapter eighteenAdapter = new EighteenAdapter(R.layout.item_eighteen, eighteenEntities);
        rvEighteenChoose.setLayoutManager(new LinearLayoutManager(this));
        rvEighteenChoose.setAdapter(eighteenAdapter);
    }


    @OnClick({R.id.tv_exit, R.id.tv_enter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_exit:
                ToastUtils.showShortToastSafe(R.string.exit_app);
                //延迟1秒执行
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ApplicationMain.finishAllActivity();
                    }
                }, 1000);

                break;
            case R.id.tv_enter:
                HomeActivity.show(this, EighteenBearActivity.this);
                break;

        }
    }
}
