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