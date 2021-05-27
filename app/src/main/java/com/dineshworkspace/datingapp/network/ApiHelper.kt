package com.dineshworkspace.datingapp.network

import com.dineshworkspace.datingapp.base.PhoneNumberLoginResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun loginUsingPhoneNumber(params: Map<String, String>): Response<PhoneNumberLoginResponse>
}
