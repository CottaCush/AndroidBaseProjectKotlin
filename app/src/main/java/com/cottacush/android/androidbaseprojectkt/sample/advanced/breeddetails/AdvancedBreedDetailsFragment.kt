package com.cottacush.android.androidbaseprojectkt.sample.advanced.breeddetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cottacush.android.androidbaseprojectkt.R
import com.cottacush.android.androidbaseprojectkt.base.BaseFragment
import com.cottacush.android.androidbaseprojectkt.databinding.FragmentAdvancedBreedDetailBinding
import com.cottacush.android.androidbaseprojectkt.extensions.viewUrl

class AdvancedBreedDetailsFragment : BaseFragment() {

    lateinit var binding: FragmentAdvancedBreedDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdvancedBreedDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = AdvancedBreedDetailsFragmentArgs.fromBundle(arguments!!)
        mainActivity.setUpToolBar(getString(R.string.breed_details_advanced))
        binding.breed = args.breed
        binding.viewDetailsButton.setOnClickListener {
            args.breed.wikipediaUrl?.let {
                mainActivity.viewUrl(it)
            }
        }
    }
}
