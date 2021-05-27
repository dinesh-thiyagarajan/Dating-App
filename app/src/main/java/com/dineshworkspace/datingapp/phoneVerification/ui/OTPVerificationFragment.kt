package com.dineshworkspace.datingapp.phoneVerification.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dineshworkspace.datingapp.R
import com.dineshworkspace.datingapp.base.BaseFragment
import com.dineshworkspace.datingapp.base.PhoneNumberLoginResponse
import com.dineshworkspace.datingapp.base.toast
import com.dineshworkspace.datingapp.dataModels.BaseResponse
import com.dineshworkspace.datingapp.helpers.AppConstants
import com.dineshworkspace.datingapp.phoneVerification.viewModel.OtpVerificationViewModel
import kotlinx.android.synthetic.main.fragment_otp_verification.*


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

    private fun onOtpValidationResponseReceived(it: BaseResponse<PhoneNumberLoginResponse>?) {

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