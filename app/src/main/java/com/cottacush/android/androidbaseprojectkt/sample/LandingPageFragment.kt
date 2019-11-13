package com.cottacush.android.androidbaseprojectkt.sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.cottacush.android.androidbaseprojectkt.R
import com.cottacush.android.androidbaseprojectkt.sample.advanced.breedlist.AdvancedBreedListFragmentDirections
import kotlinx.android.synthetic.main.fragment_landing_page.*

class LandingPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_landing_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicSampleButton.setOnClickListener {
            findNavController().navigate(
                LandingPageFragmentDirections.actionLandingPageFragmentToBreedsListFragment()
            )
        }

        advanceSampleButton.setOnClickListener {
            findNavController().navigate(
                LandingPageFragmentDirections.actionLandingPageFragmentToAdvancedBreedListFragment()
            )
        }
    }

}
