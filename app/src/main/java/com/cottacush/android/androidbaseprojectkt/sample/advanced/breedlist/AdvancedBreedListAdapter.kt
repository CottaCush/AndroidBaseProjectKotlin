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
package com.cottacush.android.androidbaseprojectkt.sample.advanced.breedlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cottacush.android.androidbaseprojectkt.R
import com.cottacush.android.androidbaseprojectkt.databinding.ItemCatListBinding
import com.cottacush.android.androidbaseprojectkt.sample.models.Breed

class AdvancedBreedListAdapter(
    val breedClickListener: (Breed) -> Unit
) : ListAdapter<Breed, AdvancedBreedListAdapter.CatBreedsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedsViewHolder {
        return CatBreedsViewHolder(
            ItemCatListBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_cat_list, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: CatBreedsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Breed>() {

        override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean = oldItem === newItem

        override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean = oldItem.id == newItem.id
    }

    inner class CatBreedsViewHolder(private var binding: ItemCatListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                breedClickListener(getItem(adapterPosition))
            }
        }

        fun bind(breed: Breed) {
            binding.breed = breed
            binding.executePendingBindings()
        }
    }
}
