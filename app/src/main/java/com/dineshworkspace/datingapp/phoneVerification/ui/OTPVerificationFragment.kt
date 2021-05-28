package com.dineshworkspace.datingapp.phoneVerification.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dineshworkspace.datingapp.LandingActivity
import com.dineshworkspace.datingapp.R
import com.dineshworkspace.datingapp.base.BaseFragment
import com.dineshworkspace.datingapp.base.OtpVerificationResponse
import com.dineshworkspace.datingapp.base.toast
import com.dineshworkspace.datingapp.dataModels.BaseResponse
import com.dineshworkspace.datingapp.dataModels.Status
import com.dineshworkspace.datingapp.helpers.AppConstants
import com.dineshworkspace.datingapp.helpers.SharedPrefHelper
import com.dineshworkspace.datingapp.phoneVerification.viewModel.OtpVerificationViewModel
import kotlinx.android.synthetic.main.fragment_otp_verification.*
import kotlinx.android.synthetic.main.layout_loading.*


class OTPVerificationFragment : BaseFragment(R.layout.fragment_otp_verification) {

    private val otpVerificationViewModel: OtpVerificationViewModel by activityViewModels()
    private var phoneNumber: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        otpVerificationViewModel.startOtpTimer()

        phoneNumber = arguments?.getString(AppConstants.BUNDLE_PHONE_NUMBER)
        phoneNumber?.let {
            tv_phone_number.text = it
        }

        otpVerificationViewModel.time.observe(viewLifecycleOwner, {
            tv_timer.text = getString(R.string.timer, it)
        })

        bnt_continue.setOnClickListener {
            onContinueClicked()
        }

        tv_phone_number.setOnClickListener {
            otpVerificationViewModel.jobForTimer?.cancel()
            findNavController().popBackStack()
        }

        otpVerificationViewModel.otpValidationResponse.observe(viewLifecycleOwner, {
            onOtpValidationResponseReceived(it)
        })
    }

    private fun onOtpValidationResponseReceived(it: BaseResponse<OtpVerificationResponse>?) {
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

    private fun showSuccessScreen(otpVerificationResponse: OtpVerificationResponse?) {
        layout_loading.visibility = View.GONE
        otpVerificationResponse?.token?.let {
            SharedPrefHelper.saveString(AppConstants.PREF_API_TOKEN, it)
        }
        (activity as LandingActivity).showFragment(
            R.id.action_OTPVerificationFragment_to_discoverFragment,
            null
        )
        SharedPrefHelper.saveBoolean(AppConstants.PREF_IS_PHONE_VALIDATED, true)
        (activity as LandingActivity).showHideBottomNav()
    }

    private fun showLoading() {
        layout_loading.visibility = View.VISIBLE
    }

    private fun onContinueClicked() {
        if (et_otp.text.toString().isEmpty()) {
            requireContext().toast(getString(R.string.err_otp))
            return
        }
        phoneNumber?.let {
            otpVerificationViewModel.validateOtp(it, et_otp.text.toString())
        }
    }
}