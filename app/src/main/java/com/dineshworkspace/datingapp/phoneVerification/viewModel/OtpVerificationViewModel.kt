package com.dineshworkspace.datingapp.phoneVerification.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dineshworkspace.datingapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpVerificationViewModel @Inject constructor() : BaseViewModel() {

    val time: MutableLiveData<String> = MutableLiveData()

    fun startOtpTimer() {
        viewModelScope.launch {
            val timer = (60 downTo 0)
                .asSequence()
                .asFlow()
                .onEach { delay(1_000) }

            timer.collect {
                time.postValue(it.toString())
            }
        }
    }


    fun validateOtp(otp: String){

    }


}