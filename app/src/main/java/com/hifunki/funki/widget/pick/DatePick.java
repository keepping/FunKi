package com.hifunki.funki.widget.pick;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;


import com.hifunki.funki.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuyang on 16/6/3.
 */
public class DatePick extends PopWicket {

    public DatePick(Activity activity) {
        super(activity);
        init();
    }

    List<String> yearList;
    List<String> monthList;
    List<String> dayList;

    OnPickListener mOnPickListener;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM", Locale.CHINA);

    @BindView(R.id.tp_year)
    TextPicker<String> yearPick;
    @BindView(R.id.tp_mounth)
    TextPicker<String> monthPick;
    @BindView(R.id.tp_day)
    TextPicker<String> dayPick;

    TextPicker.ISelectListener selectListenerImp = new TextPicker.ISelectListener() {
        @Override
        public void onSelectItemChange(TextPicker<?> textPicker, int selectItem, Object object) {
            collateDay();
        }
        private void collateDay(){
            String selectYear = yearPick.getSelectItemData();
            String selectMonth = monthPick.getSelectItemData();
            if(TextUtils.isEmpty(selectMonth)||TextUtils.isEmpty(selectYear))
                return;
            int dayCount = 0;
            try {
                calendar.setTime(simpleDate.parse(selectYear+"/"+selectMonth));
                dayCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(dayCount!=0&&dayCount!= dayList.size()){
                dayList = new ArrayList<>();
                for(int i=0;i<dayCount;i++){
                    dayList.add(String.valueOf(1+i));
                }
                dayPick.setDataList(dayList,null,dayPick.getSelectItemIndex());
            }
        }
    };

    private void init(){
        initData();
        setContentView(R.layout.pop_date_picker);
        ButterKnife.bind(this,getContentView());
        yearPick.setDataList(yearList,null, yearList.size()/2);
        monthPick.setDataList(monthList,null, monthList.size()/2);
        dayPick.setDataList(dayList,null,dayList.size()/2);
        yearPick.setSelectChangeListener(selectListenerImp);
        monthPick.setSelectChangeListener(selectListenerImp);
    }


    void initData(){
        yearList = new ArrayList<>();
        for(int i=0;i<160;i++){
            yearList.add(String.valueOf(1900+i));
        }
        monthList = new ArrayList<>();
        for(int i=0;i<12;i++){
            monthList.add(String.valueOf(i+1));
        }
        dayList = new ArrayList<>();
        for(int i=0;i<30;i++){
            dayList.add(String.valueOf(i+1));
        }
    }


    @OnClick({R.id.tv_confirm, R.id.iv_back,})
    void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                hidden();
                break;
            case R.id.tv_confirm:
                if(mOnPickListener!=null){

                    String year = yearPick.getSelectItemData();
                    String month = monthPick.getSelectItemData();
                    String day = dayPick.getSelectItemData();

                    if(year==null || month == null || day == null) return;

                    calendar.set(Integer.valueOf(year),Integer.valueOf(month)-1,Integer.valueOf(day));

                    mOnPickListener.onLocationSelect(calendar.getTime());
                }
                hidden();
                break;
        }
    }

    // 设置选择日期
    public void setSelectData(Date date){
        if(date!=null){
            calendar.setTime(date);
            yearPick.setItemSelect(String.valueOf(calendar.get(Calendar.YEAR)));
            calendar.setTime(date);
            monthPick.setItemSelect(String.valueOf(calendar.get(Calendar.MONTH)+1));
            calendar.setTime(date);
            dayPick.setItemSelect(String.valueOf(calendar.get(Calendar.DATE)));
        }
    }

    public void setOnDatePickListener(OnPickListener listener){
        this.mOnPickListener = listener;
    }

    public interface OnPickListener{
        void onLocationSelect(Date date);
    }
}
