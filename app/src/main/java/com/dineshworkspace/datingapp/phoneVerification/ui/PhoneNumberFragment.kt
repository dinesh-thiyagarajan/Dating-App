package com.dineshworkspace.datingapp.phoneVerification.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.dineshworkspace.datingapp.LandingActivity
import com.dineshworkspace.datingapp.R
import com.dineshworkspace.datingapp.base.BaseFragment
import com.dineshworkspace.datingapp.base.PhoneNumberLoginResponse
import com.dineshworkspace.datingapp.base.hideKeyboard
import com.dineshworkspace.datingapp.base.toast
import com.dineshworkspace.datingapp.dataModels.BaseResponse
import com.dineshworkspace.datingapp.dataModels.Status
import com.dineshworkspace.datingapp.helpers.AppConstants
import com.dineshworkspace.datingapp.helpers.SharedPrefHelper
import com.dineshworkspace.datingapp.phoneVerification.viewModel.PhoneNumberVerificationViewModel
import kotlinx.android.synthetic.main.fragment_phone_number.*
import kotlinx.android.synthetic.main.layout_loading.*


class PhoneNumberFragment : BaseFragment(layoutId = R.layout.fragment_phone_number) {

    private val phoneNumVerificationViewModel: PhoneNumberVerificationViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bnt_continue.setOnClickListener {
            onContinueClicked()
        }

        phoneNumVerificationViewModel.loginResponse.observe(viewLifecycleOwner, {
            onLoginResponseObtained(it)
        })
    }

    private fun onContinueClicked() {
        if (et_ph_num.text.toString().isEmpty()
            || et_ph_num.text.toString().length < 10
        ) {
            requireContext().toast(getString(R.string.err_phone_number))
            return
        }
        requireContext().hideKeyboard(requireView())
        phoneNumVerificationViewModel.loginWithPhoneNumber(et_country_code.text.toString() + et_ph_num.text.toString())
    }


    private fun onLoginResponseObtained(it: BaseResponse<PhoneNumberLoginResponse>?) {
        when (it?.status) {
            Status.LOADING -> showLoading()
            Status.SUCCESS -> showSuccessScreen(it?.data)
            Status.ERROR -> showErrorScreen(it.message)
        }
    }

    private fun showErrorScreen(message: String?) {
        layout_loading.visibility = View.GONE
        group_input_items.visibility = View.VISIBLE
        message?.let {
            requireContext().toast(it)
        }
    }

    private fun showSuccessScreen(loginResponse: PhoneNumberLoginResponse?) {
        layout_loading.visibility = View.GONE
        if (loginResponse!!.loginStatus
            && !SharedPrefHelper.getString(AppConstants.PREF_API_TOKEN, "").isNullOrEmpty()
        ) {
            showDiscoverFragment()
        } else {
            showOtpFragment()
        }
    }

    private fun showDiscoverFragment() {
        (activity as LandingActivity).showFragment(
            R.id.action_phoneNumberFragment_to_discoverFragment,
            null
        )
    }

    private fun showOtpFragment() {
        val bundle = Bundle()
        bundle.putString(
            AppConstants.BUNDLE_PHONE_NUMBER,
            et_country_code.text.toString() + et_ph_num.text.toString()
        )
        (activity as LandingActivity).showFragment(
            R.id.action_phoneNumberFragment_to_OTPVerificationFragment,
            bundle,
        )
        phoneNumVerificationViewModel.loginResponse.postValue(null)
    }

    private fun showLoading() {
        layout_loading.visibility = View.VISIBLE
        group_input_items.visibility = View.GONE
    }


}