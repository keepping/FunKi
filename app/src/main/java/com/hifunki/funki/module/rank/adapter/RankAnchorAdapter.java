package com.hifunki.funki.module.rank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 主播人气adapter
 *
 * @author lihao
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.rank.adapter.RankAnchorAdapter.java
 * @link
 * @since 2017-03-14 14:08:08
 */

public class RankAnchorAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<String> arrayList;

    public RankAnchorAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setList(ArrayList<String> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(i < 3){
            if(view == null){
            }
        }
        return null;
    }


    private class ViewHolder{

    }
}
