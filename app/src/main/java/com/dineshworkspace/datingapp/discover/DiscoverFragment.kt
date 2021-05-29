package com.dineshworkspace.datingapp.discover

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.dineshworkspace.datingapp.R
import com.dineshworkspace.datingapp.base.BaseFragment
import com.dineshworkspace.datingapp.base.ProfileResponse
import com.dineshworkspace.datingapp.base.toast
import com.dineshworkspace.datingapp.dataModels.BaseResponse
import com.dineshworkspace.datingapp.dataModels.Status
import com.dineshworkspace.datingapp.discover.adapter.ProfileAdapter
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.android.synthetic.main.fragment_otp_verification.*
import kotlinx.android.synthetic.main.layout_loading.*

class DiscoverFragment : BaseFragment(R.layout.fragment_discover) {

    private val discoverViewModel: DiscoverViewModel by activityViewModels()
    private lateinit var profileAdapter: ProfileAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        discoverViewModel.profileResponse.observe(viewLifecycleOwner, {
            onProfileResponseReceived(it)
        })

        discoverViewModel.dummyProfiles.observe(viewLifecycleOwner, {
            profileAdapter.submitList(it)
        })
    }

    private fun initAdapter() {
        profileAdapter = ProfileAdapter()
        val mLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_profiles.layoutManager = mLayoutManager
        rv_profiles.itemAnimator = DefaultItemAnimator()
        rv_profiles.adapter = profileAdapter
    }

    private fun onProfileResponseReceived(it: BaseResponse<ProfileResponse>?) {
        when (it?.status) {
            Status.LOADING -> showLoading()
            Status.SUCCESS -> showSuccessScreen(it?.data)
            Status.ERROR -> showErrorScreen(it.message)
        }
    }

    private fun showLoading() {
        layout_loading.visibility = View.VISIBLE
    }

    private fun showErrorScreen(message: String?) {
        layout_loading.visibility = View.GONE
    }

    private fun showSuccessScreen(profileResponse: ProfileResponse?) {
        layout_loading.visibility = View.GONE
    }

}