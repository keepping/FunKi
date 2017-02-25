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

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public interface RecyclerItem<T extends RecyclerViewHolder> {

    AtomicLong ID_COUNTER = new AtomicLong(0);

    void bindPayloads(T holder, List<Object> payloads);

    int getSpanSize(int spanCount, int position);

    void onViewAttachedToWindow(T holder);

    void onViewDetachedFromWindow(T holder);

    boolean isRecyclable();

    /**
     * @return item id
     */
    long diffId();

    /**
     * @return item view type
     */
    int getViewType();

    /**
     * @return item view layout resource id
     */
    int getLayout();

    /**
     * bind data callback
     *
     * @param holder view holder
     */
    void bind(T holder);

    /**
     * unbind data callback
     *
     * @param holder view holder
     */
    void unbind(T holder);
}
