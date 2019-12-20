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
