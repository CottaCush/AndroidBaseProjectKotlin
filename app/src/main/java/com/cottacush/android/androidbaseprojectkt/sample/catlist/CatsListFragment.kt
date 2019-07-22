package com.cottacush.android.androidbaseprojectkt.sample.catlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.cottacush.android.androidbaseprojectkt.App
import com.cottacush.android.androidbaseprojectkt.MainActivity
import com.cottacush.android.androidbaseprojectkt.databinding.FragmentCatListsBinding
import com.r4sh33d.duplicatecontactsremover.DuplicateContactsApp
import com.r4sh33d.duplicatecontactsremover.MainActivity
import com.r4sh33d.duplicatecontactsremover.R
import com.r4sh33d.duplicatecontactsremover.databinding.FragmentContactSourcesConstaraintLayoutBinding
import javax.inject.Inject

class CatsListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentCatListsBinding;

    private val mainActivity: MainActivity
        get() {
            return activity as? MainActivity ?: throw IllegalStateException("Not attached!")
        }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (mainActivity.applicationContext as App).component.inject(this)
        binding = FragmentCatListsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(CatsListViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.getContactsAccountsList(fragmentArgs.duplicateCriteria)


        binding.contactSourcesRecyclerView.adapter = CatsListAdapter {
            viewModel.displayContactAccountDetails(it)
        }

        viewModel.navigateToSelectedContactsAccount.observe(this, Observer {
            if (it != null) {
                this.findNavController().navigate(
                        ContactSourcesFragmentDirections.actionContactSourcesFragmentToDuplicateContactFixFragment(it,
                                fragmentArgs.duplicateCriteria
                        )
                )
                viewModel.displayContactAccountDetailsComplete()
            }
        })
    }

}
