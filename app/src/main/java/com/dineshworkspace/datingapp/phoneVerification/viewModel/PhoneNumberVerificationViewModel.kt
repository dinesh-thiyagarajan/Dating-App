package com.dineshworkspace.datingapp.phoneVerification.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dineshworkspace.datingapp.base.BaseViewModel
import com.dineshworkspace.datingapp.base.PhoneNumberLoginResponse
import com.dineshworkspace.datingapp.dataModels.BaseResponse
import com.dineshworkspace.datingapp.phoneVerification.PhoneNumberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneNumberVerificationViewModel @Inject constructor(private val phoneNumberRepository: PhoneNumberRepository) :
    BaseViewModel() {

    val loginResponse: MutableLiveData<BaseResponse<PhoneNumberLoginResponse>> = MutableLiveData()

    fun loginWithPhoneNumber(phoneNumber: String) {
        viewModelScope.launch {
            phoneNumberRepository.loginWithPhoneNumber(phoneNumber).collect {
                loginResponse.postValue(it)
            }
        }
    }

}