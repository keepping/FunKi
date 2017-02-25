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

import java.util.List;

public interface DataIO<T> {

    int size();

    void clear();

    boolean isEmpty();

    boolean contains(T element);

    boolean containsAll(@NonNull List<T> list);

    void add(T element);

    void add(int index, T element);

    void addAll(@NonNull List<T> list);

    void addAll(int index, @NonNull List<T> list);

    T remove(int index);

    void remove(T element);

    void removeAll(@NonNull List<T> list);

    void retainAll(@NonNull List<T> list);

    void replace(T oldElement, T newElement);

    void replaceAt(int index, T element);

    void replaceAll(@NonNull List<T> list);

    int indexOf(T element);

    int lastIndexOf(T element);

    T get(int index);

    List<T> getAll();

    List<T> subList(int fromIndex, int toIndex);
}
