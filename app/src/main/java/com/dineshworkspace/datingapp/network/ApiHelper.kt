package com.dineshworkspace.datingapp.network

import com.dineshworkspace.datingapp.base.OtpVerificationResponse
import com.dineshworkspace.datingapp.base.PhoneNumberLoginResponse
import com.dineshworkspace.datingapp.base.ProfileResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun loginUsingPhoneNumber(params: Map<String, String>): Response<PhoneNumberLoginResponse>
    suspend fun validateOtp(params: Map<String, String>): Response<OtpVerificationResponse>
    suspend fun fetchProfileList(params: Map<String, String>): Response<ProfileResponse>
}
