/**
 * Copyright (c) 2019 Cotta & Cush Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cottacush.android.androidbaseprojectkt.sample.basic

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cottacush.android.androidbaseprojectkt.sample.advanced.breedlist.AdvancedBreedListAdapter
import com.cottacush.android.androidbaseprojectkt.sample.basic.catlist.BreedListAdapter
import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import timber.log.Timber

@BindingAdapter("catBreedsList")
fun bindAccountsRecyclerView(recyclerView: RecyclerView, data: List<Breed>?) {
    data?.let {
        Timber.d("Update recycler view")
        val adapter = recyclerView.adapter as BreedListAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("advancedCatBreedsList")
fun bindAdvancedBreedsRecyclerView(recyclerView: RecyclerView, data: List<Breed>?) {
    data?.let {
        Timber.d("Update recycler view")
        val adapter = recyclerView.adapter as AdvancedBreedListAdapter
        adapter.submitList(data)
    }
}
