package com.hifunki.funki.util;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;


public class ViewUtil {

    // support AbsListView RecyclerView ScrollView NestedScrollView
    public static void adjustScrollViewHei(final View scrollView) {

        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                boolean needReSize = false;
                needReSize = scrollView.canScrollVertically(-1) || scrollView.canScrollVertically(1);
                if (!needReSize) {
                    if (scrollView instanceof AbsListView) {
                        AbsListView absListView = (AbsListView) scrollView;
                        ListAdapter adapter = absListView.getAdapter();

                        needReSize = adapter != null && adapter.getCount() != absListView.getChildCount();

                        if (!needReSize) {
                            if (absListView.getChildCount() == 0) {
                                needReSize = absListView.getLayoutParams().height != absListView.getPaddingTop() + absListView.getPaddingBottom();
                            } else {

                                View firstVisible = absListView.getChildAt(0);
                                needReSize = firstVisible.getTop() != absListView.getPaddingTop();
                                if (!needReSize) {
                                    View lastVisible = absListView.getChildAt(absListView.getChildCount() - 1);
                                    needReSize = lastVisible.getBottom() != absListView.getHeight() - absListView.getPaddingBottom();
                                }

                                View lastVisible = absListView.getChildAt(absListView.getChildCount() - 1);
                                System.out.println("::::::  " + (absListView.getHeight() - absListView.getPaddingBottom()));
                                System.out.println(":::     " + lastVisible.getBottom());
                            }
                        }
                    } else if (scrollView instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) scrollView;
                        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                        needReSize = layoutManager != null && layoutManager.getChildCount() != recyclerView.getChildCount();

                        if (!needReSize) {
                            if (recyclerView.getChildCount() == 0) {
                                needReSize = recyclerView.getLayoutParams().height != recyclerView.getPaddingTop() + recyclerView.getPaddingBottom();
                            } else {
                                View firstVisible = recyclerView.getChildAt(0);
                                needReSize = firstVisible.getTop() != recyclerView.getPaddingTop();
                                if (!needReSize) {
                                    View lastVisible = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
                                    needReSize = lastVisible.getBottom() != recyclerView.getHeight() - recyclerView.getPaddingBottom();
                                }
                            }
                        }
                    } else if (scrollView instanceof ScrollView) {
                        ScrollView tem = (ScrollView) scrollView;
                        if (tem.getChildCount() == 0) {
                            needReSize = tem.getLayoutParams().height != tem.getPaddingTop() + tem.getPaddingBottom();
                        } else {
                            View child = tem.getChildAt(0);
                            needReSize = child.getTop() != tem.getPaddingTop() || child.getBottom() != tem.getPaddingBottom();
                        }
                    } else if (scrollView instanceof NestedScrollView) {
                        NestedScrollView tem = (NestedScrollView) scrollView;
                        if (tem.getChildCount() == 0) {
                            needReSize = tem.getLayoutParams().height != tem.getPaddingTop() + tem.getPaddingBottom();
                        } else {
                            View child = tem.getChildAt(0);
                            needReSize = child.getTop() != tem.getPaddingTop() || child.getBottom() != tem.getPaddingBottom();
                        }
                    }
                }

                if (needReSize) {
                    System.out.println(" adjust View Height ");
                    int with = scrollView.getWidth();
                    int measureWith = View.MeasureSpec.makeMeasureSpec(with, View.MeasureSpec.EXACTLY);
                    //取大值 重新衡量 高度
                    int measureHei = View.MeasureSpec.makeMeasureSpec(50000, View.MeasureSpec.AT_MOST);
                    scrollView.measure(measureWith, measureHei);

                    ViewGroup.LayoutParams myParam = scrollView.getLayoutParams();
                    myParam.height = scrollView.getMeasuredHeight();
                    scrollView.setLayoutParams(myParam);
                }
            }
        });

    }



}
