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
package com.cottacush.android.androidbaseprojectkt.sample.basic.breeddetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cottacush.android.androidbaseprojectkt.base.BaseFragment
import com.cottacush.android.androidbaseprojectkt.databinding.FragmentBreedDetailBinding
import com.cottacush.android.androidbaseprojectkt.extensions.viewUrl

class BreedDetailsFragment : BaseFragment() {

    lateinit var binding: FragmentBreedDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreedDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = BreedDetailsFragmentArgs.fromBundle(arguments!!)
        mainActivity.setUpToolBar("Breed Details")
        binding.breed = args.breed
        binding.viewDetailsButton.setOnClickListener {
            args.breed.wikipediaUrl?.let {
                mainActivity.viewUrl(it)
            }
        }
    }
}
