package com.dineshworkspace.datingapp.discover

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.dineshworkspace.datingapp.R
import com.dineshworkspace.datingapp.base.BaseFragment
import com.dineshworkspace.datingapp.base.ProfileResponse
import com.dineshworkspace.datingapp.base.toast
import com.dineshworkspace.datingapp.dataModels.BaseResponse
import com.dineshworkspace.datingapp.dataModels.Status
import kotlinx.android.synthetic.main.fragment_otp_verification.*
import kotlinx.android.synthetic.main.layout_loading.*

class DiscoverFragment : BaseFragment(R.layout.fragment_discover) {

    private val discoverViewModel: DiscoverViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        discoverViewModel.profileResponse.observe(viewLifecycleOwner, {
            onProfileResponseReceived(it)
        })
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
        group_input_items.visibility = View.GONE
    }

    private fun showErrorScreen(message: String?) {
        layout_loading.visibility = View.GONE
        group_input_items.visibility = View.VISIBLE
        message?.let {
            requireContext().toast(it)
        }
    }

    private fun showSuccessScreen(profileResponse: ProfileResponse?) {
        layout_loading.visibility = View.GONE

    }

}