package com.dineshworkspace.datingapp.phoneVerification.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.dineshworkspace.datingapp.R
import com.dineshworkspace.datingapp.base.BaseFragment
import com.dineshworkspace.datingapp.helpers.AppConstants
import com.dineshworkspace.datingapp.phoneVerification.viewModel.OtpVerificationViewModel
import kotlinx.android.synthetic.main.fragment_otp_verification.*


class OTPVerificationFragment : BaseFragment(R.layout.fragment_otp_verification) {

    private val otpVerificationViewModel: OtpVerificationViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        otpVerificationViewModel.startOtpTimer()

        val phoneNumber = arguments?.getString(AppConstants.BUNDLE_PHONE_NUMBER)
        phoneNumber?.let {
            tv_phone_number.text = it
        }

        otpVerificationViewModel.time.observe(viewLifecycleOwner, {
            tv_timer.text = getString(R.string.timer, it)
        })
    }
}