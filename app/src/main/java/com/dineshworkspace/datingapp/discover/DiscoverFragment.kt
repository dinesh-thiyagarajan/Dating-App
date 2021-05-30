package com.dineshworkspace.datingapp.discover

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dineshworkspace.datingapp.R
import com.dineshworkspace.datingapp.base.BaseFragment
import com.dineshworkspace.datingapp.base.ProfileResponse
import com.dineshworkspace.datingapp.base.toast
import com.dineshworkspace.datingapp.dataModels.BaseResponse
import com.dineshworkspace.datingapp.dataModels.Status
import com.dineshworkspace.datingapp.discover.adapter.ProfileAdapter
import kotlinx.android.synthetic.main.fragment_discover.*
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
    }

    private fun initAdapter() {
        profileAdapter = ProfileAdapter()
        val mLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
        cv_discover_data.visibility = View.GONE
    }

    private fun showErrorScreen(message: String?) {
        layout_loading.visibility = View.GONE
        cv_discover_data.visibility = View.VISIBLE
        message?.let {
            requireContext().toast(message)
        }
    }

    private fun showSuccessScreen(profileResponse: ProfileResponse?) {
        layout_loading.visibility = View.GONE
        cv_discover_data.visibility = View.VISIBLE
        profileResponse?.let {
            val profile = it.invites.profiles[0]
            tv_profile_name.text = profile.name
            val photo = profile.generalInformation.photo[0]
            Glide.with(requireContext())
                .load(photo.photo)
                .placeholder(R.drawable.discover_grl)
                .into(iv_discover_main)

            it.likes?.let { likes ->
                profileAdapter.submitList(likes.profiles)
            }
        }
    }

}