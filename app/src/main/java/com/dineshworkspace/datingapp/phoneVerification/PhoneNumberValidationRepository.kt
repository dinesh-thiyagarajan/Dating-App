package com.dineshworkspace.datingapp.phoneVerification

import com.dineshworkspace.datingapp.base.PhoneNumberLoginResponse
import com.dineshworkspace.datingapp.dataModels.BaseResponse
import com.dineshworkspace.datingapp.helpers.AppConstants
import com.dineshworkspace.datingapp.helpers.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PhoneNumberValidationRepository @Inject constructor(private val dataSource: DataSource) {

    suspend fun loginWithPhoneNumber(phoneNumber: String): Flow<BaseResponse<PhoneNumberLoginResponse>?> {
        return flow {
            emit(BaseResponse.loading(null))
            val map = mapOf(
                AppConstants.API_KEY_NUMBER to phoneNumber,
            )
            val result = dataSource.loginUsingPhoneNumber(map)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun validateOtp(number: String, otp: String): Flow<BaseResponse<PhoneNumberLoginResponse>?> {
        return flow {
            emit(BaseResponse.loading(null))
            val map = mapOf(
                AppConstants.API_KEY_NUMBER to number,
                AppConstants.API_KEY_OTP to otp,
            )
            val result = dataSource.validateOtp(map)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}