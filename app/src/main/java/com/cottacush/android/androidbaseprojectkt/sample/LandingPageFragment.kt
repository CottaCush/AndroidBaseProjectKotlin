package com.cottacush.android.androidbaseprojectkt.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.cottacush.android.androidbaseprojectkt.R
import com.cottacush.android.androidbaseprojectkt.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_landing_page.*

class LandingPageFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_landing_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.setUpToolBar(getString(R.string.cat_breeds), true)
        basicSampleButton.setOnClickListener {
            findNavController().navigate(LandingPageFragmentDirections.actionLandingPageFragmentToBreedsListFragment())
        }
        advancedSampleButton.setOnClickListener {
            findNavController().navigate(LandingPageFragmentDirections.actionLandingPageFragmentToAdvancedBreedListFragment())
        }
    }
}
