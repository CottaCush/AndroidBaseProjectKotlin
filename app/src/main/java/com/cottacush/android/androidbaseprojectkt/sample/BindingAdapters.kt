package com.cottacush.android.androidbaseprojectkt.sample

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cottacush.android.androidbaseprojectkt.sample.catlist.BreedListAdapter
import com.cottacush.android.androidbaseprojectkt.sample.models.Breed

@BindingAdapter("catBreedsList")
fun bindAccountsRecyclerView(recyclerView: RecyclerView, data: List<Breed>?) {
    data?.let {
        val adapter = recyclerView.adapter as BreedListAdapter
        adapter.submitList(data)
    }
}