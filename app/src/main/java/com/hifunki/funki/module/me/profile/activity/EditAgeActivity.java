package com.hifunki.funki.module.me.profile.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;
import com.hifunki.funki.widget.pick.TextPicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.profile.activity.EditAgeActivity.java
 * @link
 * @since 2017-03-31 15:31:31
 */
public class EditAgeActivity extends BaseActivity {

    @BindView(R.id.tp_year)
    TextPicker<String> yearPick;
    @BindView(R.id.tp_mounth)
    TextPicker<String> monthPick;
    @BindView(R.id.tp_day)
    TextPicker<String> dayPick;
    List<String> yearList;
    List<String> monthList;
    List<String> dayList;
    Calendar calendar;
    SimpleDateFormat simpleDate;

    @Override
    protected int getViewResId() {
        return R.layout.activity_edit_age;
    }

    @Override
    protected void initVariable() {
        calendar = Calendar.getInstance();
        simpleDate = new SimpleDateFormat("yyyy/MM", Locale.CHINA);
        yearList = new ArrayList<>();
        for (int i = 0; i < 160; i++) {
            yearList.add(String.valueOf(1900 + i));
        }
        monthList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            monthList.add(String.valueOf(i + 1));
        }
        dayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dayList.add(String.valueOf(i + 1));
        }
        yearPick.setDataList(yearList, null, yearList.size() / 2);
        monthPick.setDataList(monthList, null, monthList.size() / 2);
        dayPick.setDataList(dayList, null, dayList.size() / 2);
        yearPick.setSelectChangeListener(selectListenerImp);
        monthPick.setSelectChangeListener(selectListenerImp);
    }

    TextPicker.ISelectListener selectListenerImp = new TextPicker.ISelectListener() {
        @Override
        public void onSelectItemChange(TextPicker<?> textPicker, int selectItem, Object object) {
            collateDay();
        }

        private void collateDay() {
            String selectYear = yearPick.getSelectItemData();
            String selectMonth = monthPick.getSelectItemData();
            if (TextUtils.isEmpty(selectMonth) || TextUtils.isEmpty(selectYear))
                return;
            int dayCount = 0;
            try {
                calendar.setTime(simpleDate.parse(selectYear + "/" + selectMonth));
                dayCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (dayCount != 0 && dayCount != dayList.size()) {
                dayList = new ArrayList<>();
                for (int i = 0; i < dayCount; i++) {
                    dayList.add(String.valueOf(1 + i));
                }
                dayPick.setDataList(dayList, null, dayPick.getSelectItemIndex());
            }
        }
    };

    @Override
    protected void initView() {

    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    public static void show(Context context) {
        context.startActivity(new Intent(context,EditAgeActivity.class));
    }
}
