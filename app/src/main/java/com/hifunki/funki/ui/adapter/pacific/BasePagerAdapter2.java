/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hifunki.funki.ui.adapter.pacific;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hifunki.funki.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * PagerAdapter for ViewPager
 */
public abstract class BasePagerAdapter2<T extends Item, H extends ViewHolder>
        extends PagerAdapter implements DataIO<T> {
    protected LayoutInflater inflater;
    protected final ArrayList<T> data;
    protected Queue<View> cacheViews;
    protected int currentPosition = -1;
    protected View currentTarget;
    protected OnDataSetChanged onDataSetChanged;

    public BasePagerAdapter2() {
        this(null);
    }

    public BasePagerAdapter2(List<T> data) {
        this.data = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        this.cacheViews = new LinkedList<>();
    }

    @Override
    public void clear() {
        if (data.size() > 0) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean contains(T element) {
        return data.contains(element);
    }

    @Override
    public boolean containsAll(@NonNull List<T> list) {
        return data.containsAll(list);
    }

    @Override
    public void add(T element) {
        if (data.add(element)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void add(int index, T element) {
        final int size = data.size();
        data.add(index, element);
        if (data.size() > size) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void addAll(@NonNull List<T> list) {
        if (data.addAll(list)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void addAll(int index, @NonNull List<T> list) {
        if (data.addAll(index, list)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public T remove(int index) {
        T obj = data.remove(index);
        if (obj != null) {
            notifyDataSetChanged();
        }
        return obj;
    }

    @Override
    public void remove(T element) {
        if (data.remove(element)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void removeAll(@NonNull List<T> list) {
        if (data.removeAll(list)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void retainAll(@NonNull List<T> list) {
        if (data.retainAll(list)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void replaceAll(@NonNull List<T> list) {
        if (data.size() > 0) {
            data.clear();
        }
        data.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void replace(T oldElement, T newElement) {
        replaceAt(data.indexOf(oldElement), newElement);
    }

    @Override
    public void replaceAt(int index, T element) {
        T obj = data.set(index, element);
        if (obj != null) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int indexOf(T element) {
        return data.indexOf(element);
    }

    @Override
    public int lastIndexOf(T element) {
        return data.lastIndexOf(element);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return data.subList(fromIndex, toIndex);
    }

    @Override
    public T get(int position) {
        if (position >= data.size())
            return null;
        return data.get(position);
    }

    @Override
    public ArrayList<T> getAll() {
        return data;
    }

    @Override
    public int getCount() {
        return size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        H holder;
        T item = get(position);
        View convertView = cacheViews.poll();
        if (convertView == null) {
            if (inflater == null) {
                inflater = LayoutInflater.from(container.getContext());
            }
            convertView = inflater.inflate(item.getLayout(), container, false);
            holder = createViewHolder(convertView);
            convertView.setTag(R.integer.adapter_view_holder, holder);
        } else {
            holder = (H) convertView.getTag(R.integer.adapter_view_holder);
        }
        holder.setPosition(position);
        item.bind(holder);
        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            View view = (View) object;
            T item = get(position);
            ViewHolder holder = (ViewHolder) view.getTag(R.integer.adapter_view_holder);
            item.unbind(holder);
            container.removeView(view);
            cacheViews.add(view);
        }
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        this.currentPosition = position;
        if (object instanceof View) {
            this.currentTarget = (View) object;
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (size() <= 0) {
            if (onDataSetChanged != null) {
                onDataSetChanged.onEmptyData();
            }
        } else {
            if (onDataSetChanged != null) {
                onDataSetChanged.onHasData();
            }
        }
    }

    public OnDataSetChanged getOnDataSetChanged() {
        return onDataSetChanged;
    }

    public void setOnDataSetChanged(OnDataSetChanged onDataSetChanged) {
        this.onDataSetChanged = onDataSetChanged;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public View getCurrentTarget() {
        return currentTarget;
    }

    protected abstract H createViewHolder(View convertView);
}
