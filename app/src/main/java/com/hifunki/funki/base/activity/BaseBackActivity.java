package com.hifunki.funki.base.activity;


/**
 * Created by JuQiu
 * on 16/6/20.
 */

public abstract class BaseBackActivity extends BaseTitleActivity {

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
