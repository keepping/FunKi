package com.hifunki.funki.module.rank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hifunki.funki.R;

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

    private static final int RANK_TYPE_COUNT = 2;
    private static final int RANK_ANCHOR_TOP = 0;
    private static final int RANK_ANCHOR_DOWN= 1;
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
    public int getViewTypeCount() {
        return RANK_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if(position < 3){
            return RANK_ANCHOR_TOP;
        }else {
            return RANK_ANCHOR_DOWN;
        }

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        ViewHolderDown viewHolderDown;
        int type = getItemViewType(i);

        if(view == null){
            switch (type){
                case RANK_ANCHOR_TOP:
                    view = mInflater.inflate(R.layout.item_rank_anchor , null);
                    viewHolder = new ViewHolder();
                    view.setTag(viewHolder);
                    break;
                case RANK_ANCHOR_DOWN:
                    view = mInflater.inflate(R.layout.item_rank_anchor_down , null);
                    viewHolderDown = new ViewHolderDown();
                    view.setTag(viewHolderDown);
                    break;
                default:

                    break;
            }
        }else {
            switch (type){
                case RANK_ANCHOR_TOP:
                    viewHolder = (ViewHolder) view.getTag();
                    break;
                case RANK_ANCHOR_DOWN:
                    viewHolderDown = (ViewHolderDown) view.getTag();
                    break;

                default:
                    break;
            }

        }
        return view;
    }


    private class ViewHolder{
        private ImageView mRankNo;
        private ImageView mUserBg;

    }

    private class ViewHolderDown{


    }

}
