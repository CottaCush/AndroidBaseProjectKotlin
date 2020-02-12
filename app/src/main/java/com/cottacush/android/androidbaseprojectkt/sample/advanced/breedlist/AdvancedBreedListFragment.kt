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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.cottacush.android.androidbaseprojectkt.App
import com.cottacush.android.androidbaseprojectkt.R
import com.cottacush.android.androidbaseprojectkt.base.BaseFragment
import com.cottacush.android.androidbaseprojectkt.databinding.FragmentAdvancedBreedsBinding
import com.cottacush.android.androidbaseprojectkt.networkutils.LoadingStatus
import javax.inject.Inject

class AdvancedBreedListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentAdvancedBreedsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdvancedBreedsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.setUpToolBar(getString(R.string.breed_list))
        (mainActivity.applicationContext as App).component.inject(this)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AdvancedBreedListViewModel::class.java)
        binding.viewModel = viewModel

        binding.breedsRecyclerView.adapter = AdvancedBreedListAdapter {
            viewModel.displayCatBreedDetails(it)
        }

        viewModel.navigateToSelectedBreed.observe(this, Observer {
            if (it != null) {
                this.findNavController().navigate(
                    AdvancedBreedListFragmentDirections
                        .actionAdvancedBreedListFragmentToAdvancedBreedDetailsFragment(it)
                )
                viewModel.displayCatBreedDetailsComplete()
            }
        })

        viewModel.loadingStatus.observe(this, Observer {
            when (it) {
                LoadingStatus.Success -> mainActivity.dismissLoading()
                is LoadingStatus.Loading -> mainActivity.showLoading(it.message)
                is LoadingStatus.Error -> mainActivity.showError(it.errorMessage)
            }
        })
    }
}
