package com.dineshworkspace.datingapp.discover

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.dineshworkspace.datingapp.R
import com.dineshworkspace.datingapp.base.BaseFragment
import com.dineshworkspace.datingapp.base.ProfileResponse
import com.dineshworkspace.datingapp.dataModels.BaseResponse

class DiscoverFragment : BaseFragment(R.layout.fragment_discover) {

    private val discoverViewModel: DiscoverViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        discoverViewModel.profileResponse.observe(viewLifecycleOwner, {
            onProfileResponseReceived(it)
        })
    }

    private fun onProfileResponseReceived(it: BaseResponse<ProfileResponse>?) {

    }

}