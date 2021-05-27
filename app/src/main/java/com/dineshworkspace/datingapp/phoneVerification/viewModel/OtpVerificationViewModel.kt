package com.dineshworkspace.datingapp.phoneVerification.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dineshworkspace.datingapp.base.BaseViewModel
import com.dineshworkspace.datingapp.base.PhoneNumberLoginResponse
import com.dineshworkspace.datingapp.dataModels.BaseResponse
import com.dineshworkspace.datingapp.phoneVerification.PhoneNumberValidationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpVerificationViewModel @Inject constructor(private val phoneNumberValidationRepository: PhoneNumberValidationRepository) :
    BaseViewModel() {

    val time: MutableLiveData<String> = MutableLiveData()
    val otpValidationResponse: MutableLiveData<BaseResponse<PhoneNumberLoginResponse>> =
        MutableLiveData()
    var jobForTimer: Job? = null

    fun startOtpTimer() {
        jobForTimer = viewModelScope.launch {
            val timer = (60 downTo 0)
                .asSequence()
                .asFlow()
                .onEach { delay(1_000) }

            timer.collect {
                time.postValue(it.toString())
            }
        }
        jobForTimer?.start()
    }


    fun validateOtp(number: String, otp: String) {
        viewModelScope.launch {
            phoneNumberValidationRepository.validateOtp(number, otp).collect {
                otpValidationResponse.postValue(it)
            }
        }
    }


}