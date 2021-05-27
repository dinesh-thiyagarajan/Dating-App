package com.dineshworkspace.datingapp.network

import com.dineshworkspace.datingapp.base.PhoneNumberLoginResponse
import com.dineshworkspace.datingapp.helpers.AppConstants
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface ApiService {

    @POST(AppConstants.END_POINT_PHONE_NUMBER_LOGIN)
    suspend fun phoneNumberLogin(
        @QueryMap params: Map<String, String>
    ): Response<PhoneNumberLoginResponse>

    @POST(AppConstants.END_POINT_VERIFY_OTP)
    suspend fun validateOtp(
        @QueryMap params: Map<String, String>
    ): Response<PhoneNumberLoginResponse>

}